package com.pluralsight;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SandwichTest {

        private Sandwich sandwich;


        @Test
        public void testBasePriceOnly() {
            double expectedPrice = 7.00;
            assertEquals(expectedPrice, sandwich.getPrice(), 0.01);
        }

        @Test
        public void testAddToppingsAffectsPrice() {
            Topping lettuce = new RegularTopping("Lettuce", 4);
            Topping tomato = new RegularTopping("Tomato", 8);
            sandwich.addTopping(lettuce);
            sandwich.addTopping(tomato);

            double expectedPrice = 7.00 + 0.25 + 0.25;
            assertEquals(expectedPrice, sandwich.getPrice(), 0.01);
        }

        @Test
        public void testExtraMeatAndCheeseAffectsPrice() {
            sandwich.setExtraMeat(true);
            sandwich.setExtraCheese(true);

            double expectedPrice = 7.00 + 1.00 + 0.60;
            assertEquals(expectedPrice, sandwich.getPrice(), 0.01);
        }

        @Test
        public void testToppingsListIsImmutable() {
            Topping pickle = new RegularTopping("Pickle", 3);
            sandwich.addTopping(pickle);

            List<Topping> toppings = sandwich.getToppings();
            toppings.clear(); // Should not affect original

            assertEquals(1, sandwich.getToppings().size());
        }

        @Test
        public void testGetDescriptionWithToastingAndExtras() {
            sandwich.addTopping(new PremiumTopping("Ham", 8, "meat"));
            sandwich.addTopping(new PremiumTopping("Swiss", 4, "cheese"));
            sandwich.setToasted(true);
            sandwich.setExtraMeat(true);
            sandwich.setExtraCheese(true);

            String description = sandwich.getDescription();

            assertTrue(description.contains("8\" Wheat sandwich (toasted)"));
            assertTrue(description.contains("Meats(Ham) +EXTRA"));
            assertTrue(description.contains("Cheese(Swiss) +EXTRA"));
        }

}