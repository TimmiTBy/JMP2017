package com.epam.jmp.classloading;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Ales on 23.02.2017.
 * Custom class loader, using for dymanic loading classes from .jar
 */

public class CustomJarClassLoader extends ClassLoader {

    final static Logger logger = Logger.getLogger(CustomJarClassLoader.class);

    //Cache
    private HashMap<String, Class<?>> cache = new HashMap<String, Class<?>>();

    private String jarFileName;
    private String packageName;

    public CustomJarClassLoader(String jarFileName, String packageName) {
        this.jarFileName = jarFileName;
        this.packageName = packageName;
        cacheClasses();
    }

    /**
     * Cache Classes from JAR file
     */
    private void cacheClasses() {
        try {
            JarFile jarFile = new JarFile(jarFileName);
            Enumeration entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry) entries.nextElement();
                if (isValidLoadedClass(normalizeName(jarEntry.getName()), packageName)) {
                    byte[] classData = loadClassData(jarFile, jarEntry);
                    if (classData != null) {
                        Class<?> clazz = defineClass(normalizeName(cutClassExtension(jarEntry.getName())), classData, 0, classData.length);
                        cache.put(clazz.getName(), clazz);
                        logger.debug(clazz.getName() + " class loaded in cache from jar");
                    }
                } else {
                    logger.debug("No valid classes were loaded");
                }

            }

        } catch (IOException ee) {
            logger.error("No jar file found");
        }

    }

    /**
     * Loading Class from cache according class name
     *
     * @param name
     * @return Class according class name
     * @throws ClassNotFoundException
     */
    @Override
    public synchronized Class<?> loadClass(String name) throws ClassNotFoundException {

        Class<?> result = cache.get(name);
        logger.debug(name + " Class was loaded from cahce to memory");
        if (result == null) {
            result = cache.get(packageName + "." + name);
        }
        if (result == null) {
            result = super.findSystemClass(name);
            logger.debug(name + " Class was loaded to memory");
        }

        return result;
    }

    /**
     * Load class as byte array
     *
     * @param jarFile
     * @param jarEntry
     * @return byte array
     * @throws IOException
     */
    private byte[] loadClassData(JarFile jarFile, JarEntry jarEntry) throws IOException {

        long size = jarEntry.getSize();
        if (size == -1 || size == 0) {
            return null;
        }
        byte[] data = new byte[(int) size];
        InputStream in = jarFile.getInputStream(jarEntry);
        in.read(data);
        return data;
    }

    /**
     * Replce "/" symbol to "." for java
     *
     * @param className
     * @return valid java class adress
     */
    private String normalizeName(String className) {
        return className.replace('/', '.');
    }

    /**
     * Cuting in loading class ".class" extension e.g. com.epam.classloading.CustomClass.class
     * result will be com.epam.classloading.CustomClass
     *
     * @param className
     * @return Full classPath
     */
    private String cutClassExtension(String className) {
        return className.substring(0, className.length() - 6);
    }

    /**
     * Check current file is it a class file and is its packege valid
     *
     * @param className
     * @param packageName
     * @return true or false
     */
    private boolean isValidLoadedClass(String className, String packageName) {
        return className.startsWith(packageName) && className.endsWith(".class");
    }
}
