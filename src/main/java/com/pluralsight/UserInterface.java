package com.pluralsight;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Order currentOrder;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.currentOrder = null;
    }

    public void start() {
        System.out.println("Welcome to DELI-cious!");
        System.out.println("======================");

        boolean running = true;
        while (running) {
            running = displayHomeScreen();
        }

        System.out.println("Thank you for visiting DELI-cious!");
        scanner.close();
    }

    private boolean displayHomeScreen() {
        System.out.println("\n--- HOME SCREEN ---");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("Please select an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    startNewOrder();
                    break;
                case 0:
                    return false; // Exit application
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }

        return true; // Continue running
    }

    private void startNewOrder() {
        currentOrder = new Order();
        System.out.println("\n--- NEW ORDER STARTED ---");
        System.out.println("Order ID: " + currentOrder.getOrderId());

        boolean orderActive = true;
        while (orderActive) {
            orderActive = displayOrderScreen();
        }
    }

    private boolean displayOrderScreen() {
        System.out.println("\n--- ORDER SCREEN ---");
        System.out.println("Current Order Total: $" + String.format("%.2f", currentOrder.getTotalPrice()));
        System.out.println("Items in order: " + currentOrder.getItems().size());

        System.out.println("\n1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        System.out.print("Please select an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    addSandwich();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addChips();
                    break;
                case 4:
                    checkout();
                    return false; // End order screen
                case 0:
                    cancelOrder();
                    return false; // End order screen
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }

        return true; // Continue order screen
    }

    private void addSandwich() {
        System.out.println("\n--- ADD SANDWICH ---");
        System.out.println("(Sandwich customization will be implemented next)");
        System.out.println("For now, adding a basic 8\" sandwich...");

        // Placeholder - will be fully implemented later
        Sandwich sandwich = new Sandwich(8, "white");
        currentOrder.addItem(sandwich);
        System.out.println("Sandwich added to order!");
    }

    private void addDrink() {
        System.out.println("\n--- ADD DRINK ---");
        System.out.println("(Drink selection will be implemented next)");
        System.out.println("For now, adding a medium drink...");

        // Placeholder - will be fully implemented later
        Drink drink = new Drink("Medium", "Coke");
        currentOrder.addItem(drink);
        System.out.println("Drink added to order!");
    }

    private void addChips() {
        System.out.println("\n--- ADD CHIPS ---");
        System.out.println("(Chip selection will be implemented next)");
        System.out.println("For now, adding regular chips...");

        // Placeholder - will be fully implemented later
        Chips chips = new Chips("Regular");
        currentOrder.addItem(chips);
        System.out.println("Chips added to order!");
    }

    private void checkout() {
        System.out.println("\n--- CHECKOUT ---");
        System.out.println("Order Details:");
        System.out.println("==============");

        for (OrderItem item : currentOrder.getItems()) {
            System.out.println("- " + item.getDescription() + " - $" + String.format("%.2f", item.getPrice()));
        }

        System.out.println("==============");
        System.out.println("Total: $" + String.format("%.2f", currentOrder.getTotalPrice()));

        System.out.println("\n1) Confirm Order");
        System.out.println("0) Cancel");
        System.out.print("Please select an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    confirmOrder();
                    break;
                case 0:
                    System.out.println("Returning to order screen...");
                    break;
                default:
                    System.out.println("Invalid option. Returning to order screen...");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Returning to order screen...");
        }
    }

    private void confirmOrder() {
        ReceiptManager receiptManager = new ReceiptManager();
        String receipt = receiptManager.generateReceipt(currentOrder);
        boolean saved = receiptManager.saveReceiptToFile(receipt);

        if (saved) {
            System.out.println("Order confirmed! Receipt saved successfully.");
            System.out.println("Thank you for your order!");
        } else {
            System.out.println("Order confirmed! (Receipt could not be saved)");
        }

        currentOrder = null; // Clear the current order
    }

    private void cancelOrder() {
        System.out.println("Order cancelled. Returning to home screen...");
        currentOrder = null; // Clear the current order
    }
}