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

    double newSubtotal = 0.0;
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

    public int calculateTotalOrder(int totalOrder) {
        return totalOrder++;
    }
    
    public double calculateSoldAmount(int sold, double amount) {
    	double soldAmount;
        soldAmount = sold * amount;
        return soldAmount;
    }

    public double calculateAmount(double subtotal) {
        newSubtotal += subtotal;
        return newSubtotal;
    }

    public double calculateDiscount(double sdDiscount) {
        double specialDealDiscount = 0.00;
        specialDealDiscount += sdDiscount;
        return specialDealDiscount;
    }

    public double calculateVoucher(double vcDiscount) {
        double voucherDiscount = 0.00;
        voucherDiscount += vcDiscount;
        return voucherDiscount;
    }

    public double calculateFinalTotalAmount(double totalAmount,double totalDiscount,double totalVoucher) {
    	double productTotalAmount;
        return  productTotalAmount = totalAmount - totalDiscount - totalVoucher;
    }

}
