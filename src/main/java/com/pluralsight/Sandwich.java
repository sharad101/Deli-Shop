package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Sandwich class models a customizable sandwich order.
 *
 * It implements the OrderItem interface, meaning it must provide
 * getPrice() and getDescription() methods.
 *
 * This class supports various customization options such as:
 * - Sandwich size (4, 8, or 12 inches)
 * - Bread type
 * - Toppings (including regular and premium types)
 * - Optional toast
 * - Optional extra meat and cheese
 *
 * Relationships:
 * - Implements OrderItem (is-a relationship)
 *
 * Polymorphism:
 * - The getPrice() method in this class is a specific implementation of
 *   the method defined in the OrderItem interface, allowing it to be treated
 *   the same as other items that implement the interface (e.g., drinks, chips).
 */

public class Sandwich implements OrderItem {
    private int size; // 4, 8, or 12 inches
    private String breadType;
    private List<Topping> toppings;
    private boolean toasted;
    private boolean extraMeat;
    private boolean extraCheese;



    //Constructor to create a new Sandwich object
    public Sandwich(int size, String breadType) {
        this.size = size;
        this.breadType = breadType;
        this.toppings = new ArrayList<>();
        this.toasted = false;
        this.extraMeat = false;
        this.extraCheese = false;
    }

    public int getSize() {
        return size;
    }

    public String getBreadType() {
        return breadType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public boolean isToasted() {
        return toasted;
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    //Adds a topping to the sandwich.
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    // Removes a topping from the sandwich
    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public void setExtraMeat(boolean extraMeat) {
        this.extraMeat = extraMeat;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    @Override
    public double getPrice() {
        double basePrice = getBasePriceForSize();

        /**
         * Calculates the total price of the sandwich, including:
         * - Base price (based on size)
         * - All toppings (polymorphic pricing via Topping subclasses)
         * - Optional charges for extra meat and cheese
         *
         * Polymorphism:
         * - This method uses polymorphism to sum the prices of different types of Toppings,
         *   each of which may implement getPrice() differently
         *
         *   Stream
         */
        double toppingsPrice = toppings.stream().mapToDouble(Topping::getPrice).sum();

        if (extraMeat) {
            toppingsPrice += getExtraMeatPrice();
        }
        if (extraCheese) {
            toppingsPrice += getExtraCheesePrice();
        }

        return basePrice + toppingsPrice;
    }

    /**
     * Builds a description of the sandwich including:
     * - Size, bread type, and toast status
     * - Grouped toppings by type
     * - Extra meat or cheese indicator
     *
     * This is useful for displaying the sandwich details in a receipt or order summary.
     *
     * Streams
     */
    @Override
    public String getDescription() {
        StringBuilder desc = new StringBuilder();
        desc.append(size).append("\" ").append(breadType).append(" sandwich");

        if (toasted) {
            desc.append(" (toasted)");
        }

        if (!toppings.isEmpty()) {
            desc.append(" with:");

            // Group toppings using streams
            var grouped = toppings.stream()
                    .collect(Collectors.groupingBy(t -> {
                        if (t instanceof PremiumTopping premium) {
                            return premium.getCategory().toLowerCase(); // "meat" or "cheese"
                        } else {
                            return "regular";
                        }
                    }));

            /**
             * This breaks down as:
             * grouped.getOrDefault("meat", List.of()): safely gets the list of meat toppings, or returns an empty list if there are none
             * .stream(): turns the list into a stream
             * .map(Topping::getName): transforms each Topping object into its name (String)
             * .toList(): collects the names into a list of strings.
             */
            List<String> meats = grouped.getOrDefault("meat", List.of()).stream()
                    .map(Topping::getName).toList();
            List<String> cheeses = grouped.getOrDefault("cheese", List.of()).stream()
                    .map(Topping::getName).toList();
            List<String> regular = grouped.getOrDefault("regular", List.of()).stream()
                    .map(Topping::getName).toList();

            if (!meats.isEmpty()) {
                desc.append(" Meats(").append(String.join(", ", meats)).append(")");
                if (extraMeat) desc.append(" +EXTRA");
            }

            if (!cheeses.isEmpty()) {
                desc.append(" Cheese(").append(String.join(", ", cheeses)).append(")");
                if (extraCheese) desc.append(" +EXTRA");
            }

            if (!regular.isEmpty()) {
                desc.append(" Toppings(").append(String.join(", ", regular)).append(")");
            }
        }

        return desc.toString();
    }

    //Returns the base price of the sandwich depending on its size
    private double getBasePriceForSize() {
        return switch (size) {
            case 4 -> 5.50;
            case 8 -> 7.00;
            case 12 -> 8.50;
            default -> 7.00;
        };
    }

    //Returns the additional charge for extra meat based on sandwich size
    double getExtraMeatPrice() {
        return switch (size) {
            case 4 -> 0.50;
            case 8 -> 1.00;
            case 12 -> 1.50;
            default -> 1.00;
        };
    }

    //Returns the additional charge for extra cheese based on sandwich size
    double getExtraCheesePrice() {
        return switch (size) {
            case 4 -> 0.30;
            case 8 -> 0.60;
            case 12 -> 0.90;
            default -> 0.60;
        };
    }
}
