package fr.unice.miage.game_objects;

import fr.unice.miage.game.Repository;
import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.geom.Vector2;
import fr.unice.miage.plugins.PlugInGraphic;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.plugins.PlugInWeapon;
import fr.unice.miage.sprite.Sprite;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Player {

    private String playerName = "Player";
    private int playerID;

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



    /** Move methods **/
    public void move(){ pm.move(this); }
    // Methods for speedX and speedY
    public void setSpeedX(double x){ pm.setSpeedX(x); }
    public void setSpeedY(double y){ pm.setSpeedY(y); }
    public double getSpeedX(){ return pm.getSpeedX(); }
    public double getSpeedY(){ return pm.getSpeedY(); }
    public void reverseSpeed(){ pm.reverseSpeed(); }

    /* Vectors */
    // Methods for position vectors
    public Vector2 getPosition(){ return pm.getPosition(); }
    public void setPosition(Vector2 v){ pm.setPosition(v);}
    public void addPosition(Vector2 v){ pm.addPosition(v); }

    // Methods for velocity vectors
    public Vector2 getVelocity() { return pm.getVelocity(); }
    public void setVelocity(Vector2 v){ pm.setVelocity(v); }
    public void addVelocity(Vector2 v){ pm.addVelocity(v); }

    /** Graphic methods **/
    public void draw(){ pg.draw(canvas); }
    // Methods for player sprite
    public Sprite getSprite(){ return pg.getPlayerSprite(); }

    /** Other methods **/
    public boolean isAlive(){ return alive; }


    /** Getters for plugins **/
    public List<PlugInWeapon> getPlayerWeapons(){ return weapons; }
    public PlugInMovement getPluginMovement(){ return pm; }
    public PlugInWeapon getPluginWeapon(){ return pw; }
    public PlugInGraphic getPluginGraphic(){ return pg; }

    public void setPlayerWeapons(){ }

    public void getHitByProjectile(Projectile projectile){}

    public void getHitByPlayer(Player player){}

    private void loadPlugins(List<String> plugins) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        pm = repository.loadMovement(plugins.get(0));
        pw = repository.loadWeapon(plugins.get(1));
        pg= repository.loadGraphic(plugins.get(2));
        pg.init(this);
    }

}
