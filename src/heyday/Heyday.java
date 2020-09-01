/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heyday;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author HeyDay
 */
public class HayDay {

    public static Category[] heyDayCategory() {
        Category[] cat = new Category[7];

        cat[0] = new Category('L', "Livestock");
        cat[1] = new Category('F', "Field");
        cat[2] = new Category('D', "Dairy");
        cat[3] = new Category('H', "Flower");
        cat[4] = new Category('O', "Orchard");

        return cat;
    }
    public static Product[] heyDayProduct(Category cat[]) {
        Product[] product = new Product[20];

        product[0] = new Product(cat[0], "L001", "Cow", 2000.00, "per head", 30);
        product[1] = new Product(cat[0], "L002", "Chicken", 1.51, "per kg", 80);
        product[2] = new Product(cat[0], "L003", "Sheep", 160.00, "per head", 50);

        product[3] = new Product(cat[1], "F001", "Wheat", 4.56, "per bushel", 100);
        product[4] = new Product(cat[1], "F002", "Corn", 3.16, "per bushel", 100);
        product[5] = new Product(cat[1], "F003", "Soybeans", 8.14, "per bushel", 100);

        product[6] = new Product(cat[2], "D001", "Butter", 13.76, "per cwt", 100);
        product[7] = new Product(cat[2], "D002", "Milk", 13.79, "per cwt", 100);
        product[8] = new Product(cat[2], "D003", "Cheese", 24.54, "per cwt", 100);

        product[9] = new Product(cat[3], "H001", "Sunflowers", 17.75, "per 10 stem bunch", 100);
        product[10] = new Product(cat[3], "H002", "Buttercup", 14.75, "per 10 stem bunch", 100);
        product[11] = new Product(cat[3], "H003", "Loosestrife", 8.50, "per 10 stem bunch", 100);

        product[12] = new Product(cat[4], "O001", "Apples", 1.00, "per pound", 100);
        product[13] = new Product(cat[4], "O002", "Cherries", 5.00, "per pound", 100);
        product[14] = new Product(cat[4], "O003", "Grapes", 0.60, "per pound", 100);

        return product;
    }
    public static User[] heyDayStaff(){
        
        User[] staff = {
            new Manager("xinying","xinying",'F',"010-0000000"),
            new Cashier("junyan","junyan",'M',"011-1111111"),
            new Cashier("huihui","huihui",'F',"012-2222222"),
            new Cashier("xinyi","xinyi",'F',"013-3333333")
        };
        
        return staff;
    }
    
    public static void displayProducts(int selectCat,Category cat[],Product[] product,int newProd){
        if (selectCat >= 1 && selectCat <= cat.length + 1) {
            System.out.println("\n=================================================================================");
            System.out.println("                                P R O D U C T S");
            System.out.println("=================================================================================");
            System.out.println("Code \t Category   ID\t     Product \t   Price \t\t\t   Stock");
            System.out.println("--------------------------------------------------------------------------------");
            switch (selectCat) {
                case 1:
                    for (int i = 0; i < product.length - newProd; i++) {
                        System.out.println(product[i].toString());
                    }
                    System.out.println("\n" + Product.getTotalProduct() + " Product(s)");
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    int count = 0;
                    for (int i = 0; i < product.length - newProd; i++) {
                        if (cat[selectCat - 2] == product[i].getSource()) {
                            System.out.println(product[i].toString());
                            count++;
                        }
                    }
                    if (count == 0) {
                        System.out.println("\nNo record found.");
                    } else {
                        System.out.println("\n" + count + " Product(s)");
                    }
                    break;
            }
            System.out.println("=================================================================================");
        } else {
            if (selectCat > cat.length) {
                System.out.println("Invalid input...");
            } else {
                System.out.println("Quit...");
            }
        }
    }
    public static int validateNewCategory(Category cat[], char code, String title , int newCat){
        
        int error = 0;
        
        if(title.length() <=2 || title.length() >15){
            System.out.println("\"" + title + "\"" + " Title length is out of range.");
            error++;
        }
        
        for (int i = 0; i < cat.length - newCat; i++) {
            if (cat[i].getCode() == Character.toUpperCase(code)) {
                System.out.println("\"" + code + "\"" + " Code is repeated.");
                error++;
            }
            if (cat[i].getName().equalsIgnoreCase(title)) {
                System.out.println("\"" + title + "\"" + " Category exist.");
                error++;
            }
        }
        
        return error;
    }
    public static int validateNewProduct(Category cat,Product[] product, String id, String name, double price, String detail, int stock,int newProd){
        
        int error = 0;
        int digit = 0;
        int letter = 0;
        char idFLetter = Character.toUpperCase(id.charAt(0));
        
        for (int i = 0; i < id.length(); i++){
            char chkID = id.charAt(i);
            if(Character.isDigit(chkID)){
                digit++;
            }
        }
        
        for (int i = 0; i < name.length(); i++){
            char chkName = name.charAt(i);
            if(Character.isLetter(chkName)){
                letter++;
            }
        }
        
        //check id fromat
        if (cat.getCode() != idFLetter || id.length() != 4 || digit != 3) {
            System.out.println("\"" + id + "\"" + " ID is in wrong format.");
            System.out.println("\tExample of Correct ID Format: Category Code + Sequence Numbers ->" + cat.getCode() + "001");
            error++;
        }
        
        //check name format
        if(letter != name.length()){
            System.out.println("\"" + name + "\"" + " Name should only contains letters.");
            error++;
        }else if(name.length() <=2 || name.length() >15){
            System.out.println("\"" + name + "\"" + " Name length is out of range.");
            error++;
        }
        
        //check duplicate id and name
        for (int i = 0; i < product.length - newProd; i++) {
            
            if(product[i].getId().equalsIgnoreCase(id)){
                System.out.println("\"" + id + "\"" + " Duplicate ID.");
                error++;
            }
                        
            if (product[i].getName().equalsIgnoreCase(name)) {
                System.out.println("\"" + name + "\"" + " Duplicate Name.");
                error++;
            }
        }      
        
        //check detail length
        if(detail.length() <=2 || detail.length() >20){
            System.out.println("\"" + detail + "\"" + " Detail length is out of range.");
            error++;
        }
        
        //check price
        if(price <= 0.0 || price >= 9999.99){
            System.out.println("\"" + price + "\"" + " Price is out of range.");
            error++;
        }
        
        //check stock
        if(stock <= 0 || stock >= 9999){
            System.out.println("\"" + stock + "\"" + " Stock is out of range.");
            error++;
        }
   
        return error;
    }

