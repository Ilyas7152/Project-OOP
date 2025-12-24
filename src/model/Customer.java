package model;

public class Customer {
    private int id;
    private String name;
    private double money;
    private String phoneNum;

    public Customer(int id, String name, String phoneNum, double money){
        this.id= id;
        this.name=name;
        this.phoneNum=phoneNum;
        this.money=money;

    }

    public int getid() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    public boolean pay(double amount){
        if(money>=amount){
            money -=amount;
            return true;
    }

    return false;}
    public void addMoney(double amount){
        money+=amount;
    }
    @Override
    public String toString(){
        return "(id :" +id + ", name: "+ name +", phoneNum :"+phoneNum+", money :"+money+")";
    }
    }



