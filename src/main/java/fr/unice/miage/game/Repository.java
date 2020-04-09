package fr.unice.miage.game;

import fr.unice.miage.plugins.PlugInGraphic;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.plugins.PlugInWeapon;
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
    private ClassLoader loader;
    private List<File> jarFiles = new ArrayList<>();
    private ObservableList<String> movePluginsNames= FXCollections.observableArrayList();
    private ObservableList<String> weaponPluginsNames = FXCollections.observableArrayList();
    private ObservableList<String> graphicPluginsNames = FXCollections.observableArrayList();
    private List<Class> movePlugins = new ArrayList<>();
    private List<Class> weaponPlugins = new ArrayList<>();
    private List<Class> graphicPlugins = new ArrayList<>();

    private String packageName = "fr.unice.miage";
    private String appFolderName = "classes";
    private String destinationDir = "src/main/java/fr/unice/miage/classes";
    //ClassLoader loader = new URLClassLoader(new URL[]{new File("target/classes/fr/unice/miage/classes").toURL()});

    public Repository() throws Exception {
        this.loadLibraries("plugins");

    }
    public Repository(String base) throws Exception {
        //this.loader = new URLClassLoader(new URL[] {base.toURL()});
        this.loadLibraries(base);
    }

    public ObservableList<String> getMovePluginsNames(){
        return this.movePluginsNames;
    }
    public ObservableList<String> getWeaponPluginsNames(){
        return this.weaponPluginsNames;
    }
    public ObservableList<String> getGraphicPluginsNames(){
        return  this.graphicPluginsNames;
    }
    public List<Class> getMovePlugins(){
        return this.movePlugins;
    }
    public List<Class> getWeaponPlugins(){
        return this.weaponPlugins;
    }
    public List<Class> getGraphicPlugins(){
        return this.graphicPlugins;
    }

    public Class<?> load(String opt) throws ClassNotFoundException {
        return this.loader.loadClass(opt);
    }
     public void loadLibraries(String path) throws Exception {
        File libDir = new File(path);
        if(!libDir.exists() || !libDir.isDirectory()){
            throw new RuntimeException("Invalid library directory");
        }
        for(File file : libDir.listFiles()){
            if(!this.isJarFile(file)){
                continue;
            }
            System.out.println("Found library format : " + file.getName());
            this.unzipJarFile(file);
            this.addURLToClassPath(new URL("jar:file:" + file.getCanonicalPath() + "!/"));

        }
        this.getPluginsNames();
     }

     private void getPluginsNames() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
         for (File file : this.jarFiles) {
             if (file.isDirectory()) {
                 File classFile = new File(file, file.getCanonicalPath());
                 if (classFile.exists()) {
                     Class loadedClass = this.loadClass(file);
                 }
             } else {
                 Class loadedClass = this.loadClass(file);
                 Object instance = loadedClass.getDeclaredConstructor().newInstance();
                 String interfaceName = instance.getClass().getInterfaces()[0].getSimpleName();
                 System.out.println(loadedClass);

                 switch (interfaceName){
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

                 this.jarFiles.add(f);

                 if (!fileName.endsWith("/")) {
                     InputStream is = jar.getInputStream(entry);
                     FileOutputStream fos = new FileOutputStream(f);

                     // write contents of 'is' to 'fos'
                     while (is.available() > 0) {
                         fos.write(is.read());
                     }
                     fos.close();
                     is.close();
                 }
             }
         }
     }


     public ClassLoader getClassLoader(){
        return this.loader;
     }

     private void addURLToClassPath(URL url){
         this.loader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
         Thread.currentThread().setContextClassLoader(this.loader);
     }

     private boolean isJarFile(File file) {
        return file.getName().endsWith(".jar");
     }
    private Class loadClass(File file) throws ClassNotFoundException {
        String pack = this.packageName + "." + this.appFolderName + "." + file.getName().replace(".class", "");
        Class classFile = Class.forName(pack, true, Thread.currentThread().getContextClassLoader());
        return classFile;
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

    public static void main(String[] args) throws Exception {
        Repository repo = new Repository();
        repo.loadLibraries("plugins");

        //System.out.println(repo.getClassLoader().loadClass("MoveOne.class").getName());

    }
}
