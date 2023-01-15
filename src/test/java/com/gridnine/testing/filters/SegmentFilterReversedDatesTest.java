package com.gridnine.testing.filters;

import com.gridnine.testing.Segment;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

class SegmentFilterReversedDatesTest {

    private final LocalDateTime today = LocalDateTime.now();
    private final LocalDateTime tomorrow = today.plusDays(1);
    private final SegmentFilterReversedDates filter = new SegmentFilterReversedDates();

    @Test
    void whenArrivalDateIsEarlierThanDepartureDate_thenReturnsTrue() {
        // Given
        Segment segment = Mockito.mock(Segment.class);
        given(segment.getDepartureDate()).willReturn(tomorrow);
        given(segment.getArrivalDate()).willReturn(today);
        // When
        boolean result = filter.test(segment);
        // Then
        assertTrue(result);
    }

    @Test
    void whenArrivalDateIsLaterThanDepartureDate_thenReturnsFalse() {
        // Given
        Segment segment = Mockito.mock(Segment.class);
        given(segment.getDepartureDate()).willReturn(today);
        given(segment.getArrivalDate()).willReturn(tomorrow);
        // When
        boolean result = filter.test(segment);
        // Then
        assertFalse(result);
    }
}
