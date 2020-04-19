package fr.unice.miage.game;

import fr.unice.miage.plugins.*;
import fr.unice.miage.uncompiled.CollisionOne;
import fr.unice.miage.uncompiled.GraphicOne;
import fr.unice.miage.uncompiled.MoveOne;
import fr.unice.miage.uncompiled.WeaponOne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Repository {

    private List<File> jarFiles = new ArrayList<>();
    private ObservableList<String> movePluginsNames = FXCollections.observableArrayList();
    private ObservableList<String> weaponPluginsNames = FXCollections.observableArrayList();
    private ObservableList<String> graphicPluginsNames = FXCollections.observableArrayList();
    private ObservableList<String> collisionPluginsNames = FXCollections.observableArrayList();
    private List<String> gui1PluginsNames = new ArrayList<>();
    private List<Class> movePlugins = new ArrayList<>();
    private List<Class> weaponPlugins = new ArrayList<>();
    private List<Class> graphicPlugins = new ArrayList<>();
    private List<Class> collisionPlugins = new ArrayList<>();
    private List<Class> gui1Plugins = new ArrayList<>();

    private String packageName = "fr.unice.miage";
    private String appFolderName = "uncompiled";
    private String destinationDir = "src/main/java/fr/unice/miage/classes";

    private boolean testing = Config.getTesting();

    public Repository() throws Exception {
        this.loadLibraries("plugins");
    }

    public Repository(String base) throws Exception {
        this.loadLibraries(base);
    }

    public ObservableList<String> getMovePluginsNames() {
        return movePluginsNames;
    }

    public ObservableList<String> getWeaponPluginsNames() {
        return weaponPluginsNames;
    }

    public ObservableList<String> getGraphicPluginsNames() {
        return graphicPluginsNames;
    }

    public ObservableList<String> getCollisionPluginsNames(){ return collisionPluginsNames; }

    public List<String> getGui1PluginsNames(){ return gui1PluginsNames; }


    public List<Class> getMovePlugins() {
        return movePlugins;
    }

    public List<Class> getWeaponPlugins() {
        return weaponPlugins;
    }

    public List<Class> getGraphicPlugins() {
        return graphicPlugins;
    }

    public void loadLibraries(String path) throws Exception {
        File libDir = new File(path);
        if (!libDir.exists() || !libDir.isDirectory()) {
            throw new RuntimeException("Invalid library directory");
        }
        for (File file : libDir.listFiles()) {
            if (!isJarFile(file)) {
                continue;
            }
            System.out.println("Found library format : " + file.getName());
            unzipJarFile(file);
            loadClassesFromJar(file);
        }
    }


    public void loadClassesFromJar(File pathToJar) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        JarFile jarFile = new JarFile(pathToJar);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = {new URL("jar:file:" + pathToJar + "!/")};
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            System.out.println("Loading jar class : " + je.getName());
            String pack = packageName + "." + appFolderName + "." + je.getName().replace(".class", "");
            Class loadedClass = cl.loadClass(pack);
            Object instance = loadedClass.getDeclaredConstructor().newInstance();
            String interfaceName = instance.getClass().getInterfaces()[0].getSimpleName();

            switch (interfaceName) {
                case "PlugInMovement":
                    movePluginsNames.add(instance.getClass().getSimpleName());
                    movePlugins.add(loadedClass);
                    break;
                case "PlugInWeapon":
                    weaponPluginsNames.add(instance.getClass().getSimpleName());
                    weaponPlugins.add(loadedClass);
                    break;
                case "PlugInGraphic":
                    graphicPluginsNames.add(instance.getClass().getSimpleName());
                    graphicPlugins.add(loadedClass);
                    break;
                case "PlugInCollision":
                    collisionPluginsNames.add(instance.getClass().getSimpleName());
                    collisionPlugins.add(loadedClass);
                    break;
                case "PlugInGUI1":
                    gui1PluginsNames.add(instance.getClass().getSimpleName());
                    gui1Plugins.add(loadedClass);
                    break;
            }
        }
    }

    private boolean isJarFile(File file) {
        return file.getName().endsWith(".jar");
    }

    private void unzipJarFile(File file) throws IOException, ClassNotFoundException {
        JarFile jar = new JarFile(file);
        for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements(); ) {
            JarEntry entry = enums.nextElement();
            String fileName;
            File f;
            if (entry.getName().endsWith(".class")) {
                fileName = destinationDir + File.separator + entry.getName();
                f = new File(fileName);
                //Adding to list of jar files
                jarFiles.add(f);
                if (!fileName.endsWith("/")) {
                    InputStream is = jar.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(f);
                    while (is.available() > 0) {
                        fos.write(is.read());
                    }
                    fos.close();
                    is.close();
                }
            }
        }
    }

    public PlugInMovement loadMovement(String opt) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(testing){ return new MoveOne(); }
        return (PlugInMovement) movePlugins.get(movePluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInWeapon loadWeapon(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(testing){ return new WeaponOne(); }
        return (PlugInWeapon) weaponPlugins.get(weaponPluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInGraphic loadGraphic(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(testing){ return new GraphicOne(); }
        return (PlugInGraphic) graphicPlugins.get(graphicPluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInCollision loadCollision(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(testing){ return new CollisionOne(); }
        return (PlugInCollision) collisionPlugins.get(collisionPluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInGUI1 loadGUI1(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (PlugInGUI1) gui1Plugins.get(gui1PluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }
}

