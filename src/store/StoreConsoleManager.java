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
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 0) break;

                switch (choice) {
                    case 1 : showList(products);
                        break;
                    case 2 : addNewProduct();break;
                    case 3 : showList(customers);break;
                    case 4 : addNewCustomer();break;
                    case 5 : showList(orders);break;
                    case 6 : createAndCompleteOrder();break;
                    default : System.out.println("Invalid choice!");
                }

            } catch (InvalidInputException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Validation error: " + e.getMessage());
            }
        }

        scanner.close();
    }
    private void addNewCustomer()  {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        customers.add(new Customer(customers.size() + 1, name, phone, balance));
        System.out.println("Customer added!");
    }

    private void addNewProduct()  {
        System.out.println("1) Fresh product");
        System.out.println("2) Packaged product");
        System.out.print("Type: ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        int id = products.size() + 1;

        if (type == 1) {
            System.out.print("Days to expire: ");
            int days = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Discount% (0..100): ");
            double disc = scanner.nextDouble();
            scanner.nextLine();

            products.add(new FreshProduct(price, id, name, stock, days, disc));

        } else if (type == 2) {
            System.out.print("Brand: ");
            String brand = scanner.nextLine();

            System.out.print("Discount% (0..100): ");
            double disc = scanner.nextDouble();
            scanner.nextLine();

            products.add(new PackagedProduct(price, id, name, stock, brand, disc));

        } else {
            System.out.println("Unknown type.");
            return;
        }

        System.out.println("Product added!");
    }

    private void createAndCompleteOrder() throws InvalidInputException {


        System.out.println("Customers:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ") " + customers.get(i));
        }

        System.out.print("Choose customer: ");
        int cIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.println("\nProducts:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ") " + products.get(i));
        }

        System.out.print("Choose product: ");
        int pIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Quantity: ");
        int qty = scanner.nextInt();
        scanner.nextLine();

        Customer c = customers.get(cIndex);
        Product p = products.get(pIndex);

        Order order = new Order(orders.size() + 100, p, c, qty);
        order.completeOrder();

        orders.add(order);
        System.out.println("Order completed: " + order);
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
