package com.pluralsight;

public abstract class Topping {
    protected String name;
    protected int sandwichSize;

    public Topping(String name, int sandwichSize) {
        this.name = name;
        this.sandwichSize = sandwichSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSandwichSize() {
        return sandwichSize;
    }

    public void setSandwichSize(int sandwichSize) {
        this.sandwichSize = sandwichSize;
    }

    
}