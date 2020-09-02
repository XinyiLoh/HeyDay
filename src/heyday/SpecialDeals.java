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
public class SpecialDeals {

    private static final double DISCOUNT_TEN = 0.1;
    private static final double DISCOUNT_FIVE = 0.05;
    private double orginalPrice;

    public SpecialDeals() {
        this.orginalPrice = 0.00;
    }

    public SpecialDeals(double orginalPrice) {
        this.orginalPrice = orginalPrice;
    }

    public static double getDISCOUNT_TEN() {
        return DISCOUNT_TEN;
    }

    public static double getDISCOUNT_FIVE() {
        return DISCOUNT_FIVE;
    }

    public double getOrginalPrice() {
        return orginalPrice;
    }

    public void setOrginalPrice(double orginalPrice) {
        this.orginalPrice = orginalPrice;
    }

    public double calculateDiscount(boolean discountTen, boolean discountFive) {
        double discount;

        if (discountTen) {
            discount = DISCOUNT_TEN * orginalPrice;
        } else if (discountFive) {
            discount = DISCOUNT_FIVE * orginalPrice;
        } else {
            discount = 0;
        }
        return discount;
    }

    @Override
    public String toString() {
        return "\nOrginalPrice: " + orginalPrice;
    }
}

/*
    Cow+Sheep = 10% discount
    Product from Dairy and Field > $100 = 3 pounds of apples and 2 pounds of grapes
    Product from Flower and Orchard > $100 = 5% discount
 */
