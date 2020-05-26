package fr.unice.miage.common.game_objects;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.Config;
import fr.unice.miage.common.Repository;
import fr.unice.miage.common.geom.Rotation;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.input.Mouse;
import fr.unice.miage.common.plugins.PlugInGraphic;
import fr.unice.miage.common.plugins.PlugInMovement;
import fr.unice.miage.common.plugins.PlugInWeapon;
import fr.unice.miage.common.sprite.Sprite;
import javafx.scene.paint.Paint;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String playerName;
    private final int playerID;
    private Vector2 position = new Vector2();
    private Vector2 centerPosition = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 acceleration = new Vector2();
    private Vector2 imgDirection = new Vector2(0,-1);
    private Vector2 weaponDirection = new Vector2(0,-1);
    private double rotation = 0;
    private double weaponRotation = 0;
    private final double maxSpeed = 3;
    private final double maxVelocity = 1;
    private final double maxForce = 2;
    private double mass = 3;
    private boolean hasMove = false;
    private boolean hasWeapon = false;
    private boolean hasGraphic = false;
    private boolean isBlocked = false;

    private List<Projectile> projectiles = new ArrayList<>();
    // Statistiques //
    private int numberOfShots = 0;
    private double lastShot = 0;
    private int movingDistance = 0;
    private int numberOfMoves = 0;

    private final Repository repository;
    private final CanvasGUI canvas;
    private PlugInMovement pm;
    private PlugInWeapon pw;
    private PlugInGraphic pg;
    private HealthBar healthBar;
    private double health = 100;
    private boolean alive;
    private List<PlugInWeapon> weapons;

    private Sprite playerSprite;

    public Player(List<String> plugins, Repository repository, CanvasGUI canvas, int playerID) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        this.repository = repository;
        this.canvas = canvas;
        this.playerID = playerID;
        this.playerName = "Player" + playerID;
        this.alive = true;
        this.loadPlugins(plugins);
        this.setPlayerWeapons();
    }

    public String getPlayerName(){ return playerName; }
    public int getPlayerID() { return playerID; }
    public double getLastShot(){ return lastShot; }

    // Methods for health
    public void setHealthBar(int width, int height, Paint color){
        healthBar = new HealthBar(width, height, color);
    }
    public HealthBar getHealthBar(){ return healthBar; }
    public double getHealth(){ return health; }
    public void setHealth(double value){
        if(value <= 0) {
            health = 0;
            alive = false;
            pg.setToDead(this);
        }
        else health = value;
    }
    // Methods for mass value
    public double getMass() { return mass; }
    public void setMass(double m){ mass = m; }

    /** Move methods **/
    public void move(){
        pm.move(this);
    }

    // Methods for position x and y
    public double getX(){ return position.getX(); }
    public void setX(double x){ position = new Vector2(x, position.getY()); }
    public double getY(){ return position.getY(); }
    public void setY(double y){ position = new Vector2(position.getX(), y); }

    // Methods for speedX and speedY
    public void setSpeedX(double x){ velocity = new Vector2(x, velocity.getY()); }
    public void setSpeedY(double y){ velocity = new Vector2(velocity.getX(), y);}
    public double getSpeedX(){ return velocity.getX(); }
    public double getSpeedY(){ return velocity.getY(); }
    public void reverseSpeed(){ velocity = new Vector2(-velocity.getX(), -velocity.getY()); }

    /* Vectors */
    // Methods for position vectors
    public Vector2 getPosition(){ return position;}
    public void setPosition(Vector2 v){ position = v;}
    public void addPosition(Vector2 v){ position.add(v); }
    public Vector2 getCenterPosition(){
        double centerX = position.getX() + playerSprite.getWidth();
        double centerY = position.getY() + playerSprite.getHeight();
        return new Vector2(centerX, centerY);
    }

    // Methods for velocity vectors
    public Vector2 getVelocity() { return velocity; }
    public void setVelocity(Vector2 v){ velocity = v; }
    public void addVelocity(Vector2 v){ velocity.add(v); }

    // Methods for acceleration vectors
    public Vector2 getAcceleration(){ return acceleration; }
    public void setAcceleration(Vector2 v) {  acceleration = v; }
    public void addAcceleration(Vector2 v){ acceleration.add(v); }

    // Methods for rotation value
    public void setImgDirection(Vector2 v){ imgDirection = v; }
    public void setRotation(double rot) { rotation += rot; }
    public double getRotation(){
        if(!Mouse.isMouseOn()) {
            return Rotation.rotation2Vectors(imgDirection, velocity);
        } else return rotation;
    }
    public void setWeaponDirection(Vector2 v){ weaponDirection = v; }
    public Vector2 getWeaponDirection(){ return weaponDirection; }
    public void setWeaponRotation(double rot){ weaponRotation = rot; }
    public double getWeaponRotation(){ return weaponRotation; }

    // Getters for max values
    public double getMaxVelocity() { return maxVelocity; }
    public double getMaxForce() { return maxForce; }
    public double getMaxSpeed() { return maxSpeed; }

    /** Graphic methods **/
    public void draw(){
        pg.draw(this, canvas);
    }
    // Methods for player sprite
    public Sprite getSprite(){ if(hasGraphic) return playerSprite;
        return null;
    }
    public void setSprite(Sprite sprite){ playerSprite = sprite;}

    /** Other methods **/
    public boolean isAlive(){ return alive; }
    public List<Projectile> getProjectiles(){ return projectiles; }


    /** Getters for plugins **/
    public List<PlugInWeapon> getPlayerWeapons(){ return weapons; }
    public PlugInMovement getPluginMovement(){ return pm; }
    public PlugInWeapon getPluginWeapon(){ return pw; }
    public PlugInGraphic getPluginGraphic(){ return pg; }

    /** hasers for plugins **/
    public boolean hasMove(){ return hasMove; }
    public boolean hasGraphic(){ return hasGraphic; }
    public boolean hasWeapon(){ return hasWeapon; }

    public void setPlayerWeapons(){ }

    public void shoot() { pw.shoot(this); }

    public void setMovingDistance(double distance){
        movingDistance += (int) distance;
        numberOfMoves++;
        if(isBlocked && numberOfMoves % 3 == 0) {
            isBlocked = false;
        }
    }
    public double getMovingDistance(){ return movingDistance; }

    public void incrementNumberOfShots() { numberOfShots++; }
    public void setLastShot(double chrono) { lastShot = chrono; }
    public int getNumberOfShots(){ return numberOfShots; }

    public void getHitByProjectile(Projectile projectile){
        projectile.applyPlayerImpact(this);
    }

    public void getHitByPlayer(Player player){}

    public void addProjectile(Projectile projectile){ projectiles.add(projectile); }
    public void removeProjectile(Projectile projectile) { projectiles.remove(projectile); }
    public void drawProjectiles(){ for(Projectile prj : projectiles) pw.draw(canvas, prj); }
    public void onProjectileOut(String axis, Projectile projectile) { pw.onProjectileOut(axis, projectile); }

    public void moveProjectiles() {
        for(Projectile prj : projectiles) pw.moveProjectile(prj);
    }
    public void setProjectiles(List<Projectile> prjList) {
        projectiles = prjList;
    }

    private void loadPlugins(List<String> plugins) throws  InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(!plugins.get(0).equals("Aucun")) {
            pm = repository.loadMovement(plugins.get(0));
            pm.init(this);
            hasMove = true;
        }
        if(!plugins.get(1).equals("Aucun")) {
            pw = repository.loadWeapon(plugins.get(1));
            hasWeapon = true;
        }
        if(!plugins.get(2).equals("Aucun")) {
            pg = repository.loadGraphic(plugins.get(2));
            pg.init(this);
            hasGraphic = true;
        }
    }

    public boolean isOutOfBorders() {
        if(hasGraphic){
            double w = this.getSprite().getWidth();
            double h = this.getSprite().getHeight();
            if (position.getX() + w > Config.getWorldWidth() || position.getX() < 0) { return true; }
            return position.getY() + h > Config.getWorldHeight() || position.getY() < 0;
        } else {
            if (position.getX() > Config.getWorldWidth() || position.getX() < 0) { return true; }
            return position.getY() > Config.getWorldHeight() || position.getY() < 0;
        }
    }

    public void resetStat() {
        numberOfShots = 0;
        lastShot = 0;
        movingDistance = 0;
        numberOfMoves = 0;
    }

    public void setBlocked(boolean bool){ isBlocked = bool; }

    public boolean getBlocked() { return isBlocked; }
}