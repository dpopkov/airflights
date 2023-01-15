package com.gridnine.testing.filters;

import com.gridnine.testing.calculators.FlightDurationCalculatorOnGround;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Collection of static factory methods returning instances of flight filters.
 */
public class FlightFilters {

    private FlightFilters() {}

    public static FlightFilter departureBeforeNow() {
        return new FlightFilterDepartureBeforeInstant(LocalDateTime.now());
    }

    public static FlightFilter departureNowAndAfter() {
        return departureBeforeNow().negate();
    }

    public static FlightFilter haveReversedSegments() {
        return new FlightFilterBySegments(new SegmentFilterReversedDates());
    }

    public static FlightFilter doNotHaveReversedSegments() {
        return haveReversedSegments().negate();
    }

    public static FlightFilter groundTimeGreaterThan(Duration durationLimit) {
        return new FlightFilterByDurationLimit(new FlightDurationCalculatorOnGround(), durationLimit);
    }

    public static FlightFilter groundTimeLessThanOrEqual(Duration durationLimit) {
        return groundTimeGreaterThan(durationLimit).negate();
    }
}
