/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heyday;

import java.util.ArrayList;

/**
 *
 * @author Koh Hui Hui
 */
public class Discount {

    private static final double SPECIAL_DEALS_TEN = 0.1;
    private static final double SPECIAL_DEALS_FIVE = 0.05;
    private String code;
    private double amount;
    private static ArrayList<Discount> voucher = new ArrayList<>();
    
    public Discount() {
    }
    
    public Discount(String code, double amount) {
        this.code = code;
        this.amount = amount;
        voucher.add(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static ArrayList<Discount> getVoucher() {
        return voucher;
    }

    public static void setVoucher(ArrayList<Discount> voucher) {
        Discount.voucher = voucher;
    }

    public static double getSPECIAL_DEALS_TEN() {
        return SPECIAL_DEALS_TEN;
    }

    public static double getSPECIAL_DEALS_FIVE() {
        return SPECIAL_DEALS_FIVE;
    }
    
    @Override
    public String toString() {
        return "Code: " + code + "Discount Amount: " + amount;
    }

}

/*
    Cow+Sheep = 10% discount
    Product from Dairy and Field > $100 = 3 pounds of apples and 2 pounds of grapes
    Product from Flower and Orchard > $100 = 5% discount
 */
