package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;


    public Product(double price, int id, String name, int stock) {
        setId(id);
        setName(name);
        setPrice(price);
        setStock(stock);
    }

    public Product(String name, double price, int stock) {
        setName(name);
        setPrice(price);
        setStock(stock);
        this.id = 0;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("Product id must be > 0");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Product name cannot be empty");
        this.name = name.trim();
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stock = stock;
    }

    public String getType() {
        return "Product";
    }

    public double calculateTotal(int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        return price * qty;
    }

    public void sellProduct(int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        if (qty > stock) throw new IllegalArgumentException("Not enough stock");
        stock -= qty;
    }

    public boolean isLowStock() {
        return stock <= 5;
    }

    @Override
    public String toString() {
        return "Product(id=" + id + ", name=" + name + ", stock=" + stock + ", price=" + price + ")";
    }
}
