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

    private static ArrayList<Order> totalOrder = new ArrayList<>();
    private Discount discount = new Discount();
    private DateFormat dateFormat = new SimpleDateFormat("E,  dd MMM yyyy");
    private DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
    private Date date = new Date();
    private Date time = new Date();
    private final static double TAX_RATE = 0.06;
    private static int orderID = 0;
    private double subtotal = 0.00;

    public Receipt() {
        orderID++;
    }

    public static ArrayList<Order> getTotalOrder() {
        return totalOrder;
    }

    public static void setTotalOrder(ArrayList<Order> totalOrder) {
        Receipt.totalOrder = totalOrder;
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

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double calculateTax() {
        double taxCharge;
        taxCharge = TAX_RATE * subtotal;
        return taxCharge;
    }

    public double calculateTotal(double taxCharge, double totalDiscount) {
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
        return "\nDiscount: " + discount + "\nSubtotal: " + subtotal + "\nDateFormat: " + dateFormat + "\nTimeFormat: " + timeFormat + "\nDate: " + date + "\nTime: " + time;
    }

}
