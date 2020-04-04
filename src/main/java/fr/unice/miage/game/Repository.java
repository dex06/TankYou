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
    public Class loadMovement(String opt){
        String str = "something" ;
        Class stringClass = str.getClass();
        return stringClass;

    }

   public Class loadWeapon(String path){
       String str = "something" ;
       Class stringClass = str.getClass();
       return stringClass;
    }

   public Class loadGraphic(String path){
       String str = "something" ;
       Class stringClass = str.getClass();
       return stringClass;
    }
}
