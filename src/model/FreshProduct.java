package model;

public class FreshProduct extends Product implements Discountable {
    private int daysToExpire;
    private double discountPercent; // 0..100

    public FreshProduct(double price, int id, String name, int stock, int daysToExpire, double discountPercent) {
        super(price, id, name, stock);
        setDaysToExpire(daysToExpire);
        setDiscountPercent(discountPercent);
    }

    public int getDaysToExpire() { return daysToExpire; }

    public void setDaysToExpire(int days) {
        if (days < 0) throw new IllegalArgumentException("Days to expire cannot be negative");
        this.daysToExpire = days;
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
    public String getType() {
        return "Fresh Product";
    }


    @Override
    public double calculateTotal(int qty) {
        double total = super.calculateTotal(qty);
        total *= (1 - discountPercent / 100.0);
        return total;
    }


    public boolean isExpiringSoon() {
        return daysToExpire <= 3;
    }
}
