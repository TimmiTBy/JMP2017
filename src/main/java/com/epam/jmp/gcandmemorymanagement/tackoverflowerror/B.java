package com.epam.jmp.gcandmemorymanagement.tackoverflowerror;

/**
 * Created by Ales on 19.06.2017.
 */
public class B {

    private A a = null;

    public B() {
        System.out.println("B creating");
        a = new A();
    }

}

