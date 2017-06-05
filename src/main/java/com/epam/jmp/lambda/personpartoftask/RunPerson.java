package com.epam.jmp.lambda.personpartoftask;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by Ales on 05.06.2017.
 */
public class RunPerson {

    public static void main(String[] args) {

        Person nastia = new Person(57, "Nastia");
        Person ales = new Person(87, "Ales");
        Person pavel = new Person(47, "Pavel");

        Person[] arrayOfPersons = {nastia, ales, pavel};
        // Name sort
        Stream<Person> streamName = Stream.of(arrayOfPersons);
        streamName.sorted(Comparator.comparing(e -> e.getName()))
                .forEach(System.out::println);

        // Age Sort Reverse
        Stream<Person> streamAge = Stream.of(arrayOfPersons);
        streamAge.sorted(Comparator.comparing(Person::getAge).reversed())
                .forEach(System.out::println);
    }
}
