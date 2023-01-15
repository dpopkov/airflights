package com.gridnine.testing.calculators;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class FlightDurationCalculatorOnGroundTest {

    @Test
    void canCalculateDuration() {
        // Given
        Flight flight = mock(Flight.class);
        Segment s1 = mock(Segment.class);
        Segment s2 = mock(Segment.class);
        Segment s3 = mock(Segment.class);
        LocalDateTime now = LocalDateTime.now();
        given(s1.getArrivalDate()).willReturn(now);
        given(s2.getDepartureDate()).willReturn(now.plusHours(1));
        given(s2.getArrivalDate()).willReturn(now.plusHours(2));
        given(s3.getDepartureDate()).willReturn(now.plusHours(5));
        given(flight.getSegments()).willReturn(List.of(s1, s2, s3));
        // When
        var calculator = new FlightDurationCalculatorOnGround();
        Duration total = calculator.calculateFor(flight);
        // Then
        assertEquals(Duration.ofHours(4), total);
    }
}
