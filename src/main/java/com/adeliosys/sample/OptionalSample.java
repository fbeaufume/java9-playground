package com.adeliosys.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class OptionalSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionalSample.class);

    public static void main(String[] args) {
        streamSample();
    }

    private static void orSample() {
        Optional<String> optional = getName();

        // Java 8, terminal operation
        String value1 = optional.orElse("Tom"); // Java 8

        // Java 8, terminal operation
        String value2 = optional.orElseGet(() -> "Tom"); //Java 8

        // Java 9, non terminal
        Optional<String> optional2 = optional.or(() -> Optional.of("Tom"));
    }

    private static void ifPresentOrElseSample() {
        Optional<String> optional = getName();

        // Java 8
        optional.ifPresent(System.out::println);

        // Java 9
        optional.ifPresentOrElse(
                name -> System.out.println("Hello " + name + "!"),
                () -> System.out.println(("Who are you?"))
        );
    }

    private static Optional<String> getName() {
        return Optional.empty();
    }

    private static void streamSample() {
        List<Optional<String>> list = List.of();
        //List<Optional<String>> list = List.of(Optional.empty(), Optional.of("foo"), Optional.of("bar"));

        // Get the first non empty element in Java 8
        String s1 = list.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst().orElse("default");

        // Alternative with flatMap, still in Java 8
        String s2 = list.stream()
                .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                .findFirst().orElse("default");

        // Another alternative with flatMap, still in Java 8
        String s3 = list.stream()
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .findFirst().orElse("default");

        // In Java 9
        String s4 = list.stream()
                .flatMap(Optional::stream)
                .findFirst().orElse("default");

        LOGGER.info("s1: {}", s1);
        LOGGER.info("s2: {}", s2);
        LOGGER.info("s3: {}", s3);
        LOGGER.info("s4: {}", s4);
    }
}
