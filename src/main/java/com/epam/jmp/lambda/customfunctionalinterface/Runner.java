package com.epam.jmp.lambda.customfunctionalinterface;

import com.epam.jmp.lambda.personpartoftask.Person;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by Ales on 05.06.2017.
 */
public class Runner {

    public static void main(String[] args) {
        Demonstrator<Person> demonstrator = new Demonstrator() {
            @Override
            public Person show() {
                return new Person(27, "Ales");
            }
        };

        Demonstrator<Person> personSupplier = () -> new Person(27, "Ales");
        System.out.println(personSupplier.show());

        //static method
        Demonstrator.displayStatic();

        //default
        Demonstrator<Person> personSupplier1 = () -> new Person(27, "Ales");
        personSupplier1.displayDafault();
    }
}
