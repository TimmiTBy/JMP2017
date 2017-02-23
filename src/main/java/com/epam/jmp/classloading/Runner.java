package com.epam.jmp.classloading;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Application Runner
 * Created by Ales on 23.02.2017.
 */
public class Runner {

    final static Logger logger = Logger.getLogger(Runner.class);

    private static final String FILE_PATH = "src/main/resources/custom-jar.jar";
    private static final String PACKEGE_NAME = "com.epam.classloading";
    private static final String METHOD_NAME = "demo";

    public static void main(String[] args) {
        startConsoleApplication();
    }

    /**
     * Method to start application with custom classloader
     */
    private static void startConsoleApplication() {
        CustomJarClassLoader customJarClassLoader = null;
        Class<?> loadedClass = null;
        int choise;
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        logger.debug("Application options: \n 1 - Create custom classloder and load new class to cahce \n "
                        + "2 - Load class to memory \n 3 - Use functionality of loaded class \n Press any other key to finish");
        while (flag) {
            try {
                choise = scanner.nextInt();
                switch (choise) {
                    case 1:
                        customJarClassLoader = new CustomJarClassLoader(FILE_PATH, PACKEGE_NAME);
                        break;
                    case 2:
                        try {
                            loadedClass = customJarClassLoader.loadClass("CustomJarClass");
                        } catch (ClassNotFoundException e) {
                            logger.error("Error description:", e);
                        }
                        break;
                    case 3:
                        try {
                            Object object = loadedClass.newInstance();
                            loadedClass.getMethod(METHOD_NAME, null).invoke(object);
                        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                            logger.error("Error description:", e);
                        }
                        break;
                    default:
                        flag = false;
                }
            } catch (InputMismatchException e) { //catch exception, thrown by scanner
                flag = false;
            }
        }
    }
}
