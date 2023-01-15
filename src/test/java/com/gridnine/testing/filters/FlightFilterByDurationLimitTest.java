package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import com.gridnine.testing.calculators.FlightDurationCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class FlightFilterByDurationLimitTest {

    private final Duration threeHours = Duration.ofHours(3);
    private Flight flight;
    private FlightDurationCalculator calculator;

    @BeforeEach
    void setUp() {
        flight = mock(Flight.class);
        given(this.flight.getSegments()).willReturn(List.of(mock(Segment.class), mock(Segment.class)));
        calculator = mock(FlightDurationCalculator.class);
        given(calculator.calculateFor(ArgumentMatchers.any(Flight.class))).willReturn(threeHours);
    }

    @Test
    void whenCalculatedDurationIsGreaterThanLimit_thenReturnsTrue() {
        // Given
        FlightFilterByDurationLimit filter = new FlightFilterByDurationLimit(calculator, Duration.ofHours(2));
        // When
        boolean result = filter.test(flight);
        // Then
        assertTrue(result);
    }

    @Test
    void whenCalculatedDurationIsLessThanLimit_thenReturnsFalse() {
        // Given
        FlightFilterByDurationLimit filter = new FlightFilterByDurationLimit(calculator, Duration.ofHours(4));
        // When
        boolean result = filter.test(flight);
        // Then
        assertFalse(result);
    }

    @Test
    void whenCalculatedDurationEqualsLimit_thenReturnsFalse() {
        // Given
        FlightFilterByDurationLimit filter = new FlightFilterByDurationLimit(calculator, Duration.ofHours(3));
        // When
        boolean result = filter.test(flight);
        // Then
        assertFalse(result);
    }
}
