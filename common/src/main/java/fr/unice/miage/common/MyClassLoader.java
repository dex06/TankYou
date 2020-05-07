package fr.unice.miage.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class MyClassLoader extends SecureClassLoader {
    private List<File> paths;


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
}

