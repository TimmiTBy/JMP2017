package com.epam.jmp.gcandmemorymanagement.gcdemo;

/**
 * Options: -Xmx10m -XX:SurvivorRatio=3 -Xss1m
 * Created by Ales on 19.06.2017.
 * http://www.petefreitag.com/articles/gctuning/
 */
public class GCDemo {

    public static void main(String[] args) {
        while(true) {
            new A();
        }
    }
}
