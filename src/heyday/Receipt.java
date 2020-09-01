/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heyday;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Koh Hui Hui
 */
public class Receipt {

    private Discount discount;

    private final static double TAX_RATE = 0.06;
    private static int orderID = 0;

    DateFormat dateFormat = new SimpleDateFormat("E,  dd MMM yyyy");
    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
    Date date = new Date();
    Date time = new Date();

//    Discount discount = new Discount();
    public Receipt() {
        orderID++;
    }

    public Receipt(Discount discount) {
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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
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
        return discount.toString() + "\nDateFormat: " + dateFormat + "TimeFormat: " + timeFormat + "Date: " + date + "Time: " + time;
    }

}
