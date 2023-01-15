package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

class FlightFilterBySegmentsTest {

    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = mock(Flight.class);
        given(this.flight.getSegments()).willReturn(List.of(mock(Segment.class), mock(Segment.class)));
    }

    @Test
    void whenSegmentFilterReturnsTrueAtLeastOnce_thenFlightFilterReturnsTrue() {
        // Given
        SegmentFilter segmentFilter = mock(SegmentFilter.class);
        given(segmentFilter.test(any(Segment.class))).willReturn(false, true);
        // When
        FlightFilterBySegments filter = new FlightFilterBySegments(segmentFilter);
        boolean result = filter.test(flight);
        // Then
        assertTrue(result);
    }

    @Test
    void whenSegmentFilterReturnsOnlyFalse_thenFlightFilterReturnsFalse() {
        // Given
        SegmentFilter segmentFilter = mock(SegmentFilter.class);
        given(segmentFilter.test(any(Segment.class))).willReturn(false, false);
        // When
        FlightFilterBySegments filter = new FlightFilterBySegments(segmentFilter);
        boolean result = filter.test(flight);
        // Then
        assertFalse(result);
    }
}
