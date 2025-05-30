package com.pluralsight;

/**
 * Abstract class Topping represents a generic sandwich topping.
 *
 * This class serves as a base (superclass) in an inheritance hierarchy where
 * specific types of toppings will extend this class. It encapsulates common
 * properties such as the topping's name and the size of the sandwich it is
 * applied to.
 *
 * Because it is abstract, Topping cannot be instantiated directly. Subclasses
 * are required to provide concrete implementations for the abstract method getPrice().
 *
 * This class uses inheritance and defines a contract for subclasses
 * to implement pricing logic, so we use polymorphism through method overriding.
 */

public abstract class Topping {
    protected String name;
    protected int sandwichSize;

    // Constructor to initialize a topping with its name and the sandwich size
    public Topping(String name, int sandwichSize) {
        this.name = name;
        this.sandwichSize = sandwichSize;
    }

    //Getter for the topping's name
    public String getName() {
        return name;
    }

    //Setter for the topping's name
    public void setName(String name) {
        this.name = name;
    }


    //Getter for the sandwich size
    public int getSandwichSize() {
        return sandwichSize;
    }

    //Setter for the sandwich size
    public void setSandwichSize(int sandwichSize) {
        this.sandwichSize = sandwichSize;
    }

    /**
     * Abstract method to get the price of the topping.
     * This method enforces that all subclasses must provide their own implementation,
     * enabling polymorphism. When called on a subclass instance, the overridden method
     * specific to that topping type will execute.
     */
    public abstract double getPrice();
}

