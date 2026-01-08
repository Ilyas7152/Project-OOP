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

    public void setId(int id) {
        if (id > 0) this.id = id;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) this.quantity = quantity;
    }

    public void setProduct(Product product) { this.product = product; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public double getTotalPrice() {
        double total = product.getPrice() * quantity;
        if (quantity >= 7) total *= 0.93;
        else if (quantity >= 3) total *= 0.97;
        return total;
    }

    public boolean completeOrder() {
        double total = getTotalPrice();
        if (product.sellProduct(quantity) && customer.pay(total)) {
            System.out.println("Order was completed");
            return true;
        }
        System.out.println("Order failed");
        return false;
    }

    @Override
    public String toString() {
        return "Order(id: " + id + ", product: " + product.getName() + ", customer: " + customer.getName() + ", qty: " + quantity + ")";
    }
}