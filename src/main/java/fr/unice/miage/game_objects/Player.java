package fr.unice.miage.game_objects;

import fr.unice.miage.game.Repository;
import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.geom.Vector2;
import fr.unice.miage.plugins.PlugInGraphic;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.plugins.PlugInWeapon;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Player {

    private String playerName = "Player";
    private int playerID;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private double rotation;
    private Repository repository;
    private CanvasGUI canvas;
    private PlugInMovement pluginMovement;
    private PlugInWeapon pluginWeapon;
    private PlugInGraphic pluginGraphic;
    private double health = 50;
    private List<PlugInWeapon> weapons;
    private boolean isAlive;

    public Player(List<String> plugins, Repository repository, CanvasGUI canvas, int playerID) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.position = new Vector2(100,100);
        this.velocity = new Vector2(0.1,0.1);
        this.acceleration = new Vector2();
        this.rotation = 0;
        this.repository = repository;
        this.canvas = canvas;
        this.playerID = playerID;
        this.playerName = "Player" + playerID;
        this.isAlive = true;
        this.loadPlugins(plugins);
        this.setPlayerWeapons();
    }

    public String getName(){ return playerName; }
    public int getPlayerID() { return playerID; }

    // Methods for health
    public double getHealth(){ return health; }
    public void setHealth(double value){
        if(value <= 0) health = 0;
        else health = value;
    }

    // Methods for speedX and speedY
    public void setSpeedX(double x){ velocity = new Vector2(x, velocity.getY()); }
    public void setSpeedY(double y){ velocity = new Vector2(velocity.getX(), y); }
    public double getSpeedX(){ return velocity.getX(); }
    public double getSpeedY(){ return velocity.getY(); }

    /* Vectors */
    // Methods for position vectors
    public Vector2 getPosition(){ return position; }
    public void setPosition(Vector2 v){ position = v;}
    public void addPosition(Vector2 v){ position.add(v); }

    // Methods for velocity vectors
    public Vector2 getVelocity() { return velocity; }
    public void setVelocity(Vector2 v){ velocity = v; }
    public void addVelocity(Vector2 v){ velocity.add(v); }

    // Methods for acceleration vectors
    public Vector2 getAcceleration() { return acceleration; }
    public void setAcceleration(Vector2 v){ acceleration = v; }
    public void addAcceleration(Vector2 v){ acceleration.add(v); }

    // Getters for plugins
    public List<PlugInWeapon> getPlayerWeapons(){ return weapons; }
    public PlugInMovement getPluginMovement(){ return pluginMovement; }
    public PlugInWeapon getPluginWeapon(){ return pluginWeapon; }
    public PlugInGraphic getPluginGraphic(){ return pluginGraphic; }

    public void setPlayerWeapons(){ }

    public void getHitByProjectile(Projectile projectile){}

    public void getHitByPlayer(Player player){}

    private void loadPlugins(List<String> plugins) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        pluginMovement = repository.loadMovement(plugins.get(0));
        pluginWeapon = repository.loadWeapon(plugins.get(1));
        pluginGraphic= repository.loadGraphic(plugins.get(2));
        pluginGraphic.init(this);
    }

}
