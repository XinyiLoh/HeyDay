/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heyday;

/**
 *
 * @author HP
 */
public class VoucherDiscount {

    private static final double VOUCHER_TWENTY = 20;
    private static final double VOUCHER_TEN = 10;
    private int vcCodeTwenty;
    private int vcCodeTen;
    private double originalPrice;

    public VoucherDiscount() {
        this.vcCodeTwenty = 0;
        this.vcCodeTen = 0;
        this.originalPrice = 0.00;
    }

    public VoucherDiscount(int vcCodeTwenty, int vcCodeTen, double originalPrice) {
        this.vcCodeTwenty = vcCodeTwenty;
        this.vcCodeTen = vcCodeTen;
        this.originalPrice = originalPrice;
    }

    public int getVcCodeTwenty() {
        vcCodeTwenty++;
        return vcCodeTwenty;
    }

    public int getVcCodeTen() {
        vcCodeTen++;
        return vcCodeTen;
    }

    public static double getVOUCHER_TWENTY() {
        return VOUCHER_TWENTY;
    }

    public static double getVOUCHER_TEN() {
        return VOUCHER_TEN;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }
    
    public double calculateDiscount(int vcCode) {
        double discount = 0;

        if (vcCode <= 10999) {
            discount = VOUCHER_TEN;
        } else if (vcCode > 20000) {
            discount = VOUCHER_TWENTY;
        }

        return discount;
    }

    @Override
    public String toString() {
        return "\nTwenty Dollars Voucher Code: " + vcCodeTwenty + "\nTen Dollars Voucher Code: " + vcCodeTen + "\nOriginal Price: " + originalPrice;
    }
}
