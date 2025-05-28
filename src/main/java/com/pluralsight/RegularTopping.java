package com.pluralsight;

public class RegularTopping extends Topping {

    public RegularTopping(String name, int sandwichSize) {
        super(name, sandwichSize);
    }

    @Override
    public double getPrice() {
        return 0;
    }

}