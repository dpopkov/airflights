package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.util.List;

/**
 * This abstract class should be used as a base class for flight filters.
 * It contains supporting method(s) for validating tested flights.
 */
public abstract class AbstractFlightFilter implements FlightFilter {

    protected void validateSegments(Flight flight) {
        List<Segment> segments = flight.getSegments();
        if (segments == null || segments.isEmpty()) {
            throw new IllegalArgumentException("This flight has no segments");
        }
    }
}
