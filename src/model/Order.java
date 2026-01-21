package model;

public class Order {
    private int id;
    private Product product;
    private Customer customer;
    private int quantity;

    public Order(int id, Product product, Customer customer, int quantity) {
        setId(id);
        setProduct(product);
        setCustomer(customer);
        setQuantity(quantity);
    }

    public int getId() { return id; }
    public int getQuantity() { return quantity; }
    public Product getProduct() { return product; }
    public Customer getCustomer() { return customer; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("Order id must be > 0");
        this.id = id;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null");
        this.product = product;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) throw new IllegalArgumentException("Customer cannot be null");
        this.customer = customer;
    }

    public double getTotalPrice() {
        double total = product.getPrice() * quantity;

        if (quantity >= 7) total *= 0.93;
        else if (quantity >= 3) total *= 0.97;

        if (product instanceof Discountable d) {
            total *= (1 - d.getDiscountPercent() / 100.0);
        }

        return total;
    }

    public void completeOrder() {
        double total = getTotalPrice();
        product.sellProduct(quantity);
        customer.pay(total);
    }

    @Override
    public String toString() {
        return "Order(id: " + id +
                ", product: " + product.getName() +
                ", customer: " + customer.getName() +
                ", qty: " + quantity +
                ", total: " + getTotalPrice() + ")";
    }
}
