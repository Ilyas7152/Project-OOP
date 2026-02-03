package model;

public class PackagedProduct extends Product implements Discountable {
    private String brand;
    private double discountPercent;

    public PackagedProduct(double price, int id, String name, int stock, String brand, double discountPercent) {
        super(price, id, name, stock);
        setBrand(brand);
        setDiscountPercent(discountPercent);
    }

    public String getBrand() { return brand; }

    public void setBrand(String brand) {
        if (brand == null)
            throw new IllegalArgumentException("Brand cannot be empty");
        this.brand = brand.trim();
    }

    public void setDiscountPercent(double discountPercent) {
        if (discountPercent < 0 || discountPercent > 100)
            throw new IllegalArgumentException("Discount must be between 0 and 100");
        this.discountPercent = discountPercent;
    }

    @Override
    public double getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public String getType() { return "Packaged Product"; }
}
