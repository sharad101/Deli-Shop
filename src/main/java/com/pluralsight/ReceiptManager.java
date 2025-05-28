package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class ReceiptManager {

    public String generateReceipt(Order order) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("DELI-cious Receipt\n");
        receipt.append("==================\n");
        receipt.append(order.getOrderDetails());
        receipt.append("\n\nThank you for your business!");
        return receipt.toString();
    }

    public boolean saveReceiptToFile(String receipt) {
        try {
            // Create receipts directory if it doesn't exist
            Files.createDirectories(Paths.get("receipts"));

            // Generate filename with current timestamp
            String filename = "receipts/" + generateFileName();

            // Write receipt to file
            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(receipt);
            }

            System.out.println("Receipt saved to: " + filename);
            return true;
        } catch (IOException e) {
            System.err.println("Error saving receipt: " + e.getMessage());
            return false;
        }
    }

    private String generateFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return java.time.LocalDateTime.now().format(formatter) + ".txt";
    }
}
