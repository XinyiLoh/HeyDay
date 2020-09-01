/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heyday;

/**
 *
 * @author Xin Yi
 */
public class Product {

    private Category source;
    private String id;
    private String name;
    private double price;
    private String detail;
    private int stock;
    private static int totalProduct;

    public Product(){
        this.name = "";
    }
    
    public Product(String id){
        id = id.substring(0,1).toUpperCase() + id.substring(1);
        this.id = id;
    }
    
    public Product(Category source, String id, String name, double price,String detail,int stock){
        this.source = source;
        
        id = id.substring(0,1).toUpperCase() + id.substring(1);
        this.id = id;
        
        name = name.substring(0,1).toUpperCase()+
                name.substring(1).toLowerCase();
        this.name = name;
        
        this.price = price;
        
        detail = detail.substring(0).toLowerCase();
        this.detail = detail;
        
        this.stock = stock;
        
        totalProduct++;
    }

    public Category getSource() {
        return source;
    }

    public void setSource(Category source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id.substring(0,1).toUpperCase() + id.substring(1);
        this.id = id;
        totalProduct++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.substring(0,1).toUpperCase()+
                name.substring(1).toLowerCase();
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price >= 0.0 && price <=999.99){
            this.price = price;
        }
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        detail = detail.substring(0).toLowerCase();
        this.detail = detail;
    }
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if(stock >= 0 && stock <=9999){
            this.stock = stock;
        }
    }
    
    public static int getTotalProduct(){
        return totalProduct;
    }
    
    public static void setTotalProduct(int totalProduct) {
        Product.totalProduct = totalProduct;
    }

//    void discount(double percent){
//        price = price * percent / 100.0;
//    }
    
    public boolean increaseStock(int stock){
        if(stock >= 0 && stock <=9999 ){
            this.stock += stock;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean  decreaseStock(int stock){
        if(stock >= 0 && stock <= this.stock ){
            this.stock -= stock;
            return true;
        }else{
            return false;
        }
    }
    
//    public boolean equals(Object obj) {
//        Product temp = (Product) obj;
//        return (this.getId().equalsIgnoreCase(temp.getId()));
//    }
    
    public String toString(){
        return String.format("%s %-8s %-13s $%-10.2f %-20s %-10d",
                source.toString(),id,name,price,detail,stock);
    }
}
