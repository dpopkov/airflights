package com.gridnine.testing.calculators;

import com.gridnine.testing.Flight;

import java.time.Duration;

@FunctionalInterface
public interface FlightDurationCalculator {

    Duration calculateFor(Flight flight);
}
