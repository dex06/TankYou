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
    public void reverseX(){
        this.position.reverseX();
    }
    public void reverseY(){
        this.position.reverseY();
    }
    public void setSpeedX(double x){ velocity = new Vector2(x, velocity.getY()); }
    public void setSpeedY(double y){ velocity = new Vector2(velocity.getX(), y); }
    public double getSpeedX(){ return velocity.getX(); }
    public double getSpeedY(){ return velocity.getY(); }

    public String getName(){ return this.playerName; }

    public double getHealth(){
        return this.health;
    }
    public void setHealth(double value){
        if(value <= 0) health = 0;
        else this.health = value;
    }

    public void setPlayerWeapons(){ }

    public List<PlugInWeapon> getPlayerWeapons(){ return this.weapons; }

    public PlugInMovement getPluginMovement(){
        return this.pluginMovement;
    }
    public PlugInWeapon getPluginWeapon(){
        return this.pluginWeapon;
    }
    public PlugInGraphic getPluginGraphic(){
        return this.pluginGraphic;
    }

    public void getHitByProjectile(Projectile projectile){}

    public void getHitByPlayer(Player player){}


    public Vector2 getVelocity() { return this.velocity; }
    public void setVelocity(Vector2 v){ this.velocity = v; }
    public void addVelocity(Vector2 v){ this.velocity.add(v); }

    public Vector2 getPosition(){ return this.position; }
    public void setPosition(Vector2 v){ this.position = v;}
    public void addPosition(Vector2 v){ this.position.add(v); }

    private void loadPlugins(List<String> plugins) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.pluginMovement = this.repository.loadMovement(plugins.get(0));
        this.pluginWeapon = this.repository.loadWeapon(plugins.get(1));
        this.pluginGraphic= this.repository.loadGraphic(plugins.get(2));
        this.pluginGraphic.init(this);
    }


}
