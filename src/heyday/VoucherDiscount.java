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
public class VoucherDiscount extends Discount {

    private static final double VOUCHER_TWENTY = 20;
    private static final double VOUCHER_TEN = 10;
    private int vcCodeTwenty;
    private int vcCodeTen;

    public VoucherDiscount() {
    }

    public VoucherDiscount(int vcCodeTwenty, int vcCodeTen) {
        this.vcCodeTwenty = vcCodeTwenty;
        this.vcCodeTen = vcCodeTen;
    }

    @Override
    public double getDiscount() {
        return discount;
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

    public double calculateDiscount(int vcCode) {
        if (vcCode <= 10999) {
            discount = VOUCHER_TEN;
        } else if (vcCode > 20000) {
            discount = VOUCHER_TWENTY;
        }
        return discount;
    }

    @Override
    public String toString() {
        return super.toString() + "Twenty Ringgit Voucher Code: " + vcCodeTwenty + "Ten Ringgit Voucher Code: " + vcCodeTen;
    }
}
