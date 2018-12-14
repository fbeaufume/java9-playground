package com.adeliosys.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionSample.class);

    public static void main(String[] args) {

        Set<String> set = Set.of("foo", "bar");

        List<Integer> list = List.of(12, 15, 21);

        Map<Integer, String> map = Map.of(1, "One", 2, "Two");

        LOGGER.info("set: {}", set);
        LOGGER.info("list: {}", list);
        LOGGER.info("map: {}", map);
    }
}
