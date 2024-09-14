package com.csc;

//import java.util.Arrays;

//import java.util.ArrayList;

public class GroceryCounter {

  private int[] counter = new int[]{0, 0, 0, 0};
  private int overflows = 0;

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
    GroceryCounter counter = new GroceryCounter();

    //counter.incrementTenths();
    for (int i = 0; i < 10000; i++) {
      counter.incrementHundredths();
    }
    
    System.out.println(counter.total()); // This would print out $00.00
    System.out.println(counter.numberOfOverflows());
    counter.clear();

    for (int i = 0; i < 10; i++) {
      counter.incrementOnes();
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
      counter.incrementTens();
    }

    System.out.println(counter.total());
    System.out.println(counter.numberOfOverflows());

    counter.clear();   //roll back

    System.out.println(counter.total());
    System.out.println(counter.numberOfOverflows());
  } 
}
