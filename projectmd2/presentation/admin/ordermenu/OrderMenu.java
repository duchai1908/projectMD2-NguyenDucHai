package projectmd2.presentation.admin.ordermenu;

import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

public class OrderMenu {
    public static void showOrderMenu() {
        System.out.println(Colors.CYAN + "******************** ORDER MENU **********************" + Colors.RESET);
        System.out.println("1. Display All Order");
        System.out.println("2. Order Detail");
        System.out.println("3. Display Order By Status");
        System.out.println("4. Change Status Order By Id");
        System.out.println("5. Back to Main Menu");
        System.out.println(Colors.CYAN + "******************************************************" + Colors.RESET);
        System.out.println("Enter your choice:");
        byte choice = InputMethods.getByte();
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default:
                System.err.println(ShopMessage.ERROR_ALERT);
        }
    }
}
