package com.gridnine.testing.filters;

import com.gridnine.testing.Segment;

import java.time.LocalDateTime;

/**
 * Selects segments that have reversed date, that is arrival date is earlier than departure date.
 */
class SegmentFilterReversedDates implements SegmentFilter {
    @Override
    public boolean test(Segment segment) {
        LocalDateTime departure = segment.getDepartureDate();
        LocalDateTime arrival = segment.getArrivalDate();
        return arrival.isBefore(departure);
    }
}
