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
    private int height;
    private int width;

    private Repository repository;
    private CanvasGUI canvas;
    private PlugInGraphic pg;

    public Obstacle() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

    }
}
