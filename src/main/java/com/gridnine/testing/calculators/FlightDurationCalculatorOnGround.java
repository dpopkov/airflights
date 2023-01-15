package com.gridnine.testing.calculators;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Calculates total time spent on the ground.
 */
public class FlightDurationCalculatorOnGround implements FlightDurationCalculator {
    @Override
    public Duration calculateFor(Flight flight) {
        Duration total = Duration.ZERO;
        List<Segment> segments = flight.getSegments();
        for (int i = 1; i < segments.size(); i++) {
            LocalDateTime arrival = segments.get(i - 1).getArrivalDate();
            LocalDateTime departure = segments.get(i).getDepartureDate();
            total = total.plus(Duration.between(arrival, departure));
        }
        return total;
    }
}
