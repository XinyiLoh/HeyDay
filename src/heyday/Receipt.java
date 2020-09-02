/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heyday;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Koh Hui Hui
 */
public class Receipt {

    private static ArrayList<Receipt> orders = new ArrayList<>();
    private ArrayList<Order> orderedItems = new ArrayList<>();
    private Discount discount = new Discount();
    DateFormat dateFormat = new SimpleDateFormat("E,  dd MMM yyyy");
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
    Date date = new Date();
    Date time = new Date();
    private final static double TAX_RATE = 0.06;
    private static int orderID = 0;
    private double subtotal = 0.00;

//    private static ArrayList<Order> order= new ArrayList<>();
    public Receipt() {
    }

    public Receipt(ArrayList<Order> orderedItems) {
        this.orderedItems = orderedItems;
        orders.add(this);
        orderID++;
    }

    public static ArrayList<Receipt> getOrders() {
        return orders;
    }

    public static void setOrders(ArrayList<Receipt> orders) {
        Receipt.orders = orders;
    }

    public ArrayList<Order> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(ArrayList<Order> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public DateFormat getTimeFormat() {
        return timeFormat;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    public String getTime() {
        return timeFormat.format(time);
    }

    public static int getOrderID() {
        return orderID;
    }

    public static double getTAX_RATE() {
        return TAX_RATE;
    }

    public double calculateSubtotal(double amountToPay) {
        subtotal += amountToPay;
        return subtotal;
    }

    public double calculateTax(double subtotal) {
        double taxCharge;
        taxCharge = TAX_RATE * subtotal;
        return taxCharge;
    }

    public double calculateTotal(double subtotal, double taxCharge, double totalDiscount) {
        double total;
        total = subtotal + taxCharge - totalDiscount;
        return total;
    }

    public double calculateBalance(double payment, double total) {
        double balance;
        balance = payment - total;
        return balance;
    }

    @Override
    public String toString() {
        return "\nDateFormat: " + dateFormat + "\nTimeFormat:" + timeFormat + "\nDate:" + date + "\nTime: " + time;
    }

}
