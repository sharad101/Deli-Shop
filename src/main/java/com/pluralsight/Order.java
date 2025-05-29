package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private List<OrderItem> orderItems;
    private LocalDateTime orderDate;

    public Order() {
        this.orderDate = LocalDateTime.now();
        this.orderId = generateOrderId();
        this.orderItems = new ArrayList<>();
    }

    private String generateOrderId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return orderDate.format(formatter);
    }

    public void addItem(OrderItem item) {
        orderItems.add(item);
    }

    public void removeItem(OrderItem item) {
        orderItems.remove(item);
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(orderItems); // Return copy to prevent external modification
    }

    public double getTotalPrice() {
        return orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
    }

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

    public String getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

//    public void saveReceiptToFile() {
//        String filename = "receipt_" + orderId + ".txt";
//
//        // You can customize the folder where to save, here it uses current working directory
//        String filePath = Paths.get(System.getProperty("user.dir"), filename).toString();
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//            writer.write(getOrderDetails());
//            System.out.println("Receipt saved successfully to " + filePath);
//        } catch (IOException e) {
//            System.err.println("Error saving receipt to file: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
}
