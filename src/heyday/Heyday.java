/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heyday;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *
 * @author HeyDay
 */
public class Heyday {

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

    public static User[] heyDayStaff() {

        User[] staff = {
            new Manager("xinying", "xinying", 'F', "010-0000000"),
            new Cashier("junyan", "junyan", 'M', "011-1111111"),
            new Cashier("huihui", "huihui", 'F', "012-2222222"),
            new Cashier("xinyi", "xinyi", 'F', "013-3333333")
        };

        return staff;
    }

    public static void displayProducts(int selectCat, Category cat[], Product[] product, int newProd) {
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
                        System.err.println("\nNo record found.");
                    } else {
                        System.out.println("\n" + count + " Product(s)");
                    }
                    break;
            }
            System.out.println("=================================================================================");
        } else {
            if (selectCat > cat.length) {
                System.err.println("Invalid input...");
            } else {
                System.out.println("Quit...");
            }
        }
    }

    public static int validateNewCategory(Category cat[], char code, String title, int newCat) {

        int error = 0;

        if (title.length() <= 2 || title.length() > 15) {
            System.err.println("\"" + title + "\"" + " Title length is out of range.");
            error++;
        }

        for (int i = 0; i < cat.length - newCat; i++) {
            if (cat[i].getCode() == Character.toUpperCase(code)) {
                System.err.println("\"" + code + "\"" + " Code is repeated.");
                error++;
            }
            if (cat[i].getName().equalsIgnoreCase(title)) {
                System.err.println("\"" + title + "\"" + " Category exist.");
                error++;
            }
        }

        return error;
    }

    public static int validateNewProduct(Category cat, Product[] product, String id, String name, double price, String detail, int stock, int newProd) {

        int error = 0;
        int digit = 0;
        int letter = 0;
        char idFLetter = Character.toUpperCase(id.charAt(0));

        for (int i = 0; i < id.length(); i++) {
            char chkID = id.charAt(i);
            if (Character.isDigit(chkID)) {
                digit++;
            }
        }

        for (int i = 0; i < name.length(); i++) {
            char chkName = name.charAt(i);
            if (Character.isLetter(chkName)) {
                letter++;
            }
        }

        //check id fromat
        if (cat.getCode() != idFLetter || id.length() != 4 || digit != 3) {
            System.err.println("\"" + id + "\"" + " ID is in wrong format.");
            System.err.println("\tExample of Correct ID Format: Category Code + Sequence Numbers ->" + cat.getCode() + "001");
            error++;
        }

        //check name format
        if (letter != name.length()) {
            System.err.println("\"" + name + "\"" + " Name should only contains letters.");
            error++;
        } else if (name.length() <= 2 || name.length() > 15) {
            System.err.println("\"" + name + "\"" + " Name length is out of range.");
            error++;
        }

        //check duplicate id and name
        for (int i = 0; i < product.length - newProd; i++) {

            if (product[i].getId().equalsIgnoreCase(id)) {
                System.err.println("\"" + id + "\"" + " Duplicate ID.");
                error++;
            }

            if (product[i].getName().equalsIgnoreCase(name)) {
                System.err.println("\"" + name + "\"" + " Duplicate Name.");
                error++;
            }
        }

        //check detail length
        if (detail.length() <= 2 || detail.length() > 20) {
            System.err.println("\"" + detail + "\"" + " Detail length is out of range.");
            error++;
        }

        //check price
        if (price <= 0.0 || price >= 9999.99) {
            System.err.println("\"" + price + "\"" + " Price is out of range.");
            error++;
        }

        //check stock
        if (stock <= 0 || stock >= 9999) {
            System.err.println("\"" + stock + "\"" + " Stock is out of range.");
            error++;
        }

        return error;
    }

    /*----- Receipt -----*/
    public static double calculateSubtotal(double subtotal, double amountToPay) {
        subtotal += amountToPay;
        return subtotal;
    }

    public static double calculateTax(double subtotal, double tax_rate) {
        double taxCharge;
        taxCharge = tax_rate * subtotal;
        return taxCharge;
    }

    public static double calculateTotal(double subtotal, double taxCharge, double totalDiscount) {
        double total;
        total = subtotal + taxCharge - totalDiscount;
        return total;
    }

    public static double calculateBalance(double payment, double total) {
        double balance;
        balance = payment - total;
        return balance;
    }

    public static void main(String[] args) {

        User[] staff = heyDayStaff();
        Category[] cat = heyDayCategory();
        Product[] product = heyDayProduct(cat);
        ArrayList<String> usedVc = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<Order> tempItem = new ArrayList<>();

        //number of new categoriy and product can be added
        int newCat = 2;
        int newProd = 5;

        //--Receipt--//
        double subtotal;
        double amountPaid = 0;
        double total;
        double taxCharge;
        double balance = 0;
        double vcDiscount = 0;
        double sdDiscount = 0;
        double totalDiscount;

        //--Summary--//
        Summary summary = new Summary();

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
        do {
            boolean correctUsername = false;
            boolean correctPassword = false;
            System.out.println("                                     --- LOGIN --- ");
            System.out.print("                          Username : ");
            String username = scan.nextLine();
            System.out.print("                          Password : ");
            String password = scan.nextLine();

            for (int i = 0; i < staff.length; i++) {
                if (username.equals(staff[i].getUsername())) {
                    correctUsername = true;
                    staffNo = i;
                }

                if (password.equals(staff[i].getPassword())) {
                    correctPassword = true;
                    staffNo = i;
                }
            }

            if (correctUsername == true && correctPassword == true) {
                System.out.println("\n\nHello " + staff[staffNo].getUsername() + " welcome back !");
                login = true;
            } else {
                if (correctUsername == false) {
                    System.err.println("                          Wrong username. Please try again.");
                } else if (correctPassword == false) {
                    System.err.println("                          Wrong password. Please try again.");
                } else {
                    System.err.println("                          Wrong username and password. Please try again.");
                }
            }

        } while (login == false);

        int selection = 7;
        do {
            boolean menu = false;

            while (menu == false) {
                System.out.println("\n1. Display HayDay Products");
                System.out.println("2. Order");

                if (staff[staffNo] instanceof Manager) {
                    System.out.println("3. Add Category/Product");
                    System.out.println("4. Stock Management");
                    System.out.println("5. Order Summary");
                    System.out.println("6. Staff List");
                }

                System.out.println("0. Logout");
                System.out.print("Enter choice > ");
                try {
                    selection = scan.nextInt();
                    if (staff[staffNo] instanceof Manager) {
                        if (selection < 0 || selection > 6) {
                            System.err.println("\nInvalid choice, please try again");
                            menu = false;
                        } else {
                            menu = true;
                        }
                    } else {
                        if (selection < 0 || selection > 2) {
                            System.err.println("\nInvalid choice, please try again");
                            menu = false;
                        } else {
                            menu = true;
                        }
                    }
                } catch (InputMismatchException a) {
                    System.err.println("Must enter a number");
                    scan.next();
                }
            }

            switch (selection) {
                case 0:
                    char out;
                    do {
                        System.out.print("\nAre you sure want to logout? (Y / N) > ");
                        out = scan.next().charAt(0);

                        if (out == 'y' || out == 'Y') {
                            System.out.println("\nGoodbye, ^_^  Have a nice day !");
                            selection = 7;
                        } else if (out != 'y' && out != 'Y' && out != 'n' && out != 'N') {
                            System.err.println("Invalid input, please try again");
                        }
                    } while (out != 'y' && out != 'Y' && out != 'n' && out != 'N');
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

                        try {
                            selectCat = scan.nextInt();
                        } catch (InputMismatchException a) {
                            System.err.println("Must enter a number");
                            scan.next();
                        }
                        displayProducts(selectCat, cat, product, newProd);
                    } while (selectCat != 0);
                    break;

                case 2:
                    System.out.println("\n===========================");
                    System.out.println("=    O R D E R Product    =");
                    System.out.println("===========================");

                    String orderProductID;
                    int orderQty = 0;
                    int orderStockQty = 0;
                    int orderError = 0;
                    int orderFound = 0;
                    int orderStockFound = 0;
                    char continueAddOrder;
                    char continuePayment;
                    double prodAmount = 0;
                    boolean cancelOrder = false;
                    Order addOrder = new Order();

                    int index = 0;

                    do {
                        orderError = 0;
                        orderFound = 0;
                        do {

                            orderError = 0;
                            orderFound = 0;
                            System.out.print("\nEnter Product ID > ");
                            orderProductID = scan.next();

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
                                    System.err.println("No record found, please try again");

                                }
                            }
                        } while (orderFound == 0 || orderError != 0);

                        do {
                            //Enter Unit
                            do {
                                System.out.print("\nEnter unit > ");
                                try {
                                    orderQty = scan.nextInt();

                                    if (orderQty == 0) {
                                        System.err.println("Unit cannot be zero, please try again");
                                    }
                                } catch (InputMismatchException a) {
                                    System.err.println("Must enter a number");
                                    scan.next();
                                }
                            } while (orderQty == 0);

                            for (int i = 0; i < product.length - newProd; i++) {
                                if (orderProductID.equalsIgnoreCase(product[i].getId())) {
                                    if (orderQty <= product[i].getStock()) {
                                        orderStockQty++;
                                    }
                                }
                            }
                            //Out of Stock
                            if (orderStockQty == 0) {
                                System.err.println("Sorry, out of stock..");
                            } else {
                                for (int i = 0; i < product.length - newProd; i++) {
                                    if (orderProductID.equalsIgnoreCase(product[i].getId())) {
                                        Order takeOrder = new Order(product[i], orderQty);
                                        System.out.println("\nProduct ID : " + takeOrder.getOrderedProd().getId()
                                                + "\nProduct : " + takeOrder.getOrderedProd().getName()
                                                + "\nOrder Quantity : " + takeOrder.getOrderQty());
                                        System.out.printf("Amount : $%.2f\n", takeOrder.calculateAmount(product[i]));
                                        prodAmount += takeOrder.calculateAmount(product[i]);
                                        addOrder = takeOrder;
                                        tempItem.add(index, takeOrder);
                                        index++;
                                        System.out.println("--------------------------------------------------------------------------------\n");
                                        orderStockFound++;
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
                    if (continuePayment == 'n' || continuePayment == 'N') {
                        System.err.println("\nOrder is cancelled...\n");
                        cancelOrder = true;
                        tempItem.clear();
                    } else if (continuePayment == 'Y' || continuePayment == 'y') {
                        Receipt receipt = new Receipt();

                        ArrayList<Order> orderedItem = new ArrayList<>(tempItem);
                        tempItem.clear();
                        Order.setOrderedDetails(orderedItem);
                        for (int i = 0; i < orderedItem.size(); i++) {
                            orders.add(orderedItem.get(i));
                        }
                        Receipt.setTotalOrder(orders);

                        Discount voucherTen1 = new Discount("HDVC10111", 10);
                        Discount voucherTen2 = new Discount("HDVC10112", 10);
                        Discount voucherTen3 = new Discount("HDVC10113", 10);
                        Discount voucherTen4 = new Discount("HDVC10114", 10);
                        Discount voucherTwenty1 = new Discount("HDVC20111", 20);
                        Discount voucherTwenty2 = new Discount("HDVC20112", 20);
                        Discount voucherTwenty3 = new Discount("HDVC20113", 20);
                        Discount voucherTwenty4 = new Discount("HDVC20114", 20);

                        Discount specialDeals = new Discount();

                        int vcCount = 0;
                        char printReceipt;
                        char inputAnother;
                        char retypeChoice;
                        char method;

                        boolean inputAgain = false;
                        boolean invalidCode = true;
                        boolean used;

                        boolean specialdeals = false;
                        boolean cowSheepPurchased = false;
                        boolean freeItems = false;
                        boolean flowerOrchardPurchased = false;

                        subtotal = calculateSubtotal(receipt.getSubtotal(), prodAmount);

                        //For Summary 
                        summary.calculateAmount(subtotal);

                        //----------------- Special Promotions ----------------- 
                        //Cow+Sheep = 10% discount
                        for (int i = 0; i < orderedItem.size(); i++) {
                            if (cowSheepPurchased == false) {                       //run only one time
                                if (orderedItem.get(i).getOrderedProd().getName().equals("Cow")) {
                                    for (int j = 0; j < orderedItem.size(); j++) {
                                        if (orderedItem.get(j).getOrderedProd().getName().equals("Sheep")) {
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
                            for (int j = 0; j < orderedItem.size(); j++) {
                                if (freeItems == false) {                        //run only one time
                                    for (int i = 0; i < product.length - newProd; i++) {
                                        if (orderedItem.get(j).getOrderedProd().getId().equals(product[i].getId())) {
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
                                for (int j = 0; j < orderedItem.size(); j++) {
                                    if (flowerOrchardPurchased == false) {                          //run only one time
                                        for (int i = 0; i < product.length - newProd; i++) {
                                            if (orderedItem.get(j).getOrderedProd().getId().equals(product[i].getId())) {
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

                        if (cowSheepPurchased) {
                            sdDiscount = Discount.getSPECIAL_DEALS_TEN() * subtotal;                //calculate discount
                        } else if (flowerOrchardPurchased) {
                            sdDiscount = Discount.getSPECIAL_DEALS_FIVE() * subtotal;                //calculate discount
                        } else {
                            sdDiscount = 0;
                        }

                        if (subtotal > 50.00 && !specialdeals) {

                            if (vcCount == 0) {
                                do {
                                    System.out.print(" Any voucher (Y / N) > ");
                                    method = scan.next().charAt(0);
                                    if (method != 'N' && method != 'n' && method != 'Y' && method != 'y') {
                                        System.err.println("Invalid input, please try again");
                                    }
                                } while (method != 'N' && method != 'n' && method != 'Y' && method != 'y');

                                if (method == 'y' || method == 'Y') {
                                    do {
                                        used = false;
                                        if (vcCount == 0) {
                                            System.out.print("\n Please enter voucher code: ");
                                            String inputVc = scan.next().toUpperCase();
                                            for (int i = 0; i < Discount.getVoucher().size(); i++) {
                                                if (inputVc.equals(Discount.getVoucher().get(i).getCode())) {
                                                    for (int m = 0; m < usedVc.size(); m++) {
                                                        if (usedVc.get(m).equals(Discount.getVoucher().get(i).getCode())) {
                                                            System.err.println(" Discount implemented failed, this voucher has already been used.");
                                                            used = true;
                                                            do {
                                                                System.out.print("Input another voucher code (Y / N) > ");
                                                                inputAnother = scan.next().charAt(0);
                                                                scan.nextLine();
                                                                switch (inputAnother) {
                                                                    case 'y':
                                                                    case 'Y':
                                                                        inputAgain = true;
                                                                        break;
                                                                    case 'n':
                                                                    case 'N':
                                                                        inputAgain = false;
                                                                        break;
                                                                    default:
                                                                        System.err.println("Invalid input, please try again");
                                                                }
                                                            } while (inputAnother != 'n' && inputAnother != 'N' && inputAnother != 'y' && inputAnother != 'Y');
                                                        }
                                                    }
                                                    if (!used) {
                                                        vcCount++;
                                                        System.out.printf("\n $%.2f Discount is implemented successfully...\n", Discount.getVoucher().get(i).getAmount());
                                                        vcDiscount = Discount.getVoucher().get(i).getAmount();
                                                        usedVc.add(Discount.getVoucher().get(i).getCode());
                                                        inputAgain = false;
                                                    }
                                                    invalidCode = false;
                                                    break;
                                                }
                                            }

                                            if (invalidCode) {
                                                System.err.println("Invalid voucher code");
                                                System.out.print("Re-type voucher code (Y / N) > ");
                                                retypeChoice = scan.next().charAt(0);
                                                scan.nextLine();
                                                do {
                                                    switch (retypeChoice) {
                                                        case 'y':
                                                        case 'Y':
                                                            inputAgain = true;
                                                            break;
                                                        case 'n':
                                                        case 'N':
                                                            inputAgain = false;
                                                            break;
                                                        default:
                                                            System.err.println("Invalid input, please try again");
                                                    }
                                                } while (retypeChoice != 'n' && retypeChoice != 'N' && retypeChoice != 'y' && retypeChoice != 'Y');
                                            }
                                        }
                                    } while (inputAgain);
                                }
                            }
                        }

                        if (sdDiscount > 0) {
                            totalDiscount = sdDiscount;
                            //For Summary
                            summary.calculateDiscount(sdDiscount);
                        } else if (vcDiscount > 0) {
                            totalDiscount = vcDiscount;
                            //For Summary
                            summary.calculateVoucher(vcDiscount);
                        } else {
                            totalDiscount = 0;
                        }

                        //Information are shown before input amount paid by the customers
                        taxCharge = calculateTax(subtotal, Receipt.getTAX_RATE());
                        total = calculateTotal(subtotal, taxCharge, totalDiscount);

                        System.out.printf("\n Subtotal: \t\t$%9.2f\n", subtotal);
                        if (vcCount > 0) {
                            System.out.printf(" Voucher Discount: \t$%9.2f\n", vcDiscount);
                        } else {
                            System.out.printf(" Discount: \t\t$%9.2f\n", sdDiscount);
                        }
                        System.out.printf(" Extra Charges (Tax): \t$%9.2f\n", taxCharge);
                        System.out.printf(" Total: \t\t$%9.2f\n", total);
                        do {

                            try {
                                System.out.print(" Amount Paid: \t\t$  ");
                                amountPaid = scan.nextDouble();
                                balance = calculateBalance(amountPaid, total);
                                if (amountPaid >= total) {
                                    System.out.printf(" Change: \t\t$%9.2f\n", balance);
                                } else {
                                    System.err.println("Not enough payment, please try again.");
                                }
                            } catch (InputMismatchException a) {
                                System.err.println("\nMust enter a number");
                                scan.next();
                            }
                        } while (amountPaid < total);

                        for (int i = 0; i < orderedItem.size(); i++) {
                            for (int j = 0; j < product.length - newProd; j++) {
                                if (orderedItem.get(i).getOrderedProd().getId().equals(product[j].getId())) {
                                    product[j].decreaseStock(orderedItem.get(i).getOrderQty());
                                }
                            }
                        }

                        System.out.println("\nOrdered successfully !\n");
                        //print receipt
                        do {
                            System.out.print("\nPrint receipt (Y / N) > ");                         //Prompt to print receipt
                            printReceipt = scan.next().charAt(0);
                            if (printReceipt != 'y' && printReceipt != 'Y' && printReceipt != 'n' && printReceipt != 'N') {
                                System.err.println("Invalid input, please try again");
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
                            System.out.println("\n\t\t\t\t      Staff: " + staff[staffNo].getUsername());
                            System.out.println(" =======================================================================================");
                            System.out.println(" ID\t\tItem Ordered\t\tUnit\t\tUnit Price\t\tAmount");
                            System.out.println(" =======================================================================================\n");

                            for (int i = 0; i < orderedItem.size(); i++) {
                                System.out.printf(" %-14s %-20s %6d \t\t$%9.2f \t      $%8.2f\n", orderedItem.get(i).getOrderedProd().getId(), orderedItem.get(i).getOrderedProd().getName(), orderedItem.get(i).getOrderQty(), orderedItem.get(i).getOrderedProd().getPrice(), (orderedItem.get(i).getOrderQty() * orderedItem.get(i).getOrderedProd().getPrice()));
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
                        }
                        orderedItem.clear();
                    }
                    break;

                case 3:

                    System.out.println("\n===============================");
                    System.out.println("=   A D D  CATEGORY//PRODUCT  =");
                    System.out.println("===============================");
                    int addnew = 0;

                    do {
                        /*---------------------------------------------------------------------------------------------------------------- not resolved yet ------------------------------------*/

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
                                    System.err.println("Exceeded the limit of adding new categories.");
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

                                    try {
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

                                        if (validateNewProduct(cat[temp - 1], product, prodID, prodName, prodPrice, prodDetail, prodStock, newProd) == 0) {
                                            product[addprod] = new Product(cat[temp - 1], prodID, prodName, prodPrice, prodDetail, prodStock);
                                            System.out.println("New Product Added ! --> " + product[addprod].toString() + "\n");
                                            newProd--;
                                        }

                                    } catch (Exception e) {
                                        System.err.println("Something went wrong...");
                                    }

                                } else {
                                    System.err.println("Exceeded the limit of adding new products.");
                                }
                            case 3:
                                System.out.println("Quit...");
                                break;
                            default:
                                System.err.println("Invalid Input...");
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
                                                if (product[i].getId().equalsIgnoreCase(stockID)) {
                                                    System.out.printf("%s \t %-20s \t %d \n", product[i].getId(), product[i].getName(), product[i].getStock());
                                                    count++;
                                                }
                                            }
                                            if (count <= 0) {
                                                System.err.printf("No product found!");
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
                                                System.err.println("Invalid input");
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
                                        case 4:
                                            System.out.println("Quit..");
                                            break;
                                        default:
                                            System.err.println("Invalid input..");
                                    }
                                } while (chkStock >= 1 && chkStock <= 3);
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
                                        System.err.println("Product ID not found...");
                                        System.out.print("Continue finding ? [1 = YES]: ");
                                        int yes = scan.nextInt();
                                        scan.nextLine();

                                        if (yes != 1) {
                                            found++;
                                        }
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Quit..");
                                break;
                            default:
                                System.err.println("Invalid input...");
                        }
                    } while (stockControl >= 1 && stockControl <= 3);
                    break;

                case 5:

                    double soldTotalAmount;
                    double grandFinalTotalAmount;
                    grandFinalTotalAmount = summary.calculateFinalTotalAmount(summary.getNewSubtotal(), summary.getNewsdDiscount(), summary.getNewvcDiscount());

                    System.out.println("\t\t\t\t   DAILY SUMMARY");
                    System.out.println("\t\t\t\t   -------------\n");
                    System.out.printf("Total Order : %-15d \t\t\t\tDate: %15s", Receipt.getOrderID(), summary.getDate());
                    System.out.println("\n===============================================================================");
                    System.out.print("Product ID\t\tProducts\t\tSold\t\t\tAmount");
                    System.out.println("\n===============================================================================");
                    for (int i = 0; i < Receipt.getTotalOrder().size(); i++) {
                        soldTotalAmount = 0;
                        int soldTotalQty = 0;
                        soldTotalAmount += summary.calculateSoldAmount(Receipt.getTotalOrder().get(i).getOrderQty(), Receipt.getTotalOrder().get(i).getOrderedProd().getPrice());
                        soldTotalQty += Receipt.getTotalOrder().get(i).getOrderQty();
                        System.out.printf("%-23s%4s%25d \t%22.2f\n", Receipt.getTotalOrder().get(i).getOrderedProd().getId(), Receipt.getTotalOrder().get(i).getOrderedProd().getName(), soldTotalQty, soldTotalAmount);
                    }
                    System.out.println("===============================================================================");
                    System.out.printf("Total Amount $%65.2f\n", summary.getNewSubtotal());
                    System.out.printf("Total Discount $%63.2f\n", summary.getNewsdDiscount());
                    System.out.printf("Total Voucher Amount $%57.2f\n", summary.getNewvcDiscount());
                    System.out.printf("Final Total Amount $%59.2f\n", grandFinalTotalAmount);
                    break;

                case 6:
                    System.out.println("\n===========================");
                    System.out.println("=   S T A F F List =");
                    System.out.println("===========================");

                    for (int i = 0; i < staff.length; i++) {
                        if (staff[i] instanceof Manager) {
                            System.out.println("\nManager");
                        } else if (staff[i] instanceof Cashier) {
                            System.out.println("\nCashier");
                        }

                        System.out.println((i + 1) + "\t" + staff[i].toString());
                    }
                    break;
                default:
            }

        } while (selection >= 0 && selection <= 6);

    }

}