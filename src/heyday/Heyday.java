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

    static Scanner scan = new Scanner(System.in);
    public static ArrayList<Order> tempItem = new ArrayList<>();
    public static ArrayList<Order> orders = new ArrayList<>();
    static Summary summary = new Summary();

    public static ArrayList<Category> cat = new ArrayList<Category>() {
        {
            Category[] cat = new Category[]{
                new Category('L', "Livestock"),
                new Category('F', "Field"),
                new Category('D', "Dairy"),
                new Category('H', "Flower"),
                new Category('O', "Orchard")
            };
            add(cat[0]);
            add(cat[1]);
            add(cat[2]);
            add(cat[3]);
            add(cat[4]);
        }
    };
    public static ArrayList<Product> product = new ArrayList<Product>() {
        {
            Product[] prod = new Product[]{
                new Product(cat.get(0), "L001", "Cow", 2000.00, "per head", 30),
                new Product(cat.get(0), "L002", "Chicken", 1.51, "per kg", 80),
                new Product(cat.get(0), "L003", "Sheep", 160.00, "per head", 50),
                new Product(cat.get(1), "F001", "Wheat", 4.56, "per bushel", 100),
                new Product(cat.get(1), "F002", "Corn", 3.16, "per bushel", 100),
                new Product(cat.get(1), "F003", "Soybeans", 8.14, "per bushel", 100),
                new Product(cat.get(2), "D001", "Butter", 13.76, "per cwt", 100),
                new Product(cat.get(2), "D002", "Milk", 13.79, "per cwt", 100),
                new Product(cat.get(2), "D003", "Cheese", 24.54, "per cwt", 100),
                new Product(cat.get(3), "H001", "Sunflowers", 17.75, "per 10 stem bunch", 100),
                new Product(cat.get(3), "H002", "Buttercup", 14.75, "per 10 stem bunch", 100),
                new Product(cat.get(3), "H003", "Loosestrife", 8.50, "per 10 stem bunch", 100),
                new Product(cat.get(4), "O001", "Apples", 1.00, "per pound", 100),
                new Product(cat.get(4), "O002", "Cherries", 5.00, "per pound", 100),
                new Product(cat.get(4), "O003", "Grapes", 0.60, "per pound", 100)

            };

            add(prod[0]);
            add(prod[1]);
            add(prod[2]);
            add(prod[3]);
            add(prod[4]);
            add(prod[5]);
            add(prod[6]);
            add(prod[7]);
            add(prod[8]);
            add(prod[9]);
            add(prod[10]);
            add(prod[11]);
            add(prod[12]);
            add(prod[13]);
            add(prod[14]);
        }
    };
    public static ArrayList<User> staff = new ArrayList<User>() {
        {

            User[] employee = {
                new Manager("xinying", "xinying", 'F', "010-0000000"),
                new Cashier("junyan", "junyan", 'M', "011-1111111"),
                new Cashier("huihui", "huihui", 'F', "012-2222222"),
                new Cashier("xinyi", "xinyi", 'F', "013-3333333")
            };

            add(employee[0]);
            add(employee[1]);
            add(employee[2]);
            add(employee[3]);
        }
    };

    public static User login() {

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

            for (int i = 0; i < staff.size(); i++) {
                if (username.equals(staff.get(i).getUsername())) {
                    correctUsername = true;
                    staffNo = i;
                }

                if (password.equals(staff.get(i).getPassword())) {
                    correctPassword = true;
                    staffNo = i;
                }
            }

            if (correctUsername == true && correctPassword == true) {
                System.out.println("\n\nHello " + staff.get(staffNo).getUsername() + " welcome back !");
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

        return staff.get(staffNo);
    }

    public static int mainMenu(User onDutyStaff) {

        int selection = 7;
        boolean menu = false;

        while (menu == false) {
            System.out.println("\n1. Display HayDay Products");
            System.out.println("2. Order");

            if (onDutyStaff instanceof Manager) {
                System.out.println("3. Add Category/Product");
                System.out.println("4. Stock Management");
                System.out.println("5. Order Summary");
                System.out.println("6. Staff List");
            }

            System.out.println("0. Logout");
            System.out.print("Enter choice > ");
            try {
                selection = scan.nextInt();
                if (onDutyStaff instanceof Manager) {
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
        return selection;
    }

    public static void displayProducts() {

        int selectCat = 8;
        do {
            System.out.println("\n=========================");
            System.out.println("== C A T E G O R I E S ==");
            System.out.println("=========================");
            System.out.println("1. All              ");

            for (int i = 0; i < cat.size(); i++) {
                System.out.println((i + 2) + ". " + cat.get(i).getName());
            }

            System.out.println("0. Quit             ");
            System.out.println("=========================");
            System.out.println("Total Products = " + Product.getTotalProduct());
            System.out.println("=========================");
            System.out.print("Enter choice > ");

            try {
                selectCat = scan.nextInt();
            } catch (InputMismatchException a) {
                System.err.print("Must enter a number. ");
                scan.next();
            }

            if (selectCat > cat.size() + 1) {
                System.err.println("Invalid input...");
            } else {
                System.out.println("\n=================================================================================");
                System.out.println("                                P R O D U C T S");
                System.out.println("=================================================================================");
                System.out.println("Code \t Category   ID\t     Product \t   Price \t\t\t   Stock");
                System.out.println("--------------------------------------------------------------------------------");
                switch (selectCat) {
                    case 1:
                        for (int i = 0; i < product.size(); i++) {
                            System.out.println(product.get(i).toString());
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
                        for (int i = 0; i < product.size(); i++) {
                            if (cat.get(selectCat - 2) == product.get(i).getSource()) {
                                System.out.println(product.get(i).toString());
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
            }
        } while (selectCat != 0);
    }

    public static double orderProducts() {

        String orderProductID;
        int orderStockQty = 0;
        char continueAddOrder;

        double prodAmount = 0;

        int index = 0;

        do {
            int orderError = 0;
            int orderFound = 0;
            do {
                orderError = 0;
                orderFound = 0;
                System.out.print("\nEnter Product ID > ");
                orderProductID = scan.next();

                for (int i = 0; i < product.size(); i++) {

                    if (orderProductID.equalsIgnoreCase(product.get(i).getId())) {
                        if (product.get(i).getStock() == 0) {
                            System.out.println("This product is out of stock, please increase stock before order.");
                            orderError++;
                        } else {
                            System.out.println("Code \t Category   ID\t     Product \t   Price \t\t\t   Stock");
                            System.out.println("--------------------------------------------------------------------------------");
                            System.out.println(product.get(i).toString());
                            System.out.println("--------------------------------------------------------------------------------\n");
                            orderFound++;
                        }
                    }
                }
                if (orderFound == 0 && orderError == 0) {
                    System.err.println("No record found, please try again");
                }
            } while (orderFound == 0 || orderError != 0);

            do {
                //Enter Unit
                int orderQty = 0;
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

                for (int i = 0; i < product.size(); i++) {
                    if (orderProductID.equalsIgnoreCase(product.get(i).getId())) {
                        if (orderQty <= product.get(i).getStock()) {
                            orderStockQty++;
                        }
                    }
                }
                //Out of Stock
                if (orderStockQty == 0) {
                    System.err.println("Sorry, out of stock..");
                } else {
                    for (int i = 0; i < product.size(); i++) {
                        if (orderProductID.equalsIgnoreCase(product.get(i).getId())) {
                            Order takeOrder = new Order(product.get(i), orderQty);
                            System.out.println("\nProduct ID : " + takeOrder.getOrderedProd().getId()
                                    + "\nProduct : " + takeOrder.getOrderedProd().getName()
                                    + "\nOrder Quantity : " + takeOrder.getOrderQty());
                            System.out.printf("Amount : $%.2f\n", takeOrder.calculateAmount(product.get(i)));
                            prodAmount += takeOrder.calculateAmount(product.get(i));
                            tempItem.add(index, takeOrder);
                            index++;
                            System.out.println("--------------------------------------------------------------------------------\n");
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

        return prodAmount;
    }

    public static void receipt(double prodAmount, User onDutyStaff) {

        ArrayList<String> usedVc = new ArrayList<>();

        //--Receipt--//
        double subtotal;
        double amountPaid = 0;
        double total;
        double taxCharge;
        double balance = 0;
        double vcDiscount = 0;
        double sdDiscount = 0;
        double totalDiscount;

        char continuePayment;

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

            receipt.setSubtotal(prodAmount);
            subtotal = receipt.getSubtotal();

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
                        for (int i = 0; i < product.size(); i++) {
                            if (orderedItem.get(j).getOrderedProd().getId().equals(product.get(i).getId())) {
                                if (product.get(i).getSource().getName().equals("Dairy") || product.get(i).getSource().getName().equals("Field")) {     //Purchase Dairy or Field products with subtotal exceeds $100 will get free items
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
                            for (int i = 0; i < product.size(); i++) {
                                if (orderedItem.get(j).getOrderedProd().getId().equals(product.get(i).getId())) {
                                    if (product.get(i).getSource().getName().equals("Flower") || product.get(i).getSource().getName().equals("Orchard")) {     //Purchase Flower or Orchard products with subtotal exceeds $100 will get 5% discount
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
            taxCharge = receipt.calculateTax();
            total = receipt.calculateTotal(taxCharge, totalDiscount);

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
                    balance = receipt.calculateBalance(amountPaid, total);
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
                for (int j = 0; j < product.size(); j++) {
                    if (orderedItem.get(i).getOrderedProd().getId().equals(product.get(j).getId())) {
                        product.get(j).decreaseStock(orderedItem.get(i).getOrderQty());
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
                System.out.println("\n\t\t\t\t      Staff: " + onDutyStaff.getUsername());
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
    }

    public static void addNewCategory() {

        char code;
        String title;

        System.out.print("\nCategory Code  > ");
        code = scan.next().charAt(0);
        System.out.print("Category Title > ");
        title = scan.next();

        int error = 0;

        if (Character.isLetter(code) == false) {
            System.err.println("\"" + code + "\"" + " Code must be in letter.");
            error++;
        }

        if (title.length() <= 2 || title.length() > 15) {
            System.err.println("\"" + title + "\"" + " Title should only contains min 2 to max 15 letters.");
            error++;
        }

        for (int i = 0; i < cat.size(); i++) {
            if (cat.get(i).getCode() == Character.toUpperCase(code)) {
                System.err.println("\"" + code + "\"" + " Code is repeated.");
                error++;
            }
            if (cat.get(i).getName().equalsIgnoreCase(title)) {
                System.err.println("\"" + title + "\"" + " Category exist.");
                error++;
            }
        }

        if (error == 0) {
            Category newCat = new Category(code, title);
            cat.add(newCat);
            System.out.println("New Category Added ! --> " + cat.get(cat.size() - 1).toString() + "\n");
        }

    }

    public static void addNewProduct() {

        int error = 0;
        int temp = 0;
        do {
            System.out.println("\n------------------------------------------------------------------------------------");
            for (int i = 0; i < cat.size(); i++) {
                System.out.print("| " + (i + 1) + ". " + cat.get(i).getName() + " |");
            }
            System.out.println("\n------------------------------------------------------------------------------------");

            System.out.print("Category > ");

            try {
                temp = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException a) {
                System.err.println("Must enter a number");
                scan.next();
            }

            if (temp < 1 || temp > cat.size()) {
                System.err.println("Invalid input.");
            }

        } while (temp < 1 || temp > cat.size());

        if (temp >= 1 && temp <= cat.size()) {
            //Input Product ID
            System.out.print("Product ID > ");
            String prodID = scan.nextLine();

            if (prodID.matches("^\\w{1}\\d{3}$") == false) {
                System.err.println("\"" + prodID + "\"" + " ID is in wrong format.");
                System.err.println("Example of Correct ID Format: Category Code + Sequence Numbers ->" + cat.get(temp - 1).getCode() + "001");
                error++;
            } else if (Character.toUpperCase(prodID.charAt(0)) != cat.get(temp - 1).getCode()) {
                System.err.println("Category Code unmatched.");
                error++;
            }

            //Input Product Name
            System.out.print("Name > ");
            String prodName = scan.nextLine();

            if (prodName.matches("^\\w{2,15}$") == false) {
                System.err.println("\"" + prodName + "\"" + " Name should only contains min 2 to max 15 letters.");
                error++;
            }

            //check duplicate id and name
            for (int i = 0; i < product.size(); i++) {

                if (product.get(i).getId().equalsIgnoreCase(prodID)) {
                    System.err.println("\"" + prodID + "\"" + " Duplicate ID.");
                    error++;
                }

                if (product.get(i).getName().equalsIgnoreCase(prodName)) {
                    System.err.println("\"" + prodName + "\"" + " Duplicate Name.");
                    error++;
                }
            }

            //Input Product Price
            System.out.print("Price > ");
            double prodPrice = -1;

            try {
                prodPrice = scan.nextDouble();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Must enter numbers");
                scan.nextLine();
                error++;
            }

            //check price
            if (prodPrice <= 0.0 || prodPrice >= 9999.99) {
                System.err.println("\"" + prodPrice + "\"" + " Price is out of range.");
                error++;
            }

            //Input Product Detail
            System.out.print("Detail > ");
            String prodDetail = scan.nextLine();

            //check detail length
            if (prodDetail.length() < 2 || prodDetail.length() > 20) {
                System.err.println("\"" + prodDetail + "\"" + " Detail length should only contains min 2 to max 20 letters.");
                error++;
            }

            System.out.print("Stock > ");
            int prodStock = 0;

            try {
                prodStock = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Must enter numbers");
                scan.next();
                error++;
            }

            //check stock
            if (prodStock < 0 || prodStock >= 9999) {
                System.err.println("\"" + prodStock + "\"" + " Stock is out of range.");
                error++;
            }

            if (error == 0) {
                Product prod = new Product(cat.get(temp - 1), prodID, prodName, prodPrice, prodDetail, prodStock);
                product.add(prod);
                System.out.println("New Product Added ! --> " + product.get(product.size() - 1).toString() + "\n");
                error++;
            } else {
                System.out.println("Please try again with correct information.");
            }
        }
    }

    public static void stockCheck() {
        int chkStock = 0;
        do {
            System.out.println("\n1. By product ID");
            System.out.println("2. By category");
            System.out.println("3. By range of stock quantity");
            System.out.println("4. Quit");
            System.out.print("Enter choice > ");

            try {
                chkStock = scan.nextInt();
            } catch (InputMismatchException a) {
                System.err.print("Must enter a number. ");
                scan.next();
            }

            switch (chkStock) {
                case 1:
                    int count = 0;
                    System.out.print("\nEnter Product ID > ");
                    String stockID = scan.next();
                    System.out.println("\nS T O C K ");
                    System.out.println("===========================================");
                    System.out.println("ID \t Name \t\t\t Stock");
                    System.out.println("===========================================");
                    for (int i = 0; i < product.size(); i++) {
                        if (product.get(i).getId().equalsIgnoreCase(stockID)) {
                            System.out.printf("%s \t %-20s \t %d \n", product.get(i).getId(), product.get(i).getName(), product.get(i).getStock());
                            count++;
                        }
                    }
                    if (count <= 0) {
                        System.err.printf("No product found!\n");
                    }
                    System.out.println("===========================================");
                    break;

                case 2:
                    System.out.println("\n------------------------------------------------------------------------------------");
                    for (int i = 0; i < cat.size(); i++) {
                        System.out.print("| " + (i + 1) + ". " + cat.get(i).getName() + " |");
                    }
                    System.out.println("\n------------------------------------------------------------------------------------");

                    System.out.print("\nEnter choice > ");
                    int catStock = 0;

                    try {
                        catStock = scan.nextInt();
                    } catch (InputMismatchException a) {
                        System.err.print("Must enter a number. ");
                        scan.next();
                    }

                    if (catStock > 0 && catStock <= cat.size()) {

                        System.out.println("\nS T O C K in " + cat.get(catStock - 1).getName());
                        System.out.println("===========================================");
                        System.out.println("ID \t Name \t\t\t Stock");
                        System.out.println("===========================================");
                        for (int i = 0; i < product.size(); i++) {
                            if (cat.get(catStock - 1) == product.get(i).getSource()) {
                                System.out.printf("%s \t %-20s \t %d \n", product.get(i).getId(), product.get(i).getName(), product.get(i).getStock());
                            }
                        }
                        System.out.println("===========================================");

                    } else {
                        System.err.println("Invalid input");
                    }
                    break;

                case 3:
                    System.out.print("Enter minimum stock quantity > ");
                    int minStock = 0;

                    try {
                        minStock = scan.nextInt();
                        scan.nextLine();
                    } catch (InputMismatchException a) {
                        System.err.println("Must enter a number.");
                        scan.next();
                    }

                    System.out.print("Enter maximum stock quantity > ");
                    int maxStock = 0;

                    try {
                        maxStock = scan.nextInt();
                        scan.nextLine();
                    } catch (InputMismatchException a) {
                        System.err.println("Must enter a number ");
                        scan.next();
                    }

                    System.out.println("\nS T O C K quantity between " + minStock + " and " + maxStock);
                    System.out.println("===========================================");
                    System.out.println("ID \t Name \t\t\t Stock");
                    System.out.println("===========================================");
                    for (int i = 0; i < product.size(); i++) {
                        if (product.get(i).getStock() >= minStock && product.get(i).getStock() <= maxStock) {
                            System.out.printf("%s \t %-20s \t %d \n", product.get(i).getId(), product.get(i).getName(), product.get(i).getStock());
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
    }

    public static void stockControl(int stockManage) {

        int find = 0;
        int found = 0;
        while (find == 0 && found == 0) {
            System.out.print("\nEnter Product ID > ");
            String stock = scan.next();

            for (int i = 0; i < product.size(); i++) {
                if (product.get(i).getId().equalsIgnoreCase(stock)) {
                    System.out.print("Product found   : ");
                    System.out.printf("%s \t %-15s Stock: %d \n", product.get(i).getId(), product.get(i).getName(), product.get(i).getStock());
                    found++;

                    System.out.print("Enter amount > ");
                    int stockInOut = 0;

                    try {
                        stockInOut = scan.nextInt();
                        scan.nextLine();
                    } catch (InputMismatchException a) {
                        System.err.println("Must enter a number ");
                        scan.next();
                    }

                    if (stockInOut > 0) {
                        if (stockManage == 2) {
                            product.get(i).increaseStock(stockInOut);
                            System.out.println("Stock increased !");
                        } else {
                            product.get(i).decreaseStock(stockInOut);
                            System.out.println("Stock decreased !");
                        }
                    }
                }
            }

            if (found == 0) {
                System.err.println("Product ID not found...");
                System.out.print("Continue finding ? [1 = YES]: ");
                char yes = scan.next().charAt(0);
                scan.nextLine();

                if (yes != '1' && yes != 'y' && yes != 'Y') {
                    found++;
                }
            }
        }
    }

    public static void heydaySummary() {

        double soldTotalAmount;
        double grandFinalTotalAmount;
        grandFinalTotalAmount = summary.calculateFinalTotalAmount(summary.getNewSubtotal(), summary.getNewsdDiscount(), summary.getNewvcDiscount());

        System.out.println("\t\t\t\t   DAILY SUMMARY");
        System.out.println("\t\t\t\t   -------------\n");
        System.out.printf("Total Order : %-15d \t\t\t\tDate: %15s", Receipt.getOrderID(), summary.getDate());
        System.out.println("\n============================================================================");
        System.out.print("Product ID \t\t Products \t\t Sold \t\t\t Amount");
        System.out.println("\n============================================================================");
        for (int i = 0; i < Receipt.getTotalOrder().size(); i++) {
            soldTotalAmount = 0;
            int soldTotalQty = 0;
            soldTotalAmount += summary.calculateSoldAmount(Receipt.getTotalOrder().get(i).getOrderQty(), Receipt.getTotalOrder().get(i).getOrderedProd().getPrice());
            soldTotalQty += Receipt.getTotalOrder().get(i).getOrderQty();
            System.out.printf("%-25s%-22s%-15d%13.2f\n", Receipt.getTotalOrder().get(i).getOrderedProd().getId(), Receipt.getTotalOrder().get(i).getOrderedProd().getName(), soldTotalQty, soldTotalAmount);
        }
        System.out.println("============================================================================");
        System.out.printf("Total Amount %62.2f\n", summary.getNewSubtotal());
        System.out.printf("Total Discount %60.2f\n", summary.getNewsdDiscount());
        System.out.printf("Total Voucher Amount %54.2f\n", summary.getNewvcDiscount());
        System.out.printf("Final Total Amount %56.2f\n", grandFinalTotalAmount);
    }

    public static void main(String[] args) {

        //Login Module
        User onDutyStaff = login();

        int selection = 7;
        do {
            selection = mainMenu(onDutyStaff);

            switch (selection) {

                case 0://Log out
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

                case 1://Display products
                    displayProducts();
                    break;

                case 2:
                    System.out.println("\n===========================");
                    System.out.println("=    O R D E R Product    =");
                    System.out.println("===========================");

                    double prodAmount = orderProducts();
                    receipt(prodAmount, onDutyStaff);
                    break;

                case 3://Add new Category or new Product
                    System.out.println("\n===============================");
                    System.out.println("=   A D D  CATEGORY//PRODUCT  =");
                    System.out.println("===============================");
                    int addnew = 0;

                    do {
                        System.out.println("\n1. New Category");
                        System.out.println("2. New Product");
                        System.out.println("3. Quit");
                        System.out.print("Enter choice > ");

                        try {
                            addnew = scan.nextInt();
                            scan.nextLine();
                        } catch (InputMismatchException a) {
                            System.err.print("Must enter a number. ");
                            scan.next();
                        }

                        switch (addnew) {
                            case 1://Add new Category
                                addNewCategory();
                                break;
                            case 2://Add new Product
                                addNewProduct();
                                break;
                            case 3:
                                System.out.println("Quit...");
                                break;
                            default:
                                System.err.println("Invalid Input...");
                        }
                    } while (addnew >= 1 && addnew <= 2);
                    break;

                case 4://Stock Management
                    System.out.println("\n===========================");
                    System.out.println("=   S T O C K  Management =");
                    System.out.println("===========================");
                    int stockManage = 0;
                    do {
                        System.out.println("\n1. Stock Check");
                        System.out.println("2. Increase Stock");
                        System.out.println("3. Decrease Stock");
                        System.out.println("4. Quit");
                        System.out.print("Enter choice > ");

                        try {
                            stockManage = scan.nextInt();
                        } catch (InputMismatchException a) {
                            System.err.print("Must enter a number. ");
                            scan.next();
                        }

                        switch (stockManage) {
                            case 1: //Stock check
                                stockCheck();
                                break;
                            case 2:
                            case 3: //Stock control
                                stockControl(stockManage);
                                break;
                            case 4:
                                System.out.println("Quit..");
                                break;
                            default:
                                System.err.println("Invalid input...");
                        }
                    } while (stockManage >= 1 && stockManage <= 3);
                    break;

                case 5: //Summary module
                    heydaySummary();
                    break;

                case 6:
                    System.out.println("\n===========================");
                    System.out.println("=   S T A F F List =");
                    System.out.println("===========================");

                    for (int i = 0; i < staff.size(); i++) {
                        if (staff.get(i) instanceof Manager) {
                            System.out.println("\nManager");
                        } else if (staff.get(i) instanceof Cashier) {
                            System.out.println("\nCashier");
                        }
                        System.out.println((i + 1) + "\n" + staff.get(i).toString());
                    }
                    break;
                default:
            }

        } while (selection >= 0 && selection <= 6);

    }

}
