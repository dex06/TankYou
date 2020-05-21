package fr.unice.miage.common;

import fr.unice.miage.common.plugins.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Repository {

    private final List<File> archiveFiles = new ArrayList<>();
    private final ObservableList<String> movePluginsNames = FXCollections.observableArrayList();
    private final ObservableList<String> weaponPluginsNames = FXCollections.observableArrayList();
    private final ObservableList<String> graphicPluginsNames = FXCollections.observableArrayList();
    private final ObservableList<String> collisionPluginsNames = FXCollections.observableArrayList();
    private final ObservableList<String> obstaclePluginsNames = FXCollections.observableArrayList();
    private final ObservableList<String> backgroundPluginsNames = FXCollections.observableArrayList();
    private final ObservableList<String> gui1PluginsNames = FXCollections.observableArrayList();
    private final ObservableList<String> gui2PluginsNames = FXCollections.observableArrayList();
    private final ObservableList<String> realPlayerPluginsNames = FXCollections.observableArrayList();
    private final List<Class> movePlugins = new ArrayList<>();
    private final List<Class> weaponPlugins = new ArrayList<>();
    private final List<Class> graphicPlugins = new ArrayList<>();
    private final List<Class> collisionPlugins = new ArrayList<>();
    private final List<Class> obstaclePlugins = new ArrayList<>();
    private final List<Class> backgroundPlugins = new ArrayList<>();
    private final List<Class> gui1Plugins = new ArrayList<>();
    private final List<Class> gui2Plugins = new ArrayList<>();
    private final List<Class> realPlayerPlugins = new ArrayList<>();

    private final String s = File.separator;
    private final String destinationDir = "plugins"+s+"src"+s+"main"+s+"java"+s+"fr"+"unice"+s+"miage"+s+"classes";

    private final boolean testing = Config.getTesting();

    public Repository() throws Exception { //this.loadLibraries("plugins repository");
         }

    public Repository(String base) throws Exception { this.loadLibraries(base); }

    public List<File> getJarFiles(){ return archiveFiles; }

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

    public ObservableList<String> getObstaclePluginsNames() { return obstaclePluginsNames;}

    public ObservableList<String> getBackgroundPluginsNames() { return backgroundPluginsNames;}

    public List<String> getGui1PluginsNames(){ return gui1PluginsNames; }

    public List<String> getGui2PluginsNames(){ return gui2PluginsNames; }

    public List<String> getRealPlayerPluginsNames(){ return realPlayerPluginsNames; }


    public void loadLibraries(String path) throws Exception {
        File libDir = new File(path);
        if(isJarFile(libDir)) archiveFiles.add(libDir);
        else if (!libDir.exists() || !libDir.isDirectory()) {
            throw new RuntimeException("Invalid library directory");
        }
        if(libDir.isDirectory()) {
            for (File file : libDir.listFiles()) {
                if (!isJarFile(file) & !isZipFile(file)) {
                    continue;
                }
                System.out.println("Found library format : " + file.getName());
                archiveFiles.add(file);
            }
        }
        loadClassesFromArchiveFiles();
    }


    public void loadClassesFromArchiveFiles() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        MyClassLoader cl = new MyClassLoader(archiveFiles);
        for(File archive : archiveFiles) {

            if(isZipFile(archive)){
                ZipFile zipFile = new ZipFile(archive);
                Enumeration<ZipEntry> zipEntries = (Enumeration<ZipEntry>) zipFile.entries();
                while (zipEntries.hasMoreElements()) {
                    ZipEntry je = zipEntries.nextElement();
                    if (je.isDirectory() || !je.getName().endsWith(".class") || je.getName().contains("module-info.class")) {
                        continue;
                    }
                    System.out.println("Loading jar class : " + je.getName());
                    String pack = je.getName().replace("/", ".").replace(".class", "");
                    loadClassFromPath(cl, pack);
                }
            }
            else {
                JarFile jarFile = new JarFile(archive);
                Enumeration<JarEntry> jarEntries = jarFile.entries();
                while (jarEntries.hasMoreElements()) {
                    JarEntry je = jarEntries.nextElement();
                    if (je.isDirectory() || !je.getName().endsWith(".class") || je.getName().contains("module-info.class")) {
                        continue;
                    }
                    System.out.println("Loading jar class : " + je.getRealName());
                    String pack = je.getName().replace("/", ".").replace(".class", "");
                    loadClassFromPath(cl, pack);
                }
            }

        }
    }

    private void loadClassFromPath(MyClassLoader cl, String path) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class loadedClass = cl.loadClass(path);

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
            case "PlugInObstacle":
                obstaclePluginsNames.add(instance.getClass().getSimpleName());
                obstaclePlugins.add(loadedClass);
                break;
            case "PlugInBackground":
                backgroundPluginsNames.add(instance.getClass().getSimpleName());
                backgroundPlugins.add(loadedClass);
                break;
            case "PlugInGUI1":
                gui1PluginsNames.add(instance.getClass().getSimpleName());
                gui1Plugins.add(loadedClass);
                break;
            case "PlugInGUI2":
                gui2PluginsNames.add(instance.getClass().getSimpleName());
                gui2Plugins.add(loadedClass);
                break;
            case "PlugInRealPlayer":
                realPlayerPluginsNames.add(instance.getClass().getSimpleName());
                realPlayerPlugins.add(loadedClass);
                break;

        }
    }
    private boolean isJarFile(File file) {
        return file.getName().endsWith(".jar");
    }

    private boolean isZipFile(File file){
        return file.getName().endsWith(".zip");
    }


    public PlugInMovement loadMovement(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //if(testing){ return new MoveOne(); }
        return (PlugInMovement) movePlugins.get(movePluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInWeapon loadWeapon(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //if(testing){ return new WeaponOne(); }
        return (PlugInWeapon) weaponPlugins.get(weaponPluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInGraphic loadGraphic(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //if(testing){ return new GraphicOne(); }
        return (PlugInGraphic) graphicPlugins.get(graphicPluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInCollision loadCollision(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //if(testing){ return new CollisionOne(); }
        return (PlugInCollision) collisionPlugins.get(collisionPluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInObstacle loadObstacle(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //if(testing){ return new ObstacleOne(); }
        return (PlugInObstacle) obstaclePlugins.get(obstaclePluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInBackground loadBackground(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //if(testing){ return new BackgroundOne(); }
        return (PlugInBackground) backgroundPlugins.get(backgroundPluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInGUI1 loadGUI1(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //if(testing){ return new BarOne(); }
        return (PlugInGUI1) gui1Plugins.get(gui1PluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInGUI2 loadGUI2(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //if(testing){ return new StatsOne(); }
        return (PlugInGUI2) gui2Plugins.get(gui2PluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }

    public PlugInRealPlayer loadRealPlayer(String opt) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //if(testing){ return new HumanOne(); }
        return (PlugInRealPlayer) realPlayerPlugins.get(realPlayerPluginsNames.indexOf(opt)).getDeclaredConstructor().newInstance();
    }


    public static void copyJarFile(JarFile jarFile, File destDir) throws IOException {
        String fileName = jarFile.getName();
        String fileNameLastPart = fileName.substring(fileName.lastIndexOf(File.separator));
        if(!destDir.exists()) destDir.mkdir();
        File destFile = new File(destDir, fileNameLastPart);

        JarOutputStream jos = new JarOutputStream(new FileOutputStream(destFile));
        Enumeration<JarEntry> entries = jarFile.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            InputStream is = jarFile.getInputStream(entry);
            jos.putNextEntry(new JarEntry(entry.getName()));
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                jos.write(buffer, 0, bytesRead);
            }
            is.close();
            jos.flush();
            jos.closeEntry();
        }
        jos.close();
    }

    private void unzipJarFile(File file) throws IOException {
        JarFile jar = new JarFile(file);
        for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements(); ) {
            JarEntry entry = enums.nextElement();
            String fileName;
            File f;
            if (entry.getName().endsWith(".class")) {
                fileName = destinationDir + File.separator + entry.getName();
                f = new File(fileName);
                //Adding to list of jar files
                //archiveFiles.add(f);
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


}


