package model;

public class Product {
    private double price;
    private int id;
    private String name;
    private int stock;



    public Product(double price, int id, String name, int stock) {
        this.price = price;
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean sellProduct(int amount) {
        if (stock >= amount) {
            stock -= amount;
            return true;
        }
        System.out.println("Unfortunately we can't sell it to you because we don't have so many products in stock");
        return false;
    }

    public String stockStatus() {
       if(stock>0){
           return "in Stock";
       }
       return "out of Stock";
    }

    @Override
    public String toString(){
        return "(price: "+price + ", id: "+id +",  name: "+ name + ", stock: "+stock+")";
    }

}

