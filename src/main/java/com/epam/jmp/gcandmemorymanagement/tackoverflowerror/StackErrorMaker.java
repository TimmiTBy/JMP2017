package com.epam.jmp.gcandmemorymanagement.tackoverflowerror;

/**
 * This class will generate java.lang.StackOverflowError without recursion
 * Option: -Xss1m
 * Created by Ales on 19.06.2017.
 */
public class StackErrorMaker {

    public static void main(String[] args) {
        A obj = new A();
    }
}
