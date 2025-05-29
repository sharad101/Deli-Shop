package com.pluralsight;


/**
 * The Chips class represents a chip item in the sandwich shop's menu.
 * It implements the OrderItem interface, which allows it to be included in a customer's order.
 *
 * - Each Chips object has a type (e.g., BBQ, Sour Cream).
 * - All chips have a fixed price of $1.50.
 * - The class provides methods to get and set the chip type,
 *   and to retrieve a description and price for display or receipt purposes.
 *
 * Implements polymorphism by providing specific behavior for the getPrice and getDescription
 * methods defined in the OrderItem interface.
 */
public class Chips implements OrderItem {
    private String type;

    //Constructor to create a new Chips object with  specific type
    public Chips(String type) {
        this.type = type;
    }

    //Returns the type/flavor of the chips
    public String getType() {
        return type;
    }

    //Sets or updates the type/flavor of the chips
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the fixed price of the chips.
     * @Override indicates this method is providing a concrete implementation
     * of the getPrice method from the OrderItem interface.
     */
    @Override
    public double getPrice() {
        return 1.50; // Fixed price for all chips
    }

    /**
     * Returns a description of the chips for display or receipt purposes.
     * @Override indicates this method implements the getDescription method
     * from the OrderItem interface demonstrating polymorphism.
     */
    @Override
    public String getDescription() {
        return type + " chips";
    }
}
