package com.epam.jmp.gcandmemorymanagement;

/**
 * This method will generate Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * Option -Xmx256m
 * Created by Ales on 19.06.2017.
 */
public class HeapErrorMaker {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("aaaaaaaa");
        while (true) {
            stringBuffer.append("aaaaaaaaaa");
        }
    }
}
