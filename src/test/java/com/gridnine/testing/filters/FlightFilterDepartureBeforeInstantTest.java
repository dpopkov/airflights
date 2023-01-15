package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilterDepartureBeforeInstantTest {

    private final LocalDateTime now = LocalDateTime.now();
    private final LocalDateTime hourEarlier = now.minusHours(1);
    private final LocalDateTime hourLater = now.plusHours(1);

    @Test
    void whenFlightDepartureTimeIsEarlier_thenFilterReturnsTrue() {
        // Given
        var filter = new FlightFilterDepartureBeforeInstant(now);
        Flight flight = FlightBuilder.createFlight(hourEarlier, hourLater);
        // When
        boolean result = filter.test(flight);
        // Then
        assertTrue(result);
    }

    @Test
    void whenFlightDepartureTimeIsLater_thenFilterReturnsFalse() {
        // Given
        var filter = new FlightFilterDepartureBeforeInstant(hourEarlier);
        Flight flight = FlightBuilder.createFlight(now, hourLater);
        // When
        boolean result = filter.test(flight);
        // Then
        assertFalse(result);
    }

    @Test
    void whenFlightHasNoSegments_thenThrowException() {
        // Given
        Flight flight = Mockito.mock(Flight.class);
        BDDMockito.given(flight.getSegments()).willReturn(null);
        // When/Then
        assertThrows(IllegalArgumentException.class,
                () -> {
                    var filter = new FlightFilterDepartureBeforeInstant(now);
                    filter.test(flight);
                });
    }
}
