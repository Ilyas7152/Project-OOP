package model;

public class Order {
    private int Id;
    private Product product;
    private Customer customer;
    private int quantity;


    public Order(int Id, Product product, Customer customer, int quantity) {

        this.Id = Id;
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;

    }

    public int getId() {
        return Id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setOrderId(int Id) {
        this.Id = Id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        double total= product.getPrice() * quantity;
        if (quantity >=7){
            total *=0.93;
        } else if (quantity>=3) {
            total *=0.97;
            
        }
        return total;
    }

    public boolean completeOrder() {
        double total = getTotalPrice();
        if (product.sellProduct(quantity) && customer.pay(total))
        {System.out.println("Order was completed");
            return true;
        }
        System.out.println("Order was failed");
        return false;
    }
    @Override
    public String toString(){
        return "Order(Id: " +Id+", product: "+product+", Customer :"+ customer+",quantity :"+quantity+")";
    }
    }


