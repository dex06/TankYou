package fr.unice.miage.game;

import fr.unice.miage.classes.GraphicOne;
import fr.unice.miage.classes.MoveOne;
import fr.unice.miage.classes.WeaponOne;
import fr.unice.miage.plugins.PlugInGraphic;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.plugins.PlugInWeapon;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Repository {
    ClassLoader loader = new URLClassLoader(new URL[]{new File("fr/unice/miage/classes").toURL()});

    public Repository() throws MalformedURLException {
        System.out.println("in repository");
    }
    public Repository(File base) throws MalformedURLException {
        this.loader = new URLClassLoader(new URL[] {base.toURL()});
    }
    public Class loadMovement(String opt){
        PlugInMovement pluginMovement = new MoveOne();
        Class moveClass = pluginMovement.getClass();
        return moveClass;

    }

   public Class loadWeapon(String opt){
       PlugInWeapon pluginWeapon = new WeaponOne() ;
       Class weaponClass = pluginWeapon.getClass();
       return weaponClass;
    }

   public Class loadGraphic(String opt){
       PlugInGraphic pluginGraphic = new GraphicOne();
       Class graphicClass = pluginGraphic.getClass();
       return graphicClass;
    }
}
