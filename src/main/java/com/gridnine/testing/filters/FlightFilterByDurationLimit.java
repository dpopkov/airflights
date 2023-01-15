package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import com.gridnine.testing.calculators.FlightDurationCalculator;

import java.time.Duration;

/**
 * Selects flights which have calculated duration exceeding specified duration limit.
 */
public class FlightFilterByDurationLimit extends AbstractFlightFilter {

    private final FlightDurationCalculator flightDurationCalculator;
    private final Duration durationLimit;

    public FlightFilterByDurationLimit(FlightDurationCalculator flightDurationCalculator, Duration durationLimit) {
        this.flightDurationCalculator = flightDurationCalculator;
        this.durationLimit = durationLimit;
    }

    @Override
    public boolean test(Flight flight) {
        validateSegments(flight);
        Duration calculated = flightDurationCalculator.calculateFor(flight);
        return calculated.compareTo(durationLimit) > 0;
    }
}
