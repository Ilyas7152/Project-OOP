package model;

public abstract class Product {
    protected double price;
    protected int id;
    protected String name;
    protected int stock;

    protected Product(double price, int id, String name, int stock) {
        setPrice(price);
        setId(id);
        setName(name);
        setStock(stock);
    }

    public abstract String getType();

    public int getId() { return id; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getName() { return name; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be > 0");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name.trim();
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stock = stock;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public void sellProduct(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        if (stock < amount) throw new IllegalArgumentException("Insufficient stock");
        stock -= amount;
    }

    @Override
    public String toString() {
        return "[" + getType() + "] " +
                "(price: " + price +
                ", id: " + id +
                ", name: " + name +
                ", stock: " + stock + ")";
    }
}
