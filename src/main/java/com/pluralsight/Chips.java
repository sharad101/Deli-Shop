package com.pluralsight;

public class Chips implements OrderItem {
    private String type;

    public Chips(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double getPrice() {
        return 1.50; // Fixed price for all chips
    }

    @Override
    public String getDescription() {
        return type + " chips";
    }
}
