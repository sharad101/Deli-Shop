package com.pluralsight;

/**
 * This interface ensures that any class implementing it must provide:
 * - A way to get the item's price
 * - A description of the item
 *
 * Purpose:
 * - Promotes polymorphism: Any object that implements OrderItem can be treated
 *   uniformly in code that processes order items (ex calculating totals, displaying orders).
 *
 * Relationship:
 * - This interface establishes an "implements" relationship. Any class that
 *   implements OrderItem agrees to provide implementations for the declared methods.
 */

public interface OrderItem {

    //Returns the price of the order item
    double getPrice();


    //Returns description of the order
    String getDescription();
}
