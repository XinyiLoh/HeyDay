/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heyday;

import java.util.ArrayList;

/**
 *
 * @author Xin Ying
 */
public class Order {

    private static ArrayList<Order> orderedDetails = new ArrayList<>();
    private Product orderedProd = new Product();
    private int orderQty;

    public Order() {
    }

    public Order(Product orderedProd, int orderQty) {
        this.orderedProd = orderedProd;
        this.orderQty = orderQty;
        orderedDetails.add(this);
    }

    public static ArrayList<Order> getOrderedDetails() {
        return orderedDetails;
    }

    public static void setOrderedDetails(ArrayList<Order> orderedDetails) {
        Order.orderedDetails = orderedDetails;
    }


    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public Product getOrderedProd() {
        return orderedProd;
    }

    public void setOrderedProd(Product orderedProd) {
        this.orderedProd = orderedProd;
    }

    public double calculateAmount(Product product) {
        double orderAmount = 0;
        orderAmount = product.getPrice() * orderQty;
        return orderAmount;
    }

    @Override
    public String toString() {
        return "\nOrdered Product: " + orderedProd.toString() + "\nOrdered Quantity: " + orderQty;
    }

}