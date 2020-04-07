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

    private String destinationDir = "src/main/java/fr/unice/miage/classes";
    //ClassLoader loader = new URLClassLoader(new URL[]{new File("target/classes/fr/unice/miage/classes").toURL()});
    //URL[] urls = this.loader.loadClass()
    public Repository() throws Exception {
        this.loadLibraries("plugins");

    }
    public Repository(String base) throws Exception {
        //this.loader = new URLClassLoader(new URL[] {base.toURL()});
        this.loadLibraries(base);
    }

    public Class<?> load(String opt) throws ClassNotFoundException {
        return this.loader.loadClass(opt);
    }
     public void loadLibraries(String path) throws Exception {
        File libDir = new File(path);
        if(!libDir.exists() || !libDir.isDirectory()){
            throw new RuntimeException("Invalid library directory");
        }
        List<URL> listURLs = new ArrayList<>();
        for(File file : libDir.listFiles()){
            if(!this.isJarFile(file)){
                continue;
            }
            System.out.println("Found library format : " + file.getName());
            this.unzipJarFile(file);
            //listURLs.add(new URL("jar:file:" + file.getCanonicalPath() + "!/"));
            this.addURLToClassPath(new URL("jar:file:" + file.getCanonicalPath() + "!/"));

        }
        //URL[] urls = (URL[]) listURLs.toArray();
        //this.loader = new URLClassLoader(urls);
     }

     public void unzipJarFile(File file) throws IOException {
         JarFile jar = new JarFile(file);
         for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();) {
             JarEntry entry = (JarEntry) enums.nextElement();
             System.out.println(entry.getName());
             String fileName = this.destinationDir + File.separator + entry.getName();
             File f = new File(fileName);

             if (fileName.endsWith("/")) {
                 f.mkdirs();
             }
         }

         for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();) {
             JarEntry entry = enums.nextElement();

             String fileName = destinationDir + File.separator + entry.getName();
             File f = new File(fileName);
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


     public ClassLoader getClassLoader(){
        return this.loader;
     }

     public void addURLToClassPath(URL url){
         this.loader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
         Thread.currentThread().setContextClassLoader(this.loader);
     }

     public boolean isJarFile(File file) {
        return file.getName().endsWith(".jar");
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
