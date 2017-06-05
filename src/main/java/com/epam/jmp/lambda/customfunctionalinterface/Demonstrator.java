package com.epam.jmp.lambda.customfunctionalinterface;

/**
 * Created by Ales on 05.06.2017.
 */
@FunctionalInterface
public interface Demonstrator<T> {

    T show();

    default void displayDafault() {
        System.out.println("I can see deafult method");
    }

    static  void displayStatic() {
        System.out.println("I can see static method");
    }
}
