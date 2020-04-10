package fr.unice.miage.game_objects;

import fr.unice.miage.game.Repository;
import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.game.gui.HealthBar;
import fr.unice.miage.geom.Vector2;
import fr.unice.miage.plugins.PlugInGraphic;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.plugins.PlugInWeapon;
import fr.unice.miage.sprite.Sprite;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Player {

    private String playerName = "Player";
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private double rotation;
    private Repository repository;
    private CanvasGUI canvas;
    private PlugInMovement pluginMovement;
    private PlugInWeapon pluginWeapon;
    private PlugInGraphic pluginGraphic;
    private Sprite playerSprite;
    private double health = 50;
    private HealthBar healthBar;
    private List<PlugInWeapon> weapons;
    private boolean isAlive;

    public Player(List<String> plugins, Repository repository, CanvasGUI canvas) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.position = new Vector2(100,100);
        this.velocity = new Vector2(0.1,0.1);
        this.acceleration = new Vector2();
        this.rotation = 0;
        this.repository = repository;
        this.canvas = canvas;
        this.healthBar = new HealthBar(this, this.canvas);
        //this.health.addListener((observableValue, aDouble, t1) -> healthBar.bindProgressProperty(health));
        //this.healthBar.bindProgressProperty(this.health);
        this.isAlive = true;
        this.loadPlugins(plugins);
        this.setPlayerSprite();
        this.setPlayerWeapons();
    }

    private void loadPlugins(List<String> plugins) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            this.pluginMovement = this.repository.loadMovement(plugins.get(0));
            this.pluginWeapon = this.repository.loadWeapon(plugins.get(1));
            this.pluginGraphic= this.repository.loadGraphic(plugins.get(2));
    }
    public String getName(){ return this.playerName;}


    public void setPlayerSprite(){
        this.playerSprite = this.pluginGraphic.getPlayerSprite();
    }

    public Sprite getPlayerSprite(){ return this.playerSprite; };

    public HealthBar getPlayerHealthBar(){
        return this.healthBar;
    }

    public double getHealth(){
        return this.health;
    }

    public void setHealth(double value){
        this.health = value;
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


}
