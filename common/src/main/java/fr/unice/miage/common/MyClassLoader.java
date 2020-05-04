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
    private List<File> paths = null;

//	private ArrayList<File> fileInPath = new ArrayList<>();

    public MyClassLoader(List<File> p) {
        this.paths = p;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        if (b == null || b.length == 0) {
            throw new ClassNotFoundException();
        }
        System.out.println("Chargement de la classe... si pas erreur alors ok");
        return super.defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) throws ClassNotFoundException {
        for (int j = 0; j < paths.size(); j++) {
            File f= paths.get(j);
            if(f.isDirectory()) {
                String namePath = name.replaceAll("\\.", "\\\\") + ".class";
                Path path = Paths.get(f.getAbsolutePath(), namePath);
                byte[] b;
                try {
                    b = Files.readAllBytes(path);
                    System.out.println("Classe en bytes");
                    return b;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    String localName = name.replaceAll("\\.", "/");
                    localName += ".class";
                    JarFile jarFile = new JarFile(paths.get(j));
                    Enumeration<JarEntry> e = jarFile.entries();
                    while (e.hasMoreElements()) {
                        JarEntry je = e.nextElement();
                        String loadName = je.toString();
                        if (loadName.equals(localName)) {
                            System.out.println(loadName + " " + name + " " + localName);
                            InputStream is = jarFile.getInputStream(je);
                            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                            int nextValue = is.read();
                            while (-1 != nextValue) {
                                byteStream.write(nextValue);
                                nextValue = is.read();
                            }
                            byte[] b = byteStream.toByteArray();
                            System.out.println("JAR ou ZIP en bytes");
                            return b;
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

//	private void getAllFiles(File f) {
//		if(f.isFile()) {
//			this.fileInPath.add(f);
//		} else {
//			for(File f2 : f.listFiles()) {
//				this.getAllFiles(f2);
//			}
//		}
//	}


//	private static byte[] readContentIntoByteArray(File file)
//	{
//		FileInputStream fileInputStream = null;
//		byte[] bFile = new byte[(int) file.length()];
//		try
//		{
//			//convert file into array of bytes
//			fileInputStream = new FileInputStream(file);
//			fileInputStream.read(bFile);
//			fileInputStream.close();
//			for (int i = 0; i < bFile.length; i++)
//			{
//				System.out.print((char) bFile[i]);
//			}
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return bFile;
//	}
}
