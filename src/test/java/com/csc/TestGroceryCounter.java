package com.csc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGroceryCounter {

  GroceryCounter counter;

  @BeforeEach
  void setUp() {
    counter = new GroceryCounter();
  }

  @Test
  void testIncrementHundredths() {
    counter.incrementHundredths();
    assertEquals(counter.total(), "$0.01");
  }

  @Test
  void testIncrementTenths() {
    counter.incrementTenths();
    assertEquals(counter.total(), "$0.10");
  }

  @Test
  void testIncrementOnes() {
    counter.incrementOnes();
    assertEquals(counter.total(), "$1.00");
  }

  @Test
  void testIncrementTens() {
    counter.incrementTens();
    assertEquals(counter.total(), "$10.00");
  }

  @Test
  void testNumberOfOverflows() {
    for (int i = 0; i < 11; i++) {
      counter.incrementTens();
    }
    counter.numberOfOverflows();
    assertEquals(counter.numberOfOverflows(), 1);
  }

  @Test
  void testManyOverflows() {
    for (int i = 0; i < 20; i++) {
      counter.incrementTens();
    }
    counter.numberOfOverflows();
    assertEquals(counter.numberOfOverflows(), 2);
  }

  @Test
  void testCounterUpperLimit() {
    for (int i = 0; i < 9999; i++) {
      counter.incrementHundredths();
    }
    assertEquals(counter.total(), "$99.99");
  }

  @Test
  void testOverflowAtUpperLimit() {
    for (int i = 0; i < 10000; i++) {
      counter.incrementHundredths();
    }
    assertEquals(counter.total(), "$0.00");
  }

  @Test
  void testClear() {
    for (int i = 0; i < 9; i++) {
      counter.incrementTens();
    }
    counter.clear();
    assertEquals(counter.total(), "$0.00");
  }
}
