package com.epam.jmp.gcandmemorymanagement;

import com.epam.jmp.classloading.CustomJarClassLoader;

import java.util.ArrayList;

/**
 * Using the same ClassLoader as in first task we will get java.lang.OutOfMemoryError: MetaSpace
 * Option: -XX:MaxMetaspaceSize=10M
 * Created by Ales on 19.06.2017.
 */
public class MetaSpaceErrorMaker {
    private static final String FILE_PATH = "src/main/resources/Custom-jar.jar";
    private static final String PACKEGE_NAME = "com.epam.classloading";

    public static void createMetaSpaceError() {
        ArrayList arrayList = new ArrayList();
        while (true) {
            CustomJarClassLoader customJarClassLoader = new CustomJarClassLoader(FILE_PATH, PACKEGE_NAME);
            try {
                Class<?> loadedClass = customJarClassLoader.loadClass("CustomJarClass");
                arrayList.add(loadedClass);
            } catch (ClassNotFoundException e) {

            }
        }

    }

    public static void main(String[] args) {
        createMetaSpaceError();
    }

}
