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
        System.out.println("""
    ╔══════════════════════════════════════╗
    ║         WELCOME TO DELI-cious        ║
    ║    Home of Handcrafted Sandwiches    ║
    ╚══════════════════════════════════════╝
    """);
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

        // Step 1: Select size
        int size = selectSandwichSize();
        if (size == -1) return; // User cancelled

        // Step 2: Select bread
        String breadType = selectBreadType();
        if (breadType == null) return; // User cancelled

        // Create sandwich
        Sandwich sandwich = new Sandwich(size, breadType);

        // Step 3: Add meats
        addMeatsToSandwich(sandwich);

        // Step 4: Add cheese
        addCheeseToSandwich(sandwich);

        // Step 5: Add regular toppings
        addRegularToppingsToSandwich(sandwich);

        // Step 6: Add sauces
        addSaucesToSandwich(sandwich);

        // Step 7: Add sides
        addSidesToSandwich(sandwich);

        // Step 8: Ask about toasting
        askAboutToasting(sandwich);

        // Add to order
        currentOrder.addItem(sandwich);
        System.out.println("\nSandwich added to order!");
        System.out.println("Sandwich total: $" + String.format("%.2f", sandwich.getPrice()));
    }

    private int selectSandwichSize() {
        System.out.println("\nSelect sandwich size:");
        System.out.println("1) 4\" - $5.50");
        System.out.println("2) 8\" - $7.00");
        System.out.println("3) 12\" - $8.50");
        System.out.println("0) Cancel");
        System.out.print("Choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1: return 4;
                case 2: return 8;
                case 3: return 12;
                case 0: return -1;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    return selectSandwichSize();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectSandwichSize();
        }
    }

    private String selectBreadType() {
        System.out.println("\nSelect bread type:");
        System.out.println("1) White");
        System.out.println("2) Wheat");
        System.out.println("3) Rye");
        System.out.println("4) Wrap");
        System.out.println("0) Cancel");
        System.out.print("Choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1: return "White";
                case 2: return "Wheat";
                case 3: return "Rye";
                case 4: return "Wrap";
                case 0: return null;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    return selectBreadType();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectBreadType();
        }
    }

    private void addMeatsToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect meats (Enter 0 when done):");
        String[] meats = {"Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"};

        while (true) {
            System.out.println("\nAvailable meats:");
            for (int i = 0; i < meats.length; i++) {
                double price = new PremiumTopping(meats[i], sandwich.getSize(), "meat").getPrice();
                System.out.println((i + 1) + ") " + meats[i] + " - $" + String.format("%.2f", price));
            }
            System.out.println("0) Done with meats");
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 0) break;
                if (choice >= 1 && choice <= meats.length) {
                    PremiumTopping meat = new PremiumTopping(meats[choice - 1], sandwich.getSize(), "meat");
                    sandwich.addTopping(meat);
                    System.out.println(meats[choice - 1] + " added!");
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Ask about extra meat if any meat was added
        if (sandwich.getToppings().stream().anyMatch(t -> t instanceof PremiumTopping &&
                ((PremiumTopping) t).getCategory().equals("meat"))) {
            askAboutExtraMeat(sandwich);
        }
    }

    private void addCheeseToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect cheese (Enter 0 when done):");
        String[] cheeses = {"American", "Provolone", "Cheddar", "Swiss"};

        while (true) {
            System.out.println("\nAvailable cheeses:");
            for (int i = 0; i < cheeses.length; i++) {
                double price = new PremiumTopping(cheeses[i], sandwich.getSize(), "cheese").getPrice();
                System.out.println((i + 1) + ") " + cheeses[i] + " - $" + String.format("%.2f", price));
            }
            System.out.println("0) Done with cheese");
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 0) break;
                if (choice >= 1 && choice <= cheeses.length) {
                    PremiumTopping cheese = new PremiumTopping(cheeses[choice - 1], sandwich.getSize(), "cheese");
                    sandwich.addTopping(cheese);
                    System.out.println(cheeses[choice - 1] + " added!");
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Ask about extra cheese if any cheese was added
        if (sandwich.getToppings().stream().anyMatch(t -> t instanceof PremiumTopping &&
                ((PremiumTopping) t).getCategory().equals("cheese"))) {
            askAboutExtraCheese(sandwich);
        }
    }

    private void addRegularToppingsToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect regular toppings (FREE - Enter 0 when done):");
        String[] regularToppings = {"Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeños",
                "Cucumbers", "Pickles", "Guacamole", "Mushrooms"};

        while (true) {
            System.out.println("\nAvailable regular toppings:");
            for (int i = 0; i < regularToppings.length; i++) {
                System.out.println((i + 1) + ") " + regularToppings[i] + " - FREE");
            }
            System.out.println("0) Done with regular toppings");
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 0) break;
                if (choice >= 1 && choice <= regularToppings.length) {
                    RegularTopping topping = new RegularTopping(regularToppings[choice - 1], sandwich.getSize());
                    sandwich.addTopping(topping);
                    System.out.println(regularToppings[choice - 1] + " added!");
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void addSaucesToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect sauces (FREE - Enter 0 when done):");
        String[] sauces = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"};

        while (true) {
            System.out.println("\nAvailable sauces:");
            for (int i = 0; i < sauces.length; i++) {
                System.out.println((i + 1) + ") " + sauces[i] + " - FREE");
            }
            System.out.println("0) Done with sauces");
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 0) break;
                if (choice >= 1 && choice <= sauces.length) {
                    RegularTopping sauce = new RegularTopping(sauces[choice - 1] + " sauce", sandwich.getSize());
                    sandwich.addTopping(sauce);
                    System.out.println(sauces[choice - 1] + " added!");
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void addSidesToSandwich(Sandwich sandwich) {
        System.out.println("\nSelect sides (FREE - Enter 0 when done):");
        String[] sides = {"Au Jus", "Sauce"};

        while (true) {
            System.out.println("\nAvailable sides:");
            for (int i = 0; i < sides.length; i++) {
                System.out.println((i + 1) + ") " + sides[i] + " - FREE");
            }
            System.out.println("0) Done with sides");
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 0) break;
                if (choice >= 1 && choice <= sides.length) {
                    RegularTopping side = new RegularTopping(sides[choice - 1], sandwich.getSize());
                    sandwich.addTopping(side);
                    System.out.println(sides[choice - 1] + " added!");
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void askAboutExtraMeat(Sandwich sandwich) {
        double extraCost = sandwich.getExtraMeatPrice();
        System.out.println("\nWould you like extra meat? (+$" + String.format("%.2f", extraCost) + ")");
        System.out.println("1) Yes");
        System.out.println("2) No");
        System.out.print("Choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 1) {
                sandwich.setExtraMeat(true);
                System.out.println("Extra meat added!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Skipping extra meat.");
        }
    }

    private void askAboutExtraCheese(Sandwich sandwich) {
        double extraCost = sandwich.getExtraCheesePrice();
        System.out.println("\nWould you like extra cheese? (+$" + String.format("%.2f", extraCost) + ")");
        System.out.println("1) Yes");
        System.out.println("2) No");
        System.out.print("Choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 1) {
                sandwich.setExtraCheese(true);
                System.out.println("Extra cheese added!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Skipping extra cheese.");
        }
    }

    private void askAboutToasting(Sandwich sandwich) {
        System.out.println("\nWould you like your sandwich toasted?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        System.out.print("Choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 1) {
                sandwich.setToasted(true);
                System.out.println("Sandwich will be toasted!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Sandwich will not be toasted.");
        }
    }

    private void addDrink() {
        System.out.println("\n--- ADD DRINK ---");

        // Step 1: Select size
        String size = selectDrinkSize();
        if (size == null) return; // User cancelled

        // Step 2: Select flavor
        String flavor = selectDrinkFlavor();
        if (flavor == null) return; // User cancelled

        // Create and add drink
        Drink drink = new Drink(size, flavor);
        currentOrder.addItem(drink);
        System.out.println("\nDrink added to order!");
        System.out.println("Drink total: $" + String.format("%.2f", drink.getPrice()));
    }

    private String selectDrinkSize() {
        System.out.println("\nSelect drink size:");
        System.out.println("1) Small - $2.00");
        System.out.println("2) Medium - $2.50");
        System.out.println("3) Large - $3.00");
        System.out.println("0) Cancel");
        System.out.print("Choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1: return "Small";
                case 2: return "Medium";
                case 3: return "Large";
                case 0: return null;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    return selectDrinkSize();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectDrinkSize();
        }
    }

    private String selectDrinkFlavor() {
        System.out.println("\nSelect drink flavor:");
        String[] flavors = {"Coke", "Pepsi", "Sprite", "Orange", "Lemonade", "Iced Tea", "Water"};

        for (int i = 0; i < flavors.length; i++) {
            System.out.println((i + 1) + ") " + flavors[i]);
        }
        System.out.println("0) Cancel");
        System.out.print("Choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) return null;
            if (choice >= 1 && choice <= flavors.length) {
                return flavors[choice - 1];
            } else {
                System.out.println("Invalid choice. Please try again.");
                return selectDrinkFlavor();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectDrinkFlavor();
        }
    }

    private void addChips() {
        System.out.println("\n--- ADD CHIPS ---");

        String chipType = selectChipType();
        if (chipType == null) return; // User cancelled

        // Create and add chips
        Chips chips = new Chips(chipType);
        currentOrder.addItem(chips);
        System.out.println("\nChips added to order!");
        System.out.println("Chips total: $" + String.format("%.2f", chips.getPrice()));
    }

    private String selectChipType() {
        System.out.println("\nSelect chip type (All chips - $1.50):");
        String[] chipTypes = {"Regular", "BBQ", "Sour Cream & Onion", "Salt & Vinegar",
                "Jalapeno", "Nacho Cheese", "Cool Ranch", "Flamin' Hot"};

        for (int i = 0; i < chipTypes.length; i++) {
            System.out.println((i + 1) + ") " + chipTypes[i]);
        }
        System.out.println("0) Cancel");
        System.out.print("Choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice == 0) return null;
            if (choice >= 1 && choice <= chipTypes.length) {
                return chipTypes[choice - 1];
            } else {
                System.out.println("Invalid choice. Please try again.");
                return selectChipType();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return selectChipType();
        }
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
                    // Save receipt only after confirmation
                    currentOrder.saveReceiptToFile();
                    System.out.println("Receipt saved. Thank you for your order!");

                    confirmOrder(); // This can clear/reset order if needed
                    currentOrder = null; // Clean up
                    break;

                case 0:
                    System.out.println("Checkout cancelled. Returning to order screen...");
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