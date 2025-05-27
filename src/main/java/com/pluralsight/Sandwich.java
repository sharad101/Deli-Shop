package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

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



    public void setSize(int size) {
        this.size = size;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public boolean isExtraMeat() {
        return extraMeat;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
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

            // Add extra meat and cheese costs
            if (extraMeat) {
                toppingsPrice += getExtraMeatPrice();
            }
            if (extraCheese) {
                toppingsPrice += getExtraCheesePrice();
            }

            return basePrice + toppingsPrice;
        }

        private double getBasePriceForSize() {
            switch (size) {
                case 4: return 5.50;
                case 8: return 7.00;
                case 12: return 8.50;
                default: return 7.00; // Default to 8" price
            }
        }

        private double getExtraMeatPrice() {
            switch (size) {
                case 4: return 0.50;
                case 8: return 1.00;
                case 12: return 1.50;
                default: return 1.00;
            }
        }

        private double getExtraCheesePrice() {
            switch (size) {
                case 4: return 0.30;
                case 8: return 0.60;
                case 12: return 0.90;
                default: return 0.60;
            }
        }

        @Override
        public String getDescription() {
            StringBuilder desc = new StringBuilder();
            desc.append(size).append("\" ").append(breadType).append(" sandwich");
            if (toasted) {
                desc.append(" (toasted)");
            }
            if (!toppings.isEmpty()) {
                desc.append(" with ").append(toppings.size()).append(" toppings");
            }
            if (extraMeat) {
                desc.append(" + extra meat");
            }
            if (extraCheese) {
                desc.append(" + extra cheese");
            }
            return desc.toString();
        }

}
