package com.gridnine.testing;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static com.gridnine.testing.filters.FlightFilters.*;

public class Main {

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightsProcessor processor = new FlightsProcessor(flights, System.out::println,
                containsOption(args, "--print-info"));
        processor.runWithFilter(departureNowAndAfter(),
                "Excluding flights that have departure date earlier that now");
        processor.runWithFilter(doNotHaveReversedSegments(),
                "Excluding flights that have segments with reversed departure and arrival dates");
        processor.runWithFilter(groundTimeLessThanOrEqual(Duration.ofHours(2)),
                "Excluding flights that have ground time greater than 2 hours");
    }

    private static boolean containsOption(String[] args, String option) {
        return Arrays.asList(args).contains(option);
    }
}
