package com.pluralsight;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SandwichTest {

    @Test
    void testGetPrice_BaseOnly_8Inch() {
        Sandwich sandwich = new Sandwich(8, "Wheat");
        double expected = 7.00; // base price for 8-inch
        assertEquals(expected, sandwich.getPrice(), 0.001);
    }

    @Test
    void testGetPrice_WithRegularToppings() {
        Sandwich sandwich = new Sandwich(8, "White");
        sandwich.addTopping(new RegularTopping("Lettuce", 8));
        sandwich.addTopping(new RegularTopping("Tomato", 8));

        double expected = 7.00;
        assertEquals(expected, sandwich.getPrice(), 0.001);
    }

    @Test
    void testGetPrice_WithPremiumToppings() {
        Sandwich sandwich = new Sandwich(8, "Italian");
        sandwich.addTopping(new PremiumTopping("Turkey", 8, "Meat"));
        sandwich.addTopping(new PremiumTopping("Cheddar", 8, "Cheese"));

        double expected = 7.00 + 2.00 + 1.50;
        assertEquals(expected, sandwich.getPrice(), 0.001);
    }

    @Test
    void testGetPrice_WithExtraMeatAndCheese() {
        Sandwich sandwich = new Sandwich(12, "Multigrain");
        sandwich.setExtraMeat(true);
        sandwich.setExtraCheese(true);

        // base: 8.50, extra meat: 1.50, extra cheese: 0.90
        double expected = 8.50 + 1.50 + 0.90;
        assertEquals(expected, sandwich.getPrice(), 0.001);
    }

    @Test
    void testGetPrice_CombinedToppingsAndExtras() {
        Sandwich sandwich = new Sandwich(4, "White");
        sandwich.addTopping(new RegularTopping("Lettuce", 4));
        sandwich.addTopping(new PremiumTopping("Ham", 4, "Meat"));
        sandwich.setExtraMeat(true);
        sandwich.setExtraCheese(true);

        double expected = 5.50 + 0.25 + 0.75 + 0.50 + 0.30;
        assertEquals(expected, sandwich.getPrice(), 0.001);
    }


}