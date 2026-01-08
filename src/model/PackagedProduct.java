package model;

public class PackagedProduct extends Product {
    private String brand;

    public PackagedProduct(double price, int id, String name, int stock, String brand) {
        super(price, id, name, stock);
        this.brand = brand;
    }

    @Override
    public String getType() { return "Packaged Product"; }
}