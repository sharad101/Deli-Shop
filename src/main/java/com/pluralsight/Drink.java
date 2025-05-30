package com.pluralsight;

/**
 * The Drink class represents a beverage that can be added to a customer's order
 * in the DELI-cious point-of-sale system. It implements the OrderItem interface,
 * which means it must define methods to get the price and description of the item.
 *
 * Fields:
 * - size: the size of the drink
 * - flavor: the flavor of the drink
 *
 * This class demonstrates polymorphism by implementing the OrderItem interface,
 * allowing it to be treated the same as other items like Sandwiches and Chips
 * through shared method signatures.
 */
public class Drink implements OrderItem {
    private String size; // Small, Medium, Large
    private String flavor;

    //Constructor to initialize a drink with a specific size and flavor
    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    //Returns the size of the drink
    public String getSize() {
        return size;
    }

    //Returns the flavor of the drink
    public String getFlavor() {
        return flavor;
    }

    /**
     * Calculates and returns the price of the drink based on its size.
     * Defaults to $2.50 if size is unrecognized.
     * @Override indicates this method overrides the one declared in OrderItem,
     * demonstrating polymorphism.
     */
    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {
            case "small": return 2.00;
            case "medium": return 2.50;
            case "large": return 3.00;
            default: return 2.50; // Default to medium price
        }
    }

    /**
     * Returns a string describing the drink
     * @Override indicates this method overrides the one declared in OrderItem,
     * demonstrating polymorphism.
     */
    @Override
    public String getDescription() {
        return size + " " + flavor + " drink";
    }


}