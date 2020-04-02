package fr.unice.miage.game;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Repository {
    ClassLoader loader = new URLClassLoader(new URL[]{new File("fr/unice/miage/classes").toURL()});

    public Repository() throws MalformedURLException {}
    public Repository(File base) throws MalformedURLException {
        this.loader = new URLClassLoader(new URL[] {base.toURL()});
    }
   /* public List<Class<?>> loadMovement(String path){

    } */

   /* public List<Class<?>> loadWeapon(String path){

    } */

   /* public List<Class<?>> loadGraphic(String path){

    } */
}
