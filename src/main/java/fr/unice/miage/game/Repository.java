package fr.unice.miage.game;

import fr.unice.miage.classes.GraphicOne;
import fr.unice.miage.classes.MoveOne;
import fr.unice.miage.classes.WeaponOne;
import fr.unice.miage.plugins.PlugInGraphic;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.plugins.PlugInWeapon;

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
    private List<String> movePluginsNames= new ArrayList<>();
    private List<String> weaponPluginsNames = new ArrayList<>();
    private List<String> graphicPluginsNames = new ArrayList<>();
    private List<PlugInMovement> movePlugins = new ArrayList<>();
    private List<PlugInWeapon> weaponPlugins = new ArrayList<>();
    private List<PlugInGraphic> graphicPlugins = new ArrayList<>();

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

    public List<String> getMovePluginsNames(){
        return this.movePluginsNames;
    }
    public List<String> getWeaponPluginsNames(){
        return this.weaponPluginsNames;
    }
    public List<String> getGraphicPluginsNames(){
        return  this.graphicPluginsNames;
    }
    public List<PlugInMovement> getMovePlugins(){
        return this.movePlugins;
    }
    public List<PlugInWeapon> getWeaponPlugins(){
        return this.weaponPlugins;
    }
    public List<PlugInGraphic> getGraphicPlugins(){
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

                 switch (interfaceName){
                     case "PlugInMovement":
                         this.movePluginsNames.add(instance.getClass().getSimpleName());
                         this.movePlugins.add((PlugInMovement) instance);
                         break;
                     case "PlugInWeapon":
                         this.weaponPluginsNames.add(instance.getClass().getSimpleName());
                         this.weaponPlugins.add((PlugInWeapon) instance);
                         break;
                     case "PlugInGraphic":
                         this.graphicPluginsNames.add(instance.getClass().getSimpleName());
                         this.graphicPlugins.add((PlugInGraphic) instance);
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

    public Class loadMovement(String opt) throws ClassNotFoundException {
        PlugInMovement pluginMovement = new MoveOne();
        Class moveClass = pluginMovement.getClass();
        //return this.loader.loadClass(opt);
        return moveClass;

    }

   public Class loadWeapon(String opt){
       PlugInWeapon pluginWeapon = new WeaponOne() ;
       Class weaponClass = pluginWeapon.getClass();
       //return this.loader.loadClass(opt);
       return weaponClass;
    }

   public Class loadGraphic(String opt){
       PlugInGraphic pluginGraphic = new GraphicOne();
       Class graphicClass = pluginGraphic.getClass();
       //return this.loader.loadClass(opt);
       return graphicClass;
    }

    public static void main(String[] args) throws Exception {
        Repository repo = new Repository();
        repo.loadLibraries("plugins");

        //System.out.println(repo.getClassLoader().loadClass("MoveOne.class").getName());

    }
}
