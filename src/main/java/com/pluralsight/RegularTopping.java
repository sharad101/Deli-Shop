package com.pluralsight;

/**
 * RegularTopping is a subclass of the abstract Topping class.
 *
 * This class represents a standard or default type of topping used in a sandwich.
 * It inherits common properties such as name and sandwich size from the Topping class.
 *
 * Relationship:
 * - Inherits from Topping (is-a relationship).
 * - It provides a specific implementation of the abstract getPrice() method,
 *   which is required because Topping is abstract.
 */
public class RegularTopping extends Topping {



     //Constructor for RegularTopping
    public RegularTopping(String name, int sandwichSize) {
        super(name, sandwichSize);
    }

    /**
     * Provides the specific implementation of the abstract getPrice() method from Topping.
     * This method currently returns 0, because regular toppings are free
     * Polymorphism:
     * - Yes, this method overrides the abstract method from Topping.
     * - This allows different types of Topping objects to respond differently
     *   when getPrice() is called.
     */
    @Override
    public double getPrice() {
        return 0;
    }

}