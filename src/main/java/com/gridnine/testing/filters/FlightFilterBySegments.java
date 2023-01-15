package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.util.List;

/**
 * Selects flights that have segments selected by a specified segment filter.
 */
class FlightFilterBySegments extends AbstractFlightFilter {

    private final SegmentFilter segmentFilter;

    public FlightFilterBySegments(SegmentFilter segmentFilter) {
        this.segmentFilter = segmentFilter;
    }

    @Override
    public boolean test(Flight flight) {
        validateSegments(flight);
        List<Segment> segments = flight.getSegments();
        for (Segment segment : segments) {
            if (segmentFilter.test(segment)) {
                return true;
            }
        }
        return false;
    }
}
