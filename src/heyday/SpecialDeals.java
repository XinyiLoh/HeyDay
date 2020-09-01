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
public class SpecialDeals extends Discount {

    private static final double DISCOUNT_TEN = 0.1;
    private static final double DISCOUNT_FIVE = 0.05;

    public SpecialDeals() {
    }

    @Override
    public double getDiscount() {
        return discount;
    }

    public static double getDISCOUNT_TEN() {
        return DISCOUNT_TEN;
    }

    public static double getDISCOUNT_FIVE() {
        return DISCOUNT_FIVE;
    }

    public double calculateDiscount(double subtotal, boolean discountTen, boolean discountFive) {
        if (discountTen) {
            discount = DISCOUNT_TEN * subtotal;
        } else if (discountFive) {
            discount = DISCOUNT_FIVE * subtotal;
        } else {
            discount = 0;
        }
        return discount;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

/*
    Cow+Sheep = 10% discount
    Product from Dairy and Field > $100 = 3 pounds of apples and 2 pounds of grapes
    Product from Flower and Orchard > $100 = 5% discount
 */
