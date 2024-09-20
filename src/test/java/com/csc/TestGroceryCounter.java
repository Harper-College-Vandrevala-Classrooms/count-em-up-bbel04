package com.csc;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGroceryCounter {

  GroceryCounter counter;

  @Test
  void testIncrementHundredths() {
    counter = new GroceryCounter();

    counter.incrementHundredths();
    assertEquals(counter.total(), "$0.01");
  }

  @Test
  void testIncrementTenths() {
    counter = new GroceryCounter();
    counter.incrementTenths();
    assertEquals(counter.total(), "$0.10");
  }

  @Test
  void testIncrementOnes() {
    counter = new GroceryCounter();
    counter.incrementOnes();
    assertEquals(counter.total(), "$1.00");
  }

  @Test
  void testIncrementTens() {
    counter = new GroceryCounter();
    counter.incrementTens();
    assertEquals(counter.total(), "$10.00");
  }

  @Test
  void testNumberOfOverflows() {
    counter = new GroceryCounter();
    for (int i = 0; i < 11; i++) {
      counter.incrementTens();
    }
    counter.numberOfOverflows();
    assertEquals(counter.numberOfOverflows(), 1);
  }

  @Test
  void testManyOverflows() {
    counter = new GroceryCounter();
    for (int i = 0; i < 20; i++) {
      counter.incrementTens();
    }
    counter.numberOfOverflows();
    assertEquals(counter.numberOfOverflows(), 2);
  }

  @Test
  void testCounterUpperLimit() {
    counter = new GroceryCounter();
    for (int i = 0; i < 9999; i++) {
      counter.incrementHundredths();
    }
    assertEquals(counter.total(), "$99.99");
  }

  @Test
  void testOverflowAtUpperLimit() {
    counter = new GroceryCounter();
    for (int i = 0; i < 10000; i++) {
      counter.incrementHundredths();
    }
    assertEquals(counter.total(), "$0.00");
  }

  @Test
  void testClear() {
    counter = new GroceryCounter();
    for (int i = 0; i < 9; i++) {
      counter.incrementTens();
    }
    counter.clear();
    assertEquals(counter.total(), "$0.00");
  }

  @Test
  void TestGroceryCounterConstructor() {
    counter = new GroceryCounter(1,1,1,1);
    assertEquals(counter.total(), "$11.11");
  }
}
