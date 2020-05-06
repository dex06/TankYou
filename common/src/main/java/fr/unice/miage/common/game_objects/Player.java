package fr.unice.miage.common.game_objects;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.Repository;
import fr.unice.miage.common.geom.Vector2;
import fr.unice.miage.common.plugins.PlugInGraphic;
import fr.unice.miage.common.plugins.PlugInMovement;
import fr.unice.miage.common.plugins.PlugInWeapon;
import fr.unice.miage.common.sprite.Sprite;
import fr.unice.miage.common.utils.Finder;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private String playerName;
    private int playerID;
    protected Vector2 position = new Vector2();
    protected Vector2 velocity = new Vector2();
    protected Vector2 acceleration = new Vector2();
    protected double rotation = 0;
    protected double maxSpeed = 3;
    protected double maxVelocity = 1;
    protected double maxForce = 2;
    protected double mass = 3;
    protected boolean hasMove = false;
    protected boolean hasWeapon = false;
    protected boolean hasGraphic = false;

    public List<Projectile> projectiles = new ArrayList<>();

    public double lastShot = 0;
    private Repository repository;
    private CanvasGUI canvas;
    private PlugInMovement pm;
    private PlugInWeapon pw;
    private PlugInGraphic pg;
    private double health = 100;
    private List<PlugInWeapon> weapons;
    private boolean alive;

    public Player(List<String> plugins, Repository repository, CanvasGUI canvas, int playerID) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        this.repository = repository;
        this.canvas = canvas;
        this.playerID = playerID;
        this.playerName = "Player" + playerID;
        this.alive = true;
        this.loadPlugins(plugins);
        this.setPlayerWeapons();
    }

    public void shot(){
        Player p = Finder.findClosestPlayer(this);
        double direction = Math.atan2(p.getPosition().getY() - this.getPosition().getY(), p.getPosition().getX() - this.getPosition().getX());
//        System.out.println(this.getName() + " " + direction);

        double xCenter = this.position.getX() + pg.getPlayerSprite().getWidth()/2;
        double yCenter =this.position.getY() + pg.getPlayerSprite().getHeight()/2;
        double longueur = Math.sqrt(Math.pow(pg.getPlayerSprite().getWidth()/2, 2) + Math.pow(pg.getPlayerSprite().getHeight()/2, 2));
//        new Vector2(pm.getPosition().getX(), pm.getPosition().getY());
        projectiles.add(new Projectile(new Vector2(xCenter + longueur*Math.cos(direction),
                yCenter + longueur*Math.sin(direction)),
                direction));
//        projectiles.add(new Projectile(new Vector2(pm.getPosition().getX(), pm.getPosition().getY()), direction));
    }

    public String getName(){ return playerName; }
    public int getPlayerID() { return playerID; }

    // Methods for health
    public double getHealth(){ return health; }
    public void setHealth(double value){
        if(value <= 0) {
            health = 0;
            alive = false;
            pg.setToDead();
        }
        else health = value;
    }
    // Methods for mass value
    public double getMass() { return mass; }
    public void setMass(double m){ mass = m; }

    /** Move methods **/
    public void move(){ pm.move(this); }

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

    // Methods for velocity vectors
    public Vector2 getVelocity() { return velocity; }
    public void setVelocity(Vector2 v){ velocity = v; }
    public void addVelocity(Vector2 v){ velocity.add(v); }

    // Methods for acceleration vectors
    public Vector2 getAcceleration(){ return acceleration; }
    public void setAcceleration(Vector2 v) {  acceleration = v; }
    public void addAcceleration(Vector2 v){ acceleration.add(v); }

    // Methods for rotation value
    public void setRotation(double rot) { rotation = rot; }
    public double getRotation(){ return rotation; }

    // Getters for max values
    public double getMaxVelocity() { return maxVelocity; }
    public double getMaxForce() { return maxForce; }
    public double getMaxSpeed() { return maxSpeed; }

    /** Graphic methods **/
    public void draw(){ pg.draw(canvas); }
    // Methods for player sprite
    public Sprite getSprite(){ return pg.getPlayerSprite(); }

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

    public void getHitByProjectile(Projectile projectile){}

    public void getHitByPlayer(Player player){}

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

    public void checkProjectileOut(){
        pw.checkProjectileOut(projectiles);
//        for (int counter = 0; counter < this.projectiles.size(); counter++) {
//            if(projectiles.get(counter).position.getX() > 600 || projectiles.get(counter).position.getX() < 0 || projectiles.get(counter).position.getY() > 600 || projectiles.get(counter).position.getY() < 0){
//                this.projectiles.remove(counter);
//            }
// POUR DES PROJECTILES QUI PEUVENT REBONDIR SUR DES MURS
//            if(projectiles.get(counter).position.getX() > 600 || projectiles.get(counter).position.getX() < 0){
//                projectiles.get(counter).rotation = Math.PI - projectiles.get(counter).rotation;
//            }
//            else if(projectiles.get(counter).position.getY() > 600 || projectiles.get(counter).position.getY() < 0){
//                projectiles.get(counter).rotation = -projectiles.get(counter).rotation;
//            }
//        }
    }


    public void moveProjectiles() {
        //pw.moveProjectile(projectiles);
    }
}