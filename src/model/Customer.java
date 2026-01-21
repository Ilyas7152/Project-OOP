package model;

public class Customer {
    private int id;
    private String name;
    private double money;
    private String phoneNum;

    public Customer(int id, String name, String phoneNum, double money) {
        setId(id);
        setName(name);
        setPhoneNum(phoneNum);
        setMoney(money);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getMoney() { return money; }
    public String getPhoneNum() { return phoneNum; }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("Customer id must be > 0");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Customer name cannot be empty");
        this.name = name.trim();
    }

    public void setMoney(double money) {
        if (money < 0) throw new IllegalArgumentException("Money cannot be negative");
        this.money = money;
    }

    public void setPhoneNum(String phoneNum) {
        if (phoneNum == null || phoneNum.trim().isEmpty())
            throw new IllegalArgumentException("Phone cannot be empty");
        this.phoneNum = phoneNum.trim();
    }

    public void pay(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        if (money < amount) throw new IllegalArgumentException("Not enough money");
        money -= amount;
    }

    public void addMoney(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be > 0");
        money += amount;
    }

    @Override
    public String toString() {
        return "(id: " + id + ", name: " + name + ", phoneNum: " + phoneNum + ", money: " + money + ")";
    }
}
