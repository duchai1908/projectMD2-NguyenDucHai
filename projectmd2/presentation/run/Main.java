package projectmd2.presentation.run;

import projectmd2.business.design.IUser;
import projectmd2.business.feature.userimpl.UserImpl;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;
import projectmd2.presentation.admin.DashBoardView;
import projectmd2.presentation.admin.categorymenu.CategoryMenu;
import projectmd2.presentation.admin.securityquestion.SecurityQuestionMenu;

import java.util.Scanner;

public class Main {
    public static IUser userLits = new UserImpl();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("***************SHOP***************");
            System.out.println("1. Visit Shop");
            System.out.println("2. Login");
            System.out.println("3. Register");
            System.out.println("4. Forgot Password");
            System.out.println("5. Exit");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    SecurityQuestionMenu.showSecurityQuesttionMenu(sc);
                    break;
                case 2:
                    userLits.login();
                    break;
                case 3:
                    userLits.register();
                    break;
                case 4:

                case 5:
                    System.exit(0);
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
