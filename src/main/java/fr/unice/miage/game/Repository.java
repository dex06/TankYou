package fr.unice.miage.game;

import fr.unice.miage.plugins.PlugInGraphic;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.plugins.PlugInWeapon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javassist.NotFoundException;

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
    private List<Class> movePlugins = new ArrayList<>();
    private List<Class> weaponPlugins = new ArrayList<>();
    private List<Class> graphicPlugins = new ArrayList<>();

    private String packageName = "fr.unice.miage";
    private String appFolderName = "classes";
    private String destinationDir = "src/main/java/fr/unice/miage/classes";

    public Repository() throws Exception {
        this.loadLibraries("plugins");
    }

    public Repository(String base) throws Exception {
        this.loadLibraries(base);
    }

    public ObservableList<String> getMovePluginsNames() {
        return this.movePluginsNames;
    }

    public ObservableList<String> getWeaponPluginsNames() {
        return this.weaponPluginsNames;
    }

    public ObservableList<String> getGraphicPluginsNames() {
        return this.graphicPluginsNames;
    }

    public List<Class> getMovePlugins() {
        return this.movePlugins;
    }

    public List<Class> getWeaponPlugins() {
        return this.weaponPlugins;
    }

    public List<Class> getGraphicPlugins() {
        return this.graphicPlugins;
    }

    public void loadLibraries(String path) throws Exception {
        File libDir = new File(path);
        if (!libDir.exists() || !libDir.isDirectory()) {
            throw new RuntimeException("Invalid library directory");
        }
        for (File file : libDir.listFiles()) {
            if (!this.isJarFile(file)) {
                continue;
            }
            System.out.println("Found library format : " + file.getName());
            this.unzipJarFile(file);
            this.loadClassesFromJar(file);
        }
    }


    public void loadClassesFromJar(File pathToJar) throws IOException, ClassNotFoundException, NotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        JarFile jarFile = new JarFile(pathToJar);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = {new URL("jar:file:" + pathToJar + "!/")};
        URLClassLoader cl = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            System.out.println("loading jar classe : " + je.getName());
            String pack = this.packageName + "." + this.appFolderName + "." + je.getName().replace(".class", "");
            Class loadedClass = cl.loadClass(pack);
            Object instance = loadedClass.getDeclaredConstructor().newInstance();
            String interfaceName = instance.getClass().getInterfaces()[0].getSimpleName();

            switch (interfaceName) {
                case "PlugInMovement":
                    this.movePluginsNames.add(instance.getClass().getSimpleName());
                    this.movePlugins.add(loadedClass);
                    break;
                case "PlugInWeapon":
                    this.weaponPluginsNames.add(instance.getClass().getSimpleName());
                    this.weaponPlugins.add(loadedClass);
                    break;
                case "PlugInGraphic":
                    this.graphicPluginsNames.add(instance.getClass().getSimpleName());
                    this.graphicPlugins.add(loadedClass);
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
                this.jarFiles.add(f);
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
        return (PlugInMovement) this.movePlugins.get(this.movePluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInWeapon loadWeapon(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (PlugInWeapon) this.weaponPlugins.get(this.weaponPluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInGraphic loadGraphic(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return (PlugInGraphic) this.graphicPlugins.get(this.graphicPluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }
}

