package com.pluralsight;

public class Drink implements OrderItem {
    private String size; // Small, Medium, Large
    private String flavor;

    public Drink(String size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {
            case "small": return 2.00;
            case "medium": return 2.50;
            case "large": return 3.00;
            default: return 2.50; // Default to medium price
        }
    }

    @Override
    public String getDescription() {
        return size + " " + flavor + " drink";
    }


}