package fr.unice.miage.game_objects;

import fr.unice.miage.game.Repository;
import fr.unice.miage.geom.Vector2;
import fr.unice.miage.plugins.PlugInGraphic;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.plugins.PlugInWeapon;
import fr.unice.miage.sprite.Sprite;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Player {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;
    private Repository repository;
    private PlugInMovement pluginMovement;
    private PlugInWeapon pluginWeapon;
    private PlugInGraphic pluginGraphic;
    private Sprite playerSprite;
    private int life;

    public Player(List<String> plugins, Repository repository) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.acceleration = new Vector2();
        this.repository = repository;
        this.loadPlugins(plugins);
        this.setSprite();
    }

    private void loadPlugins(List<String> plugins) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            this.pluginMovement = this.repository.loadMovement(plugins.get(0));
            this.pluginWeapon = this.repository.loadWeapon(plugins.get(1));
            this.pluginGraphic= this.repository.loadGraphic(plugins.get(2));
    }

    public PlugInMovement getPluginMovement(){
        return this.pluginMovement;
    }
    public PlugInWeapon getPluginWeapon(){
        return this.pluginWeapon;
    }
    public PlugInGraphic getPluginGraphic(){
        return this.pluginGraphic;
    }

    public void setSprite(){
        this.playerSprite = this.pluginGraphic.getPlayerSprite();
    }


}
