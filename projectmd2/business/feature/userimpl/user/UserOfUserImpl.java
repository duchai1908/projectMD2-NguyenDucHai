package projectmd2.business.feature.userimpl.user;

import projectmd2.business.design.IUser;
import projectmd2.business.feature.userimpl.UserImpl;
import projectmd2.presentation.run.Main;

import java.util.Scanner;

public class UserOfUserImpl {
    private static UserImpl userList = new UserImpl();
    public static void displayUser() {
        System.out.println("*********** INFORMATION OF USER: "+ Main.userLogin.getUserName() +" **********");
        Main.userLogin.displayDataforUser();
    }

    public static void editUser() {
        Scanner sc = new Scanner(System.in);
        Main.userLogin.inputData(sc,false);
        userList.save(Main.userLogin);
        System.out.println("Edit successfully");
    }
}
