package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

/**
 * The ReceiptManager class is responsible for generating and saving receipts
 * for customer orders. It provides functionality to create receipt content
 * from an Order and write it to a timestamped text file.
 */

public class ReceiptManager {

    //Generates a formatted receipt string from an Order object
    public String generateReceipt(Order order) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("DELI-cious Receipt\n");
        receipt.append("==================\n");
        receipt.append(order.getOrderDetails());
        receipt.append("\n\nThank you for your business!");
        return receipt.toString();
    }



    // Saves the given receipt string to a text file in the "receipts" directory,
    // using a timestamp-based filename.
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

    //Generates a unique filename using the current date and time
    private String generateFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return java.time.LocalDateTime.now().format(formatter) + ".txt";
    }
}
