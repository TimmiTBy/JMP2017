package com.epam.jmp.gcandmemorymanagement.tackoverflowerror;

/**
 * Created by Ales on 19.06.2017.
 */
public class A {

    private B b = null;

    public A() {
        System.out.println("A creating");
        b = new B();
    }
}
