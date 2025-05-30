package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Order class represents a customer's complete order.
 * It contains an auto-generated order ID based on the current timestamp,
 * a list of OrderItem objects (like Sandwiches, Chips, or Drinks),
 * and the order date and time.
 *
 * This class is responsible for managing the order's contents and
 * calculating the total price.
 */
public class Order {
    private String orderId;
    private List<OrderItem> orderItems;
    private LocalDateTime orderDate;

    /**
     * Constructor that initializes a new Order.
     * - Sets the order date/time to the current moment.
     * - Generates a unique order ID using the date/time.
     * - Initializes an empty list of order items.
     */
    public Order() {
        this.orderDate = LocalDateTime.now();
        this.orderId = generateOrderId();
        this.orderItems = new ArrayList<>();
    }


    // Generates a unique order ID using the date and time format
    private String generateOrderId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return orderDate.format(formatter);
    }

    //Adds an item to the order
    public void addItem(OrderItem item) {
        orderItems.add(item);
    }

    //Removes an item from the order
    public void removeItem(OrderItem item) {
        orderItems.remove(item);
    }

    //Returns a copy of the order items list
    public List<OrderItem> getItems() {
        return new ArrayList<>(orderItems); // Return copy to prevent external modification
    }

    //Calculates and returns the total price of the order by summing the prices
    // of all order items
    // Streams
    public double getTotalPrice() {

        //Take the list of order items, get the price of each one, and sum them up.
        return orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
    }

    /**
     * Builds a readable summary of the order details including:
     * - Order ID
     * - Order date/time
     * - List of each item with its description and price
     * - Total price
     */
    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Order ID: ").append(orderId).append("\n");
        details.append("Order Date: ").append(orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        details.append("Items:\n");

        for (OrderItem item : orderItems) {
            details.append("- ").append(item.getDescription()).append(" - $").append(String.format("%.2f", item.getPrice())).append("\n");
        }

        details.append("Total: $").append(String.format("%.2f", getTotalPrice()));
        return details.toString();
    }

    // Gets the unique order ID
    public String getOrderId() {
        return orderId;
    }

    // Gets the date and time when the order was created
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

}
