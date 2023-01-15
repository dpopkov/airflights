package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;

import java.time.LocalDateTime;

/**
 * Selects flights that have departure time earlier than a certain point in time.
 */
class FlightFilterDepartureBeforeInstant extends AbstractFlightFilter {

    private final LocalDateTime instantInLocalDateTime;

    public FlightFilterDepartureBeforeInstant(LocalDateTime instantInLocalDateTime) {
        this.instantInLocalDateTime = instantInLocalDateTime;
    }

    /**
     * Returns true if the departure time of the specified flight is earlier than the instant of this filter,
     * or false otherwise.
     */
    @Override
    public boolean test(Flight flight) {
        validateSegments(flight);
        LocalDateTime departure = flight.getSegments().iterator().next().getDepartureDate();
        return departure.isBefore(instantInLocalDateTime);
    }
}
