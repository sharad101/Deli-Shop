package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChipsTest {

    @Test
    void testChipsCreationAndGetters() {
        Chips chips = new Chips("BBQ");

        assertEquals("BBQ", chips.getType());
        assertEquals("BBQ chips", chips.getDescription());
        assertEquals(1.50, chips.getPrice(), 0.001);
    }

    @Test
    void testSetType() {
        Chips chips = new Chips("Plain");
        chips.setType("Sour Cream");

        assertEquals("Sour Cream", chips.getType());
        assertEquals("Sour Cream chips", chips.getDescription());
    }

}