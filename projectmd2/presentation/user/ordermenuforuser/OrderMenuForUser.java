package projectmd2.presentation.user.ordermenuforuser;

import projectmd2.business.feature.orderimpl.OrderFeature;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

public class OrderMenuForUser {
    public static void showOrderMenuForUser() {
        System.out.println(Colors.CYAN + "*********************** Order Menu ***********************" + Colors.RESET);
        System.out.println("1. Order history");
        System.out.println("2. Order detail");
        System.out.println("3. Display Order By Status");
        System.out.println("4. Exit");
        System.out.println(Colors.CYAN + "**********************************************************" + Colors.RESET);
        System.out.println("Enter your choice:");
        byte choice = InputMethods.getByte();
        switch (choice) {
            case 1:
                OrderFeature.showAllOrder();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.err.println(ShopMessage.ERROR_ALERT);
        }
    }
}
