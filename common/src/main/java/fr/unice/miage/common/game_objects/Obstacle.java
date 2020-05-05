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


public class Obstacle {
    protected Vector2 position;
    protected double rotation;
    private Vector2 size;

    private Repository repository;
    private CanvasGUI canvas;
    private PlugInGraphic pg;

    public Obstacle(Vector2 position, Vector2 size) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        this.position = position;
        this.size = size;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getSize() {
        return size;
    }
}
