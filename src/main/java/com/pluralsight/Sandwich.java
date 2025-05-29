package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sandwich implements OrderItem {
    private int size; // 4, 8, or 12 inches
    private String breadType;
    private List<Topping> toppings;
    private boolean toasted;
    private boolean extraMeat;
    private boolean extraCheese;

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

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

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
        double toppingsPrice = toppings.stream().mapToDouble(Topping::getPrice).sum();

        if (extraMeat) {
            toppingsPrice += getExtraMeatPrice();
        }
        if (extraCheese) {
            toppingsPrice += getExtraCheesePrice();
        }

        return basePrice + toppingsPrice;
    }

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



    private double getBasePriceForSize() {
        return switch (size) {
            case 4 -> 5.50;
            case 8 -> 7.00;
            case 12 -> 8.50;
            default -> 7.00;
        };
    }

    double getExtraMeatPrice() {
        return switch (size) {
            case 4 -> 0.50;
            case 8 -> 1.00;
            case 12 -> 1.50;
            default -> 1.00;
        };
    }

    double getExtraCheesePrice() {
        return switch (size) {
            case 4 -> 0.30;
            case 8 -> 0.60;
            case 12 -> 0.90;
            default -> 0.60;
        };
    }
}
