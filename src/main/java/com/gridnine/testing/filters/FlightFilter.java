package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;

import java.util.function.Predicate;

@FunctionalInterface
public interface FlightFilter extends Predicate<Flight> {

    @Override
    default FlightFilter negate() {
        return (t) -> !test(t);
    }
}
