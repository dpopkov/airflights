package com.gridnine.testing.filters;

import com.gridnine.testing.Segment;

import java.util.function.Predicate;

@FunctionalInterface
public interface SegmentFilter extends Predicate<Segment> {
}
