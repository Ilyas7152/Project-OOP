package store;

import exception.InvalidInputException;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class StoreConsoleManager implements StoreConsole {

    private final ArrayList<Product> products = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Order> orders = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public StoreConsoleManager() {
        initializeData();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n===== STORE SYSTEM =====");
        System.out.println("1) View Products");
        System.out.println("2) Add Product");
        System.out.println("3) View Customers");
        System.out.println("4) Add Customer");
        System.out.println("5) View Orders");
        System.out.println("6) Filter Fresh Products");
        System.out.println("7) Create Order & Complete");
        System.out.println("0) Exit");
        System.out.print("Choice: ");
    }

    @Override
    public void run() {
        while (true) {
            displayMenu();

            try {
                int choice = readInt();
                if (choice == 0) break;

                switch (choice) {
                    case 1 -> showList(products);
                    case 2 -> addNewProduct();
                    case 3 -> showList(customers);
                    case 4 -> addNewCustomer();
                    case 5 -> showList(orders);
                    case 6 -> filterFresh();
                    case 7 -> createAndCompleteOrder();
                    default -> System.out.println("Invalid choice!");
                }

            } catch (InvalidInputException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Validation error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        scanner.close();
    }


    private int readInt() throws InvalidInputException {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new InvalidInputException("Expected integer number");
        }
    }

    private double readDouble() throws InvalidInputException {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (Exception e) {
            throw new InvalidInputException("Expected numeric value");
        }
    }

    // it is menu act.
    private void addNewCustomer() throws InvalidInputException {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Balance: ");
        double balance = readDouble();

        customers.add(new Customer(customers.size() + 1, name, phone, balance));
        System.out.println("Customer added!");
    }

    private void addNewProduct() throws InvalidInputException {
        System.out.println("1) Fresh product");
        System.out.println("2) Packaged product");
        System.out.print("Type: ");
        int type = readInt();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = readDouble();

        System.out.print("Stock: ");
        int stock = readInt();

        int id = products.size() + 1;

        if (type == 1) {
            System.out.print("Days to expire: ");
            int days = readInt();

            System.out.print("Discount% (0..100): ");
            double disc = readDouble();

            products.add(new FreshProduct(price, id, name, stock, days, disc));

        } else if (type == 2) {
            System.out.print("Brand: ");
            String brand = scanner.nextLine();

            System.out.print("Discount% (0..100): ");
            double disc = readDouble();

            products.add(new PackagedProduct(price, id, name, stock, brand, disc));

        } else {
            System.out.println("Unknown type.");
            return;
        }

        System.out.println("Product added!");
    }

    private void createAndCompleteOrder() throws InvalidInputException {
        if (customers.isEmpty() || products.isEmpty()) {
            System.out.println("Add at least 1 customer and 1 product first.");
            return;
        }

        System.out.println("Customers:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ") " + customers.get(i));
        }

        System.out.print("Choose customer: ");
        int cIndex = readInt() - 1;

        System.out.println("\nProducts:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ") " + products.get(i));
        }

        System.out.print("Choose product: ");
        int pIndex = readInt() - 1;

        System.out.print("Quantity: ");
        int qty = readInt();

        Customer c = customers.get(cIndex);
        Product p = products.get(pIndex);

        Order order = new Order(orders.size() + 100, p, c, qty);
        order.completeOrder();

        orders.add(order);
        System.out.println("Order completed: " + order);
    }

    private void filterFresh() {
        boolean found = false;
        for (Product p : products) {
            if (p instanceof FreshProduct) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) System.out.println("No fresh products found.");
    }

    private void showList(ArrayList<?> list) {
        if (list.isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        for (Object o : list) System.out.println(o);
    }

    private void initializeData() {
        products.add(new FreshProduct(100, 1, "Milk", 10, 5, 10));
        customers.add(new Customer(1, "John", "123", 500));
        orders.add(new Order(101, products.get(0), customers.get(0), 2));
    }
}
