package model;

import java.awt.*;

public class Main {
     public static void main(String[] args) {
     System.out.println("---Grocery Store Management System");
         System.out.println();
     Product n1=new Product(250,1,"apple",30);
     Product n2=new Product(300,2,"banana",30);
     Product n3=new Product(700,130,"chocolate",50);
         System.out.println();
     Customer c1=new Customer(11,"Ivan","8777321201",1500);
     Customer c2=new Customer(22,"Alexandr","8000010101",2000);
     Customer c3=new Customer(33,"Ruslan","8558430190",5000);
         System.out.println();
     Order o1=new Order(111,n1,c1,20);
     Order o2=new Order(222,n3,c3,7);

         System.out.println();
     System.out.println("---Menu Products---");
     System.out.println(n1);
     System.out.println(n2);
     System.out.println(n3);
         System.out.println();

     System.out.println("---Menu Customers---");
     System.out.println(c1);
     System.out.println(c2);
     System.out.println(c3);
         System.out.println();
     System.out.println("---Menu Orders---");
     System.out.println(o1);
     System.out.println(o2);
         System.out.println();
     System.out.println("--- TESTING GETTERS ---");
         System.out.println("Product name: " + n1.getName());
         System.out.println("Product price: " + n1.getPrice());
         System.out.println("Product id:"+n1.getId());
         System.out.println("Product stock:"+n1.getStock());
         System.out.println("Customer name: " + c1.getName());
         System.out.println("Customer id:"+c1.getid());
         System.out.println("Customer phoneNum:"+c1.getPhoneNum());
         System.out.println("Customer money: " + c1.getMoney());
         System.out.println("Order quantity: " + o1.getQuantity());

         System.out.println();

         System.out.println("---Testing setters---");
         System.out.println("Updating item3...");
         n1.setName("Tea");
         n1.setId(7);
         n1.setPrice(377.77);
         n1.setStock(15);
         System.out.println("Updated:"+n1);
         System.out.println();

         System.out.println("---TESTING CUSTOMER METHODS---");
         c1.pay(1000);
         System.out.println("Ivan bought 4 apples and our new balance is " + c1.getMoney());
         c1.addMoney(2000);
         System.out.println("Ivan added money to his account "+ c1.getMoney());
         System.out.println();


         System.out.println("--- TESTING PRODUCT METHODS ---");
         n1.sellProduct(3);
         System.out.println(n1.getStock());
         n1.stockStatus();
         System.out.println(n1.stockStatus());
         System.out.println();

         System.out.println("---TESTING ORDER METHODS---");

         System.out.println("Total price of order is:" + o1.getTotalPrice());
 o1.completeOrder();
         System.out.println();


 System.out.println("---FINAL STATE---");
 System.out.println("Products in stock:");
         System.out.println(n1);
         System.out.println(n2);
         System.out.println(n3);
         System.out.println();

         System.out.println("Customers:");
         System.out.println(c1);
         System.out.println(c2);
         System.out.println(c3);
         System.out.println();

         System.out.println("Orders:");
         System.out.println(o1);
         System.out.println(o2);


         System.out.println("\n=== Program Complete===");
     }}