    public static void main(String[] args) {
        
        User[] staff = heyDayStaff();
        Category[] cat = heyDayCategory();
        Product[] product = heyDayProduct(cat);
        ArrayList<String> usedVc = new ArrayList<>();

        //number of new categoriy and product can be added
        int newCat = 2;
        int newProd = 5;
        
        
        double subtotal = 0.00;
        double amountPaid;
        double total;
        double taxCharge;
        double balance;
        double vcDiscount = 0;
        double sdDiscount = 0;
        double totalDiscount;
        char printReceipt;
        int retypeChoice;
        int method;
        int vcCount = 0;
        
        ArrayList<Integer> orderedProductQty = new ArrayList<>();
        ArrayList<Double> orderedProductAmount = new ArrayList<>();
//        int orderedProductQty = 0;
//        double orderedProductAmount = 0.0;
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("........................................................................................");
        System.out.println("    ////    ////   ////////  /////  /////   ////////          //////     /////  /////  ");
        System.out.println("   ////    ////   ///        ////  ////    ///    ///      ///    ///    ////  ////");
        System.out.println("  ////////////   ////////     /////       ///     ///    ////////////     /////");
        System.out.println(" ////    ////   ///           ///        ///    ///    ////      ////     ///");
        System.out.println("////    ////   ////////      ///        ////////     ////        ////    ///");
        System.out.println("........................................................................................");
        System.out.println("----------------------------------------------------------------------------------------");
        
        boolean login = false;
        int staffNo = 0;
        do{
            boolean correctUsername = false;
            boolean correctPassword = false;
            System.out.println("                                     --- LOGIN --- ");
            System.out.print("                          Username : ");
            String username = scan.nextLine();
            System.out.print("                          Password : ");
            String password = scan.nextLine();
            
            for(int i = 0 ; i < staff.length ; i++){
                if(username.equals(staff[i].getUsername())){
                    correctUsername = true;
                    staffNo = i;
                }

                if(password.equals(staff[i].getPassword())){
                    correctPassword = true;
                    staffNo = i;
                }
            }
            
            if(correctUsername == true && correctPassword == true){
                System.out.println("\n\nHello " + staff[staffNo].getUsername() + " welcome back !");
                login = true;
            }else{
                if(correctUsername == false){
                    System.out.println("                          Wrong username. Please try again.");
                }else if(correctPassword == false){
                    System.out.println("                          Wrong password. Please try again.");
                }else{
                    System.out.println("                          Wrong username and password. Please try again.");
                }
            }
            
        }while(login == false);
        
        int selection = 7;
        do{
            boolean menu = false;
      
            while(menu == false){
                System.out.println("\n1. Display HayDay Products");
                System.out.println("2. Order");

                if(staff[staffNo] instanceof Manager){
                    System.out.println("3. Add Category/Product");
                    System.out.println("4. Stock Management");
                    System.out.println("5. Order Summary");
                    System.out.println("6. Staff List");
                }

                System.out.println("0. Logout");
                System.out.print("Enter choice > ");
                selection = scan.nextInt();

                if(staff[staffNo] instanceof Manager){
                    if (selection < 0 || selection > 6) {
                        System.out.println("\nInvalid choice, please try again");
                        menu = false;
                    }else{
                        menu = true;
                    }
                }else{
                    if (selection < 0 || selection > 2) {
                        System.out.println("\nInvalid choice, please try again");
                        menu = false;
                    }else{
                        menu = true;
                    }
                }
            }

            switch (selection) {
                case 0:
                    System.out.print("\nAre you sure want to logout? [1=YES]");
                    int out = scan.nextInt();
                    
                    if(out == 1 ){
                        System.out.println("\nGoodbye, ^_^  Have a nice day !");
                        selection = 7;
                    }
                    break;
                case 1:
                    int selectCat = 8;
                    do {
                        System.out.println("\n=========================");
                        System.out.println("== C A T E G O R I E S ==");
                        System.out.println("=========================");
                        System.out.println("1. All              ");

                        for (int i = 0; i < cat.length - newCat; i++) {
                            System.out.println((i + 2) + ". " + cat[i].getName());
                        }

                        System.out.println("0. Quit             ");
                        System.out.println("=========================");
                        System.out.println("Total Products = " + Product.getTotalProduct());
                        System.out.println("=========================");
                        System.out.print("Enter choice > ");
                        selectCat = scan.nextInt();
                        displayProducts(selectCat,cat,product,newProd);
                    } while (selectCat != 0);
                    break;

                case 2:
                    System.out.println("\n===========================");
                    System.out.println("=    O R D E R Product    =");
                    System.out.println("===========================");

                    String orderProductID;
                    int orderQty;
                    int orderStockQty = 0;
                    int orderError = 0;
                    int orderFound = 0;
                    int orderStockFound = 0;
                    char continueAddOrder;
                    char continuePayment;
                    
                    boolean cancelOrder = false;

                    Order takeOrder = new Order();

                    ArrayList<String> orderedProdId = new ArrayList<>();
                    ArrayList<String> orderedProdName = new ArrayList<>();
                    ArrayList<Integer> orderedProdQty = new ArrayList<>();
                    ArrayList<Double> orderedUnitPrice = new ArrayList<>();
                    ArrayList<Double> orderedProdAmount = new ArrayList<>();

                    do {
                        orderError = 0;
                        orderFound = 0;
                        do {

                            orderError = 0;
                            orderFound = 0;
                            //Enter Product ID
                            System.out.print("\nEnter Product ID > ");
                            takeOrder.setOrderProductID(orderProductID = scan.next());

                            for (int i = 0; i < product.length - newProd; i++) {

                                if (orderProductID.equalsIgnoreCase(product[i].getId())) {
                                    if (product[i].getStock() == 0) {
                                        System.out.println("This product is out of stock, please increase stock before order.");
                                        orderError++;
                                    } else {
                                        System.out.println("Code \t Category   ID\t     Product \t   Price \t\t\t   Stock");
                                        System.out.println("--------------------------------------------------------------------------------");
                                        System.out.println(product[i].toString());
                                        System.out.println("--------------------------------------------------------------------------------\n");
                                        orderFound++;
                                    }
                                }
                            }
                            if (orderFound == 0) {
                                if (orderError == 0) {
                                    System.out.println("No record found, please try again");

                                }
                            }
                        } while (orderFound == 0 || orderError!= 0);
                        
                        do {
                            //Enter Unit
                            do {
                                System.out.print("\nEnter unit > ");
                                takeOrder.setOrderQty(orderQty = scan.nextInt());
                                if (orderQty == 0) {
                                    System.err.println("Unit cannot be zero, please try again");
                                }
                            } while (orderQty == 0);
                            for (int i = 0; i < product.length - newProd; i++) {
                                if (orderQty <= product[i].getStock()) {
                                    orderStockQty++;
                                }
                            }
                            //Out of Stock
                            if (orderStockQty == 0) {
                                System.out.println("Sorry, out of stock..");
                            } else {
                                for (int i = 0; i < product.length - newProd; i++) {

                                    if (orderProductID.equalsIgnoreCase(product[i].getId())) {
                                        System.out.println("\nProduct ID : " + takeOrder.getOrderProductID()
                                                + "\nProduct : " + product[i].getName()
                                                + "\nOrder Quantity : " + takeOrder.getOrderQty());
                                        System.out.printf("Amount : $%.2f\n", takeOrder.calculateAmount(product, newProd));
                                        System.out.println("--------------------------------------------------------------------------------\n");
                                        orderStockFound++;

                                        //The ordered products are remembered in the arrays
                                        orderedProdId.add(takeOrder.getOrderProductID().toUpperCase());
                                        orderedProdName.add(product[i].getName());
                                        orderedProdQty.add(takeOrder.getOrderQty());
                                        orderedUnitPrice.add(product[i].getPrice());
                                        orderedProdAmount.add(takeOrder.calculateAmount(product, newProd));
                                    }
                                }
                            }
                        } while (orderStockQty == 0);
                        do {
                            System.out.print("\n Continue Add Order (Y / N) > ");
                            continueAddOrder = scan.next().charAt(0);
                            if (continueAddOrder != 'n' && continueAddOrder != 'N' && continueAddOrder != 'y' && continueAddOrder != 'Y') {
                                System.err.print("Invalid input...");
                            }
                        } while (continueAddOrder != 'n' && continueAddOrder != 'N' && continueAddOrder != 'y' && continueAddOrder != 'Y');
                    } while (continueAddOrder == 'y' || continueAddOrder == 'Y');

                    do {
                        System.out.print("\n Proceed to payment (Y / N) > ");
                        continuePayment = scan.next().charAt(0);
                        if (continuePayment != 'n' && continuePayment != 'N' && continuePayment != 'y' && continuePayment != 'Y') {
                            System.err.print("Invalid input...");
                        }
                    } while (continuePayment != 'n' && continuePayment != 'N' && continuePayment != 'y' && continuePayment != 'Y');

                    //------------- Receipt -------------
                    if (continuePayment == 'Y' || continuePayment == 'y') {
                        Receipt receipt = new Receipt();

                        VoucherDiscount voucher = new VoucherDiscount(20110, 10110);
                        SpecialDeals specialpromo = new SpecialDeals();

                        int[] vcCode10 = new int[8];
                        int[] vcCode20 = new int[8];
                        String[] entireCode10 = new String[8];
                        String[] entireCode20 = new String[8];

                        boolean backToMethod = false;
                        boolean invalidCode = true;

                        boolean specialdeals = false;
                        boolean cowSheepPurchased = false;
                        boolean freeItems = false;
                        boolean flowerOrchardPurchased = false;

                        for (int i = 0; i < vcCode10.length; i++) {
                            vcCode10[i] = voucher.getVcCodeTen();
                            entireCode10[i] = "HDVC" + vcCode10[i];
                        }

                        for (int i = 0; i < vcCode20.length; i++) {
                            vcCode20[i] = voucher.getVcCodeTwenty();
                            entireCode20[i] = "HDVC" + vcCode20[i];
                        }

                        for (int i = 0; i < orderedProdAmount.size(); i++) {
                            subtotal += orderedProdAmount.get(i);     //calculate subtotal
                        }

                        //----------------- Special Promotions ----------------- 
                        //Cow+Sheep = 10% discount
                        for (int i = 0; i < orderedProdName.size(); i++) {
                            if (cowSheepPurchased == false) {                       //run only one time
                                if (orderedProdName.get(i).equals("Cow")) {
                                    for (int j = 0; j < orderedProdName.size(); j++) {
                                        if (orderedProdName.get(j).equals("Sheep")) {
                                            cowSheepPurchased = true;
                                            specialdeals = true;
                                            System.out.println("\n Customer will get 10% discount purchasing cow(s) and sheep(s).");
                                        }
                                    }
                                }
                            }
                        }
                        //Product from Dairy and Field > $100 = 3 pounds of apples and 2 pounds of grapes
                        if (cowSheepPurchased == false) {                   //customer is not allowed to receive more than one discount
                            for (int j = 0; j < orderedProdId.size(); j++) {
                                if (freeItems == false) {                        //run only one time
                                    for (int i = 0; i < product.length - newProd; i++) {
                                        if (orderedProdId.get(j).equals(product[i].getId())) {
                                            if (product[i].getSource().getName().equals("Dairy") || product[i].getSource().getName().equals("Field")) {     //Purchase Dairy or Field products with subtotal exceeds $100 will get free items
                                                if (subtotal > 100) {
                                                    freeItems = true;
                                                    specialdeals = true;
                                                    System.out.println("\n Customer will get free 3 pounds of apples and 2 pounds of grapes with purchase include Dairy or Field products more than $100.");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            //Product from Flower and Orchard > $100 = 5% discount
                            if (freeItems == false) {
                                for (int j = 0; j < orderedProdId.size(); j++) {
                                    if (flowerOrchardPurchased == false) {                          //run only one time
                                        for (int i = 0; i < product.length - newProd; i++) {
                                            if (orderedProdId.get(j).equals(product[i].getId())) {
                                                if (product[i].getSource().getName().equals("Flower") || product[j].getSource().getName().equals("Orchard")) {     //Purchase Flower or Orchard products with subtotal exceeds $100 will get 5% discount
                                                    if (subtotal > 100) {
                                                        flowerOrchardPurchased = true;
                                                        specialdeals = true;
                                                        System.out.println("\n Customer will get 5% discount with purchase include Flower or Orchard products more than $100.");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        sdDiscount = specialpromo.calculateDiscount(subtotal, cowSheepPurchased, flowerOrchardPurchased);               //Discount is calculated

                        do {
                            do {
                                System.out.println("\n\nPayment Method");
                                System.out.println("============");
                                System.out.println("1. Cash");
                                if (vcCount == 0 && subtotal > 50.00 && specialdeals == false) {                             //If voucher hasn't been used before & total amount exceeds $50, customer is allowed to receive voucher discount
                                    System.out.println("2. Voucher");
                                }
                                System.out.print("Please select a payment method > ");
                                method = scan.nextInt();
                                if (method != 1 && method != 2) {
                                    System.err.println("Invalid input, please try again");
                                }
                            } while (method != 1 && method != 2);

                            switch (method) {
                                case 1:
                                    break;
                                case 2:
                                    //----------------- Voucher Discount ----------------- 
                                    if (vcCount == 0 && subtotal > 50.00 && specialdeals == false) {
                                        do {
                                            boolean used = false;
                                            System.out.print("Please enter voucher code: ");
                                            String inputVc = scan.next().toUpperCase();
                                            for (int i = 0; i < vcCode10.length; i++) {
                                                if (inputVc.equals(entireCode10[i])) {                                  //Searching for the valid code
                                                    for (int j = 0; j < usedVc.size(); j++) {
                                                        if (usedVc.get(j).equals(entireCode10[i])) {                //Comparing the used voucher with the found code
                                                            System.err.println("Discount implemented failed, this voucher has already been used.");
                                                            used = true;
                                                            do {
                                                                System.out.print("1 - Input another voucher code, 2 - Back to payment method > ");
                                                                retypeChoice = scan.nextInt();
                                                                switch (retypeChoice) {
                                                                    case 1:
                                                                        backToMethod = false;
                                                                        break;
                                                                    case 2:
                                                                        backToMethod = true;
                                                                        break;
                                                                    default:
                                                                        System.out.println("Invalid input, please try again");
                                                                }
                                                            } while (retypeChoice != 1 && retypeChoice != 2);
                                                        }
                                                    }
                                                    if (used == false) {
                                                        System.out.println("\n$10 Discount is implemented successfully...");
                                                        vcCount++;
                                                        vcDiscount = voucher.calculateDiscount(vcCode10[i]);
                                                        backToMethod = true;
                                                        usedVc.add(entireCode10[i]);
                                                    }
                                                    invalidCode = false;
                                                }
                                            }
                                            for (int n = 0; n < vcCode20.length; n++) {
                                                if (inputVc.equals(entireCode20[n])) {                                      //Searching for the valid code
                                                    for (int m = 0; m < usedVc.size(); m++) {
                                                        if (usedVc.get(m).equals(entireCode20[n])) {                 //Comparing the used voucher with the found code
                                                            System.err.println("Discount implemented failed, this voucher has already been used.");
                                                            used = true;
                                                            do {
                                                                System.out.print("1 - Input another voucher code, 2 - Back to payment method > ");
                                                                retypeChoice = scan.nextInt();
                                                                switch (retypeChoice) {
                                                                    case 1:
                                                                        backToMethod = false;
                                                                        break;
                                                                    case 2:
                                                                        backToMethod = true;
                                                                        break;
                                                                    default:
                                                                        System.err.println("Invalid input, please try again");
                                                                }
                                                            } while (retypeChoice != 1 && retypeChoice != 2);
                                                        }
                                                    }
                                                    if (used == false) {
                                                        System.out.println("\n$20 Discount is implemented successfully...");
                                                        vcCount++;
                                                        vcDiscount = voucher.calculateDiscount(vcCode20[n]);
                                                        backToMethod = true;
                                                        usedVc.add(entireCode20[n]);
                                                    }
                                                    invalidCode = false;
                                                }
                                            }
                                            if (invalidCode) {
                                                System.err.println("Invalid voucher code");
                                                do {
                                                    System.out.print("1 - Re-type voucher code, 2 - Back to payment method > ");
                                                    retypeChoice = scan.nextInt();
                                                    switch (retypeChoice) {
                                                        case 1:
                                                            backToMethod = false;
                                                            break;
                                                        case 2:
                                                            backToMethod = true;
                                                            break;
                                                        default:
                                                            System.err.println("Invalid input, please try again");
                                                    }
                                                } while (retypeChoice != 1 && retypeChoice != 2);
                                            }
                                        } while (backToMethod == false);
                                    }
                            }
                        } while (method == 2);

                        if (sdDiscount > 0) {
                            totalDiscount = sdDiscount;
                        } else if (vcDiscount > 0) {
                            totalDiscount = vcDiscount;
                        } else {
                            totalDiscount = 0;
                        }

                        //Information are shown before input amount paid by the customers
                        taxCharge = receipt.calculateTax(subtotal);
                        total = receipt.calculateTotal(subtotal, receipt.calculateTax(subtotal), totalDiscount);
                        System.out.printf("\n Subtotal: \t\t$%9.2f\n", subtotal);
                        if (vcCount > 0) {
                            System.out.printf(" Voucher Discount: \t$%9.2f\n", vcDiscount);
                        } else {
                            System.out.printf(" Discount: \t\t$%9.2f\n", sdDiscount);
                        }
                        System.out.printf(" Extra Charges (Tax): \t$%9.2f\n", taxCharge);
                        System.out.printf(" Total: \t\t$%9.2f\n", total);
                        do {
                            System.out.print(" Amount Paid: \t\t$  ");
                            amountPaid = scan.nextDouble();
                            balance = receipt.calculateBalance(amountPaid, total);
                            if (amountPaid >= total) {
                                System.out.printf(" Change: \t\t$%9.2f\n", balance);
                            } else {
                                System.err.println("Not enough payment, please try again.");
                            }
                        } while (amountPaid < total);

                        //print receipt
                        do {
                            System.out.print("\nPrint receipt (Y / N) > ");                         //Prompt to print receipt
                            printReceipt = scan.next().charAt(0);
                            if (printReceipt != 'y' && printReceipt != 'Y' && printReceipt != 'n' && printReceipt != 'N') {
                                System.out.println("Invalid input, please try again");
                            }
                        } while (printReceipt != 'y' && printReceipt != 'Y' && printReceipt != 'n' && printReceipt != 'N');
                        if (printReceipt == 'y' || printReceipt == 'Y') {
                            System.out.println("----------------------------------------------------------------------------------------");
                            System.out.println("\n                                       R E C E I P T");
                            System.out.println("\n                                          Hay Day");
                            System.out.println("\t\t       2967  Red Hawk Road, 56301 Saint Cloud, Minnesota");
                            System.out.println("\t\t\t           Tel: (320) 682-3506");
                            System.out.print("\t\t\t\t      hey@hayday.com");
                            System.out.println("\n\t\t\t          Date: " + receipt.getDate() + "\n\t\t\t             Time: " + receipt.getTime());
                            System.out.printf("\t\t\t              Order ID: #%04d\n\n", Receipt.getOrderID());
                            System.out.println(" =======================================================================================");
                            System.out.println(" ID\t\tItem Ordered\t\tUnit\t\tUnit Price\t\tAmount");
                            System.out.println(" =======================================================================================\n");
                            for (int i = 0; i < orderedProdId.size(); i++) {
                                System.out.printf(" %-14s %-20s %6d \t\t$%9.2f \t      $%8.2f\n", orderedProdId.get(i), orderedProdName.get(i), orderedProdQty.get(i), orderedUnitPrice.get(i), orderedProdAmount.get(i));
                            }
                            if (freeItems == true) {
                                System.out.printf(" O001 \t\tApples\t\t\t  3 \t\t\t\t\t  F.O.C\n");
                                System.out.printf(" O003 \t\tGrapes\t\t\t  2 \t\t\t\t\t  F.O.C\n");
                            }
                            System.out.println("\n =======================================================================================");
                            System.out.printf("\t\t\t\t\t\t\tSubtotal: \t%15.2f\n", subtotal);
                            if (vcDiscount > 0) {
                                System.out.printf("\t\t\t\t\t\t\tVoucher Discount: %13.2f\n", vcDiscount);
                            } else {
                                System.out.printf("\t\t\t\t\t\t\tDiscount: \t%15.2f\n", sdDiscount);
                            }
                            System.out.printf("\t\t\t\t\t\t\tTax(6%%): \t%15.2f\n", taxCharge);
                            System.out.printf("\t\t\t\t\t\t\tTotal: \t\t%15.2f\n", total);
                            System.out.println("\t\t\t\t\t\t\t================================");
                            System.out.printf("\t\t\t\t\t\t\tCash tendered: \t%15.2f\n", amountPaid);
                            System.out.printf("\t\t\t\t\t\t\tChange: \t%15.2f\n", balance);
                            System.out.println(" =======================================================================================");

                            System.out.print("\n\t\t             Thank you for shopping at Hay Day. "
                                    + "\n\t\t            Your business is greatly appreciated. "
                                    + "\n\t\t\t         Please come back soon. "
                                    + "\n\n\t\t\t             Have a nice day!\n");
                            System.out.println("----------------------------------------------------------------------------------------");

//                            for (int i : orderedProdQty) {
//                                
//                            }
                            
                            for (int i = 0 ; i< orderedProdAmount.size() ; i++) {
                                orderedProductQty.add(orderedProdQty.get(i));
                                orderedProductAmount.add(orderedProdAmount.get(i));
                            }
          
                            //Arrays are cleared before continue another order
                            orderedProdId.clear();
                            orderedProdName.clear();
                            orderedProdQty.clear();
                            orderedUnitPrice.clear();
                            orderedProdAmount.clear();
                        }else{
                            System.out.println("\nOrdered successfully !\n");
                        }
                    } else if (continuePayment == 'n' || continuePayment == 'N') {
                        System.out.println("\nOrder is cancelled...\n");
                        cancelOrder = true;
                    }
                    break;

                case 3:

                    System.out.println("\n===============================");
                    System.out.println("=   A D D  CATEGORY//PRODUCT  =");
                    System.out.println("===============================");
                    int addnew = 0;

                    do {
                        System.out.println("\n1. New Category");
                        System.out.println("2. New Product");
                        System.out.println("3. Quit");
                        System.out.print("Enter choice > ");
                        addnew = scan.nextInt();

                        switch (addnew) {
                            case 1:
                                int addcat = cat.length - newCat;
                                if (addcat <= cat.length) {
                                    System.out.print("\nCategory Code  > ");
                                    char code = scan.next().charAt(0);
                                    System.out.print("Category Title > ");
                                    String title = scan.next();
                                    if (validateNewCategory(cat, code, title, newCat) == 0) {
                                        cat[addcat] = new Category(code, title);
                                        System.out.println("New Category Added ! --> " + cat[addcat].toString() + "\n");
                                        newCat--;
                                    }
                                } else {
                                    System.out.println("Exceeded the limit of adding new categories.");
                                }
                                break;

                            case 2:
                                int addprod = product.length - newProd;
                                if (addprod <= product.length) {
                                    System.out.println("\n------------------------------------------------------------------------------------");
                                    for (int i = 0; i < cat.length - newCat; i++) {
                                        System.out.print("| " + (i + 1) + ". " + cat[i].getName() + " |");
                                    }
                                    System.out.println("\n------------------------------------------------------------------------------------");

                                    System.out.print("Category > ");
                                    int temp = scan.nextInt();
                                    scan.nextLine();

                                    try{
                                        System.out.print("Product ID > ");
                                        String prodID = scan.nextLine();
                                        System.out.print("Name > ");
                                        String prodName = scan.nextLine();
                                        System.out.print("Price > ");
                                        double prodPrice = scan.nextDouble();
                                        scan.nextLine();
                                        System.out.print("Detail > ");
                                        String prodDetail = scan.nextLine();
                                        System.out.print("Stock > ");
                                        int prodStock = scan.nextInt();
                                        scan.nextLine();
                                        
                                        //                            while (!isdigit(pnum[i])) {
//				printf("Phone number : ");
//				rewind(stdin);
//				scanf("%s", pnum);
//                            for (i = 0; i < pnum[i]; i++) {
//					if (isdigit(pnum[i])) 

                                        if (validateNewProduct(cat[temp - 1], product, prodID, prodName, prodPrice, prodDetail, prodStock, newProd) == 0) {
                                            product[addprod] = new Product(cat[temp - 1], prodID, prodName, prodPrice, prodDetail, prodStock);
                                            System.out.println("New Product Added ! --> " + product[addprod].toString() + "\n");
                                            newProd--;
                                        }

                                    }catch(Exception e){
                                        System.out.println("Something went wrong...");
                                    }

                                } else {
                                    System.out.println("Exceeded the limit of adding new products.");
                                }
                            case 3: System.out.println("Quit...");break;
                            default : System.out.println("Invalid Input...");
                        }
                    } while (addnew >= 1 && addnew <= 2);
                    break;

                case 4:
                    System.out.println("\n===========================");
                    System.out.println("=   S T O C K  Management =");
                    System.out.println("===========================");
                    int stockControl = 0;
                    do {
                        System.out.println("\n1. Stock Check");
                        System.out.println("2. Increase Stock");
                        System.out.println("3. Decrease Stock");
                        System.out.println("4. Quit");
                        System.out.print("Enter choice > ");
                        stockControl = scan.nextInt();

                        switch (stockControl) {
                            case 1:
                                int chkStock = 0;
                                do {
                                    System.out.println("\n1. By product ID");
                                    System.out.println("2. By category");
                                    System.out.println("3. By range of stock quantity");
                                    System.out.println("4. Quit");
                                    System.out.print("Enter choice > ");
                                    chkStock = scan.nextInt();

                                    switch (chkStock) {
                                        case 1:
                                            int count = 0;
                                            System.out.print("\nEnter Product ID > ");
                                            String stockID = scan.next();
                                            System.out.println("\nS T O C K ");
                                            System.out.println("===========================================");
                                            System.out.println("ID \t Name \t\t\t Stock");
                                            System.out.println("===========================================");
                                            for (int i = 0; i < product.length - newProd; i++) {
                                                if(product[i].getId().equalsIgnoreCase(stockID)){
                                                    System.out.printf("%s \t %-20s \t %d \n", product[i].getId(), product[i].getName(), product[i].getStock());
                                                    count++;
                                                }
                                            }
                                            if(count <= 0){
                                                System.out.printf("No product found!");
                                            }
                                            System.out.println("===========================================");
                                            break;

                                        case 2:
                                            System.out.println("\n------------------------------------------------------------------------------------");
                                            for (int i = 0; i < cat.length - newCat; i++) {
                                                System.out.print("| " + (i + 1) + ". " + cat[i].getName() + " |");
                                            }
                                            System.out.println("\n------------------------------------------------------------------------------------");

                                            System.out.print("\nEnter choice > ");
                                            int catStock = scan.nextInt();

                                            if (catStock >= 0 && catStock <= cat.length) {

                                                System.out.println("\nS T O C K in " + cat[catStock - 1].getName());
                                                System.out.println("===========================================");
                                                System.out.println("ID \t Name \t\t\t Stock");
                                                System.out.println("===========================================");
                                                for (int i = 0; i < product.length - newProd; i++) {
                                                    if (cat[catStock - 1] == product[i].getSource()) {
                                                        System.out.printf("%s \t %-20s \t %d \n", product[i].getId(), product[i].getName(), product[i].getStock());
                                                    }
                                                }
                                                System.out.println("===========================================");

                                            } else {
                                                System.out.println("Invalid input");
                                            }
                                            break;

                                        case 3:
                                            System.out.print("Enter minimum stock quantity > ");
                                            int minStock = scan.nextInt();
                                            scan.nextLine();
                                            System.out.print("Enter maximum stock quantity > ");
                                            int maxStock = scan.nextInt();
                                            scan.nextLine();

                                            System.out.println("\nS T O C K quantity between " + minStock + " and " + maxStock);
                                            System.out.println("===========================================");
                                            System.out.println("ID \t Name \t\t\t Stock");
                                            System.out.println("===========================================");
                                            for (int i = 0; i < product.length - newProd; i++) {
                                                if (product[i].getStock() >= minStock && product[i].getStock() <= maxStock) {
                                                    System.out.printf("%s \t %-20s \t %d \n", product[i].getId(), product[i].getName(), product[i].getStock());
                                                }
                                            }
                                            System.out.println("===========================================");
                                            break;
                                        case 4:System.out.println("Quit..");break;
                                        default:System.out.println("Invalid input..");
                                    }
                                } while (chkStock >=1 && chkStock <= 3);
                                break;
                            case 2:
                            case 3:

                                int find = 0;
                                int found = 0;
                                while (find == 0 && found == 0) {
                                    System.out.print("\nEnter Product ID > ");
                                    String stock = scan.next();

                                    for (int i = 0; i < product.length - newProd; i++) {
                                        if (product[i].getId().equalsIgnoreCase(stock)) {
                                            System.out.print("Product found   : ");
                                            System.out.printf("%s \t %-15s Stock: %d \n", product[i].getId(), product[i].getName(), product[i].getStock());
                                            found++;

                                            System.out.print("Enter amount > ");
                                            int stockInOut = scan.nextInt();
                                            scan.nextLine();

                                            if (stockControl == 2) {
                                                product[i].increaseStock(stockInOut);
                                                System.out.println("Stock increased !");
                                            } else {
                                                product[i].decreaseStock(stockInOut);
                                                System.out.println("Stock decreased !");
                                            }
                                        }
                                    }

                                    if (found == 0) {
                                        System.out.println("Product ID not found...");
                                        System.out.print("Continue finding ? [1 = YES]: ");
                                        int yes = scan.nextInt();
                                        scan.nextLine();

                                        if (yes != 1) {
                                            found++;
                                        }
                                    }
                                }
                                break;
                            case 4:System.out.println("Quit..");break;
                            default:System.out.println("Invalid input...");
                        }
                    } while (stockControl >= 1 && stockControl <= 3);
                    break;

                case 5: 
                    //--Summary--//
                    int totalOrder;
                    int summaryOrderID;
                    double soldTotalAmount;
                    double totalAmount;
                    double totalDiscounts;
                    double totalVoucher;
                    double grandFinalTotalAmount;

                    Summary summary = new Summary();
                    summaryOrderID = Receipt.getOrderID();
                    totalOrder = summary.calculateTotalOrder(summaryOrderID);
                    totalAmount = summary.calculateAmount(subtotal);
                    totalDiscounts = summary.calculateDiscount(sdDiscount);
                    totalVoucher = summary.calculateVoucher(vcDiscount);
                    grandFinalTotalAmount = summary.calculateFinalTotalAmount(totalAmount,totalDiscounts,totalVoucher);

                    System.out.printf("%61s","SUMMARY");
                    System.out.printf("%61s","\n-------");
                    System.out.printf("%-15s", "Total Order : " + totalOrder + "%55s", "Date" + summary.getDate());
                    System.out.println("\n=============================================================================");
                    System.out.println("Product ID          Products            Sold                 Amount");
                    for (int i = 0; i < product.length - newProd; i++) {
                        System.out.printf("%s               %-20s", product[i].getId(), product[i].getName());
                    };
                    for(int i = 0; i < orderedProductQty.size(); i++){
                            soldTotalAmount = summary.calculateSoldAmount(orderedProductQty.get(i), orderedProductAmount.get(i));
                            System.out.printf("%-20d %-20.2f", orderedProductQty.get(i), soldTotalAmount);
                    }
                    System.out.println("=============================================================================");
                    System.out.printf("Total Amount $%67.2f\n", totalAmount);
                    System.out.printf("Total Discount $%65.2f\n", totalDiscounts);
                    System.out.printf("Total Voucher Amount $%59.2f\n", totalVoucher);
                    System.out.printf("Final Total Amount $%61.2f\n", grandFinalTotalAmount);
                    break;
                case 6: 
                    System.out.println("\n===========================");
                    System.out.println("=   S T A F F List =");
                    System.out.println("===========================");

                    for(int i = 0 ; i < staff.length ; i++){
                        if(staff[i] instanceof Manager){
                            System.out.println("\nManager");    
                        }else if(staff[i] instanceof Cashier){
                            System.out.println("\nCashier");  
                        }

                        System.out.println((i+1) + "\t" + staff[i].toString());    
                    }
                    break;
                default : 
            }

        } while (selection >= 0 && selection <= 6);
            
    }

}
