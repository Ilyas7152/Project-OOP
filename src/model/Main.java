package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();

        while (true) {
            System.out.println("\n1. View Products  2. Add Product");
            System.out.println("3. View Customers 4. Add Customer");
            System.out.println("5. View Orders    6. Filter Fresh");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            switch (choice) {
                case 1 -> showList(products);
                case 2 -> addNewProduct();
                case 3 -> showList(customers);
                case 4 -> addNewCustomer();
                case 5 -> showList(orders);
                case 6 -> filterFresh();
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addNewCustomer() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        customers.add(new Customer(customers.size() + 1, name, phone, balance));
    }

    private static void addNewProduct() {
        System.out.println("1.General 2.Fresh 3.Packaged");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        int id = products.size() + 1;
        if (type == 2) {
            System.out.print("Days: ");
            int days = scanner.nextInt();
            scanner.nextLine();
            products.add(new FreshProduct(price, id, name, stock, days));
        } else if (type == 3) {
            System.out.print("Brand: ");
            String brand = scanner.nextLine();
            products.add(new PackagedProduct(price, id, name, stock, brand));
        } else {
            products.add(new Product(price, id, name, stock));
        }
    }

    private static void showList(ArrayList<?> list) {
        for (Object o : list) System.out.println(o);
    }

    private static void filterFresh() {
        for (Product p : products) {
            if (p instanceof FreshProduct fp) {
                System.out.println(fp);
            }
        }
    }

    private static void initializeData() {
        Product p = new Product(100, 1, "Milk", 10);
        Customer c = new Customer(1, "John", "123", 500);
        products.add(p);
        customers.add(c);
        orders.add(new Order(101, p, c, 2));
    }
}