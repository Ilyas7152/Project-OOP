package model;

public class FreshProduct extends Product {
    private int daysToExpire;

    public FreshProduct(double price, int id, String name, int stock, int daysToExpire) {
        super(price, id, name, stock);
        setDaysToExpire(daysToExpire);
    }

    public int getDaysToExpire() { return daysToExpire; }

    public void setDaysToExpire(int days) {
        if (days >= 0) this.daysToExpire = days;
    }

    @Override
    public String getType() { return "Fresh Product"; }
}

