package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    public void testAddItemAndTotalPrice() {
        class TestItem extends OrderItem {
            public TestItem(String description, double price) {
                super(description, price);
            }
        }

        Order order = new Order();
        OrderItem item1 = new TestItem("Turkey Sandwich", 7.99);
        OrderItem item2 = new TestItem("Chips", 1.99);

        order.addItem(item1);
        order.addItem(item2);

        assertEquals(9.98, order.getTotalPrice(), 0.01);
    }
    @Test
    public void testRemoveItem() {
        Order order = new Order();
        OrderItem item = new OrderItem("Drink", 2.49);

        order.addItem(item);
        order.removeItem(item);

        assertTrue(order.getItems().isEmpty(), "Order should be empty after removing item");
    }

    @Test
    public void testOrderDetailsNotNull() {
        Order order = new Order();
        OrderItem item = new OrderItem("Veggie Wrap", 6.25);
        order.addItem(item);

        String details = order.getOrderDetails();
        assertNotNull(details);
        assertTrue(details.contains("Veggie Wrap"));
        assertTrue(details.contains("$6.25"));
    }

}