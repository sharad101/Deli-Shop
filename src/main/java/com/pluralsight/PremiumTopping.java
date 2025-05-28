package com.pluralsight;

public class PremiumTopping extends Topping {
    private String category; // "meat" or "cheese"

    public PremiumTopping(String name, int sandwichSize, String category) {
        super(name, sandwichSize);
        this.category = category;
    }

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

    public String getCategory() {
        return category;
    }
}
