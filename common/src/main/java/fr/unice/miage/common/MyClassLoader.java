package fr.unice.miage.common;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class MyClassLoader extends SecureClassLoader {
    private final List<File> paths;


    public MyClassLoader(List<File> p) {
        super(Thread.currentThread().getContextClassLoader());
        this.paths = p;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] b = loadClassData(name);
            if (b == null || b.length == 0) {
                throw new ClassNotFoundException();
            }
            return super.defineClass(name, b, 0, b.length);
        } catch (ClassNotFoundException e){
            System.out.println("Parent classloader");
            return this.loadClass(name);
        }
    }

    private byte[] loadClassData(String name) {
        for (File f : paths) {
            if (f.isDirectory()) {
                String namePath = name.replaceAll("\\.", "\\\\") + ".class";
                Path path = Paths.get(f.getAbsolutePath(), namePath);
                byte[] b;
                try {
                    b = Files.readAllBytes(path);
                    return b;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    String localName = name.replaceAll("\\.", "/");
                    localName += ".class";
                    JarFile jarFile = new JarFile(f);
                    Enumeration<JarEntry> e = jarFile.entries();
                    while (e.hasMoreElements()) {
                        JarEntry je = e.nextElement();
                        String loadName = je.toString();
                        if (loadName.equals(localName)) {
                            InputStream is = jarFile.getInputStream(je);
                            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                            int nextValue = is.read();
                            while (-1 != nextValue) {
                                byteStream.write(nextValue);
                                nextValue = is.read();
                            }
                            return byteStream.toByteArray();
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        try {
            InputStream is = loadImage(name);
            return is;
        } catch (Exception e) {
            return this.getParent().getResourceAsStream(name);
        }
    }


    public InputStream loadImage(String name){
        for (File f : paths) {
            if (f.isDirectory()) {
                Path path = Paths.get(f.getAbsolutePath(), name);
                try {
                    File file = path.toFile();
                    System.out.println(file);
                    return new FileInputStream(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    String localName = name.replaceAll("\\.", "/");
                    JarFile jarFile = new JarFile(f);
                    Enumeration<JarEntry> e = jarFile.entries();
                    while (e.hasMoreElements()) {
                        JarEntry je = e.nextElement();
                        String loadName = "/" + je.toString();
                        if (loadName.endsWith(".png") || loadName.endsWith(".jpg")) {
                            if(loadName.equals(name))  return jarFile.getInputStream(je);
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.println("loader img failing");
                }
            }
        }
        return null;
    }
}

