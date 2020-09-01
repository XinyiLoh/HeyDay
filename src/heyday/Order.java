/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heyday;

/**
 *
 * @author Xin Ying
 */
public class Order {

    private String orderProductID;
    private int orderQty;

    public Order() {
        this.orderProductID = "";
        this.orderQty = 0;
    }

    public Order(String orderProductID, int orderQty) {
        this.orderProductID = orderProductID;
        this.orderQty = orderQty;
    }

    public void setOrderProductID(String orderProductID) {
        this.orderProductID = orderProductID;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public String getOrderProductID() {
        return orderProductID;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public double calculateAmount(Product[] product, int newProd) {
        double orderAmount = 0;

        for (int i = 0; i < product.length - newProd; i++) {
            if (orderProductID.equalsIgnoreCase(product[i].getId())) {
                orderAmount = product[i].getPrice()*orderQty;
            }
        }
        return orderAmount;
    }

    public String toString() {
        return "\nProduct ID: " + orderProductID + "\nOrder Quantity: " + orderQty + "\n";
    }

}
