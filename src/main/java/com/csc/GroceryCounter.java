package com.csc;

//import java.util.Arrays;

//import java.util.ArrayList;

public class GroceryCounter {

  private int[] counter = new int[]{0, 0, 0, 0};
  private int overflows = 0;

  public GroceryCounter() { //default constructor
    counter = new int[]{0,0,0,0};
  }

  public GroceryCounter(int tens, int ones, int tenths, int hundredths) { //initialized counter
    if ((tens <= 9 && 0 <= tens) && (ones <= 9 && 0 <= ones) && (tenths <= 9 && 0 <= tenths) && (hundredths <= 9 && 0 <= hundredths)) {
      counter = new int[] {tens, ones, tenths, hundredths};
    }
    else {
      System.out.println("Counter initialization out of bounds. Default to 00.00.");
      counter = new int[] {0, 0, 0, 0};
    }
    
  }

  void incrementHundredths() { 
    // Check if max counter value is reached (9999), then reset and increment overflow
    if (counter[0] == 9 && counter[1] == 9 && counter[2] == 9 && counter[3] == 9) {
        counter[0] = 0;
        counter[1] = 0;
        counter[2] = 0;
        counter[3] = 0;
        overflows++;  // increment the overflow counter
    } 
    // increment logic
    else {
        if (counter[3] < 9) { 
            counter[3]++;
        } else { 
            counter[3] = 0;
            incrementTenths();
        }
    }
  }

  void incrementTenths() {
    if (counter[2] < 9) {   //if number in tenths place is less than 9, increment
      counter[2]++;
    }
    else {   //if number in tenths place is greater than or equal to 9, roll back to zero, increment ones
      counter[2] = 0;
      incrementOnes();
    }
  }

  void incrementOnes() {    
    if (counter[1] < 9) {   //if number in ones place is less than 9, increment 
      counter[1]++;
    }
    else {    //if number in ones is greater than or equal to 9, roll back to zero, increment tens
      counter[1] = 0;
      incrementTens();
    }
  }

  void incrementTens() {
    if (counter[0] < 9) {  //if less than 9, increment
      counter[0]++;
    }
    else {  //if greater than or equal to 9, roll back to zero, increment overflows
      counter[0] = 0;
      overflows++;
    }
  }

  int numberOfOverflows() {
    return overflows;
  }

  String total() {    //display in dollar format
    if (counter[0] == 0) {
      return String.format("$%d.%d%d", counter[1], counter[2], counter[3]);
    }
    else {
      return String.format("$%d%d.%d%d", counter[0], counter[1], counter[2], counter[3]);
    }
  }

  void clear() {    
    for (int i = 0; i < counter.length; i++) {  //roll back all to zero
      counter[i] = 0;
    }
    overflows = 0;   //reset overflows
  }

  public static void main(String[] args) {
    GroceryCounter counter = new GroceryCounter(1, 0, 0, 0);
    GroceryCounter counter2 = new GroceryCounter();

    //counter.incrementTenths();
    for (int i = 0; i < 10000; i++) {
      counter.incrementHundredths();
    }
    
    System.out.println(counter.total()); // This would print out $00.00
    System.out.println(counter.numberOfOverflows());
    counter.clear();

    for (int i = 0; i < 10; i++) {
      counter2.incrementOnes();
    }

    System.out.println(counter.total());

    counter.incrementTens();
    counter.incrementTens();
    counter.incrementOnes();
    counter.incrementHundredths();
    counter.incrementTenths();

    System.out.println(counter.total());

    for (int i = 0; i < 30; i++) {
      counter.incrementOnes();
    }
    
    System.out.println(counter.total());

    for (int i = 0; i < 22; i++) {
      counter2.incrementTens();
    }

    System.out.println("Counter1 Total: " + counter.total());
    System.out.println("Counter 1 Number of Overflows: " + counter.numberOfOverflows());
    System.out.println("Counter 2 Total: " + counter2.total());
    System.out.println("Counter 2 Number of Overflows: " + counter2.numberOfOverflows());

    counter.clear();   //roll back
    counter2.clear();

    System.out.println("Counter1 Total: " + counter.total());
    System.out.println("Counter 1 Number of Overflows: " + counter.numberOfOverflows());
    System.out.println("Counter 2 Total: " + counter2.total());
    System.out.println("Counter 2 Number of Overflows: " + counter2.numberOfOverflows());
  } 
}
