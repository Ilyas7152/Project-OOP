package model;

public class Product {
   protected double price;
    protected int id;
    protected String name;
    protected int stock;

    public Product(double price, int id, String name, int stock) {
        setPrice(price);
        setId(id);
        setName(name);
        setStock(stock);
    }

    public int getId() { return id; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getName() { return name; }

    public void setId(int id) {
        if (id >= 0) this.id = id;
        else System.out.println("Error: ID can't be negative!");
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) this.name = name;
        else System.out.println("Error: Name cannot be empty!");
    }

    public void setStock(int stock) {
        if (stock >= 0) this.stock = stock;
        else System.out.println("Error: Stock can't be negative!");
    }

    public void setPrice(double price) {
        if (price >= 0) this.price = price;
        else System.out.println("Error: Price can't be negative!");
    }

    public boolean sellProduct(int amount) {
        if (amount > 0 && stock >= amount) {
            stock -= amount;
            return true;
        }
        System.out.println("Insufficient stock!");
        return false;
    }

    public String getType() { return "Product"; }



    @Override
    public String toString() {
        return "[" + getType() + "] " +
                "(price: " + price +
                ", id: " + id +
                ", name: " + name +
                ", stock: " + stock + ")";
    }
}
