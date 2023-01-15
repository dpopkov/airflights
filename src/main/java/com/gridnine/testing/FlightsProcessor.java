package com.gridnine.testing;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Processes lists of flights in a unified way.
 */
public class FlightsProcessor {

    private final List<Flight> flights;
    private final Consumer<Flight> consumer;
    private final boolean printInfo;

    public FlightsProcessor(List<Flight> flights, Consumer<Flight> consumer, boolean printInfo) {
        this.flights = flights;
        this.consumer = consumer;
        this.printInfo = printInfo;
    }

    public void runWithFilter(Predicate<Flight> flightFilter, String message) {
        if (printInfo) {
            System.out.println();
            System.out.println(message);
        }
        flights.stream()
                .filter(flightFilter)
                .forEach(consumer);
    }
}
