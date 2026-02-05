package store;

import database.ProductDao;
import exception.InvalidInputException;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreConsoleManager implements StoreConsole {
    private final ArrayList<Customer> customers = new ArrayList<>();
    private final ArrayList<Order> orders = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final ProductDao productDao = new ProductDao();



    @Override
    public void displayMenu() {
        System.out.println("\n===== GROCERY STORE (Week 8) =====");
        System.out.println("1) Add Fresh Product");
        System.out.println("2) Add Packaged Product");
        System.out.println("3) View All Products");
        System.out.println("4) Add new customer");
        System.out.println("5) Show orders");
        System.out.println("6) Show customers");
        System.out.println("7) Update product");
        System.out.println("8) Delete product");
        System.out.println("9) Search by Name");
        System.out.println("10) Search by Price Range");
        System.out.println("11) Search by minimum Price");
        System.out.println("12) Polymorphism demo");
        System.out.println("0) Exit");
        System.out.print("Choice: ");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addFreshProduct();
                    case 2 -> addPackagedProduct();
                    case 3 -> readAllProducts();
                    case 4 -> addNewCustomer();
                    case 5 ->showList(orders);
                    case 6 -> showList(customers);
                    case 7 -> updateProduct();
                    case 8 -> deleteProduct();
                    case 9 -> searchByName();
                    case 10 -> searchByPriceRange();
                    case 11 -> searchByMinPrice();
                    case 12 -> demonstratePolymorphism();

                    case 0 -> running = false;
                    default -> System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
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

    private void addFreshProduct() throws SQLException {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        System.out.print("Days to expire: ");
        int days = Integer.parseInt(scanner.nextLine());

        System.out.print("Discount%: ");
        double discount = Double.parseDouble(scanner.nextLine());

        FreshProduct product =
                new FreshProduct(price, id, name, stock, days, discount);

        productDao.createProduct(product);
        System.out.println("✅ Fresh product added");
    }

    private void addPackagedProduct() throws SQLException {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        System.out.print("Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Discount%: ");
        double discount = Double.parseDouble(scanner.nextLine());

        PackagedProduct product =
                new PackagedProduct(price, id, name, stock, brand, discount);

        productDao.createProduct(product);
        System.out.println("✅ Packaged product added");
    }


    private void readAllProducts() throws SQLException {
        List<Product> products = productDao.readAllProducts();
        if (products.isEmpty()) {
            System.out.println("📭 No products in database.");
            return;
        }
        products.forEach(System.out::println);
    }



    private void updateProduct() throws SQLException {
        System.out.print("Product ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("New name: ");
        String name = scanner.nextLine();

        System.out.print("New stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        System.out.print("New price: ");
        double price = Double.parseDouble(scanner.nextLine());

        boolean ok = productDao.updateProductBasic(id, name, stock, price);
        System.out.println(ok ? "✅ Updated" : "❌ Product not found");
    }




    private void deleteProduct() throws SQLException {
        System.out.print("Product ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Are you sure? (yes/no): ");
        if (!scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Cancelled");
            return;
        }

        boolean ok = productDao.deleteProductById(id);
        System.out.println(ok ? "✅ Deleted" : "❌ Not found");
    }

    private void showList(ArrayList<?> list) {
        if (list.isEmpty()) {
            System.out.println("Empty list");
            return;
        }
        for (Object o : list) System.out.println(o);
    }




    private void searchByName() throws SQLException {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        List<Product> results = productDao.searchByName(name);
        results.forEach(System.out::println);
    }

    private void searchByPriceRange() {
        System.out.print("Min price: ");
        double min = Double.parseDouble(scanner.nextLine());

        System.out.print("Max price: ");
        double max = Double.parseDouble(scanner.nextLine());

        List<Product> results = productDao.searchByPriceRange(min, max);
        results.forEach(System.out::println);
    }
    private void searchByMinPrice() {
        System.out.print("Enter minimum price: ");
        double minPrice = Double.parseDouble(scanner.nextLine());

        List<Product> results = productDao.searchByMinPrice(minPrice);

        if (results.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        results.forEach(System.out::println);
    }



    private void demonstratePolymorphism() throws SQLException {
        List<Product> products = productDao.readAllProducts();

        System.out.print("Quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());

        for (Product p : products) {
            System.out.println(p.getType() + " -> " + p.calculateTotal(qty));
        }
    }
}
