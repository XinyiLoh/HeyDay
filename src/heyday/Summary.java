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
 * @author Yap Jun Yan
 */
public class Summary {

    private double newsoldAmount = 0.00;
    private double newSubtotal = 0.0;
    private double newsdDiscount = 0.00;
    private double newvcDiscount = 0.00;
    private double productTotalAmount = 0.00;
    DateFormat dateFormat = new SimpleDateFormat("E,  dd MMM yyyy");
    Date date = new Date();

    public Summary() {
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public String getDate() {
        return dateFormat.format(date);
    }

    public double getNewsoldAmount() {
        return newsoldAmount;
    }

    public void setNewsoldAmount(double newsoldAmount) {
        this.newsoldAmount = newsoldAmount;
    }

    public double getNewSubtotal() {
        return newSubtotal;
    }

    public void setNewSubtotal(double newSubtotal) {
        this.newSubtotal = newSubtotal;
    }

    public double getNewsdDiscount() {
        return newsdDiscount;
    }

    public void setNewsdDiscount(double newsdDiscount) {
        this.newsdDiscount = newsdDiscount;
    }

    public double getNewvcDiscount() {
        return newvcDiscount;
    }

    public void setNewvcDiscount(double newvcDiscount) {
        this.newvcDiscount = newvcDiscount;
    }

    public double getProductTotalAmount() {
        return productTotalAmount;
    }

    public void setProductTotalAmount(double productTotalAmount) {
        this.productTotalAmount = productTotalAmount;
    }
    
//    public int calculateTotalOrder(int totalOrder) {
//        return totalOrder++;
//    }
//
    public double calculateSoldAmount(int sold, double amount) {
        newsoldAmount = sold * amount;
        return newsoldAmount;
    }

    public double calculateAmount(double subtotal) {
        newSubtotal += subtotal;
        return newSubtotal;
    }

    public double calculateDiscount(double sdDiscount) {

        newsdDiscount += sdDiscount;
        return newsdDiscount;
    }

    public double calculateVoucher(double vcDiscount) {
        newvcDiscount += vcDiscount;
        return newvcDiscount;
    }

    public double calculateFinalTotalAmount(double totalAmount, double totalDiscount, double totalVoucher) {
        productTotalAmount = totalAmount - totalDiscount - totalVoucher;
        return productTotalAmount;
    }

    @Override
    public String toString() {
        return "NewsoldAmount=" + newsoldAmount + ", newSubtotal=" + newSubtotal + ", newsdDiscount=" + newsdDiscount + ", newvcDiscount=" + newvcDiscount + ", productTotalAmount=" + productTotalAmount + ", dateFormat=" + dateFormat + ", date=" + date;
    }
    
}
