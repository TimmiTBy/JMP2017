package com.epam.jmp.lambda.javastandardlibraryfunctionalinterfaces;

import com.epam.jmp.lambda.personpartoftask.Person;

import java.util.function.*;

/**
 * Created by Ales on 05.06.2017.
 */
public class InterfacesExamples {

    public static void main(String[] args) {

        Person pavel = new Person(47, "Pavel");

        //Supplier
        Supplier<Person> personSupplier = () -> new Person(27, "Ales");
        System.out.println(personSupplier.get());

        //Consumer
        Consumer<Person> maksim = (p) -> System.out.println("Hello, " + p.getName());
        maksim.accept(new Person(28, "Maksim"));

        //Function
        Function<Person, Integer> plusYear = p -> p.getAge() + 1;
        System.out.println(plusYear.apply(pavel));

        //BiFunction
        BiFunction<String, CharSequence, Boolean> checkContainsString = String::contains;
        System.out.println(checkContainsString.apply("Ales", "Al"));

        //Unary
        DoubleUnaryOperator abs = Math::abs;
        System.out.println(abs.applyAsDouble(-23.5));

        //Predicate
        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("ales"));
        System.out.println(predicate.negate().test("ales"));
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test(""));

    }
}
