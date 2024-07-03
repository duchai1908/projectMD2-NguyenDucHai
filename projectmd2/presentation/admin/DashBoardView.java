package projectmd2.presentation.admin;

import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;
import projectmd2.presentation.admin.categorymenu.CategoryMenu;
import projectmd2.presentation.admin.productsmenu.ProductsMenu;

import java.util.Scanner;

public class DashBoardView {
    public static void showDashBoardView(Scanner sc) {
        while (true) {
            System.out.println("********************DASHBOARD**********************");
            System.out.println("1. Category Management System");
            System.out.println("2. Products Management System");
            System.out.println("3. User Management System Administrator");
            System.out.println("4. Log Out");
            System.out.println("***************************************************");
            System.out.println("Enter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    CategoryMenu.showCategoryMenu(sc);
                    break;
                case 2:
                    ProductsMenu.showProductsMenu(sc);
                    break;
                case 3:
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }

    }
}
