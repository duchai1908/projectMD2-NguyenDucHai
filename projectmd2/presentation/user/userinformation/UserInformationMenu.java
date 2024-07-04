package projectmd2.presentation.user.userinformation;

import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;
import projectmd2.presentation.user.addressforuser.AddressForUserMenu;

public class UserInformationMenu {
    public static void showUserInformation() {
        while (true){
            System.out.println("****************USER INFORMATION****************");
            System.out.println("1. View User Information");
            System.out.println("2. Edit User Information");
            System.out.println("3. Address User Information");
            System.out.println("4. Change Password");
            System.out.println("5. Back");
            System.out.println("************************************************");
            System.out.println("Enter your choice: ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    AddressForUserMenu.addressForUserMenu();
                    break;
                case 4:
                    break;
                case 5:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
