package fr.unice.miage.common;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class MyClassLoader extends SecureClassLoader {

    private ArrayList<File> path = null;

    public MyClassLoader(ArrayList<File> p) {
        this.path = p;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        //System.out.println(b);
        return super.defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {
        String relativePath = name.replace(".", File.separator).concat(".class");
        for(File file : path)
            if (file.isDirectory()) {
                File classFile = new File(file, relativePath);
                if (classFile.exists()) {
                    try {
                        System.out.println(classFile);
                        return Files.readAllBytes(classFile.toPath());
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                } else {
                    try (ZipFile zipFile = new ZipFile(file)) {
                        ZipEntry zipE = zipFile.getEntry(relativePath);
                        if(zipE != null) {
                            try {
                                InputStream inStr = zipFile.getInputStream(zipE);
                                return inStr.readAllBytes();
                            } catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    } catch (ZipException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ArrayList<File> al = new ArrayList<File>();
        //File new_file = new File("./test.zip");
        //System.out.println(new File(".").getPath());
        //al.add(new File("./src/main/java/prog_adv/Main"));
        al.add(new File("./test.zip"));
        //al.add(new File("./test.jar"));
        MyClassLoader myCL = new MyClassLoader(al);
        myCL.loadClass("test");
    }


}