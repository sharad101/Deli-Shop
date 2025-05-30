package com.pluralsight;

/**
 * PremiumTopping is a subclass of the abstract Topping class.
 * This class represents premium toppings such as meat or cheese, which have
 * an associated cost based on their category and the size of the sandwich.
 * Relationship:
 * - Inherits from the abstract class Topping (is-a relationship).
 * - It provides a implementation of the abstract getPrice() method.
 * - Demonstrates polymorphism by overriding getPrice(), allowing different
 *   pricing logic for PremiumTopping
 */

public class PremiumTopping extends Topping {
    private String category;

    //Constructor for PremiumTopping
    public PremiumTopping(String name, int sandwichSize, String category) {
        super(name, sandwichSize);
        this.category = category;
    }

    /**
     * Calculates the price of the premium topping based on its category and sandwich size.
     * - If the category is "meat", pricing is: $1.00 (4"), $2.00 (8"), $3.00 (12").
     * - If the category is "cheese", pricing is: $0.75 (4"), $1.50 (8"), $2.25 (12").
     * Polymorphism:
     * - Yes, this method overrides the abstract getPrice() method in Topping.
     * - This allows PremiumTopping to provide category-based pricing while other
     *   subclasses like RegularTopping can implement their own logic.
     **/
    @Override
    public double getPrice() {
        if ("meat".equalsIgnoreCase(category)) {
            switch (sandwichSize) {
                case 4:
                    return 1.00;
                case 8:
                    return 2.00;
                case 12:
                    return 3.00;
                default:
                    return 2.00;
            }
        } else if ("cheese".equalsIgnoreCase(category)) {
            switch (sandwichSize) {
                case 4:
                    return 0.75;
                case 8:
                    return 1.50;
                case 12:
                    return 2.25;
                default:
                    return 1.50;
            }
        }
        return 0.00;
    }



  //Getter for the category of the premium topping
  //Returns the topping category meat or cheese
    public String getCategory() {
        return category;
    }
}
