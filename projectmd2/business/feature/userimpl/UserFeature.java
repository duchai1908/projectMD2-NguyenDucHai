package projectmd2.business.feature.userimpl;

import projectmd2.business.entity.RoleName;
import projectmd2.business.entity.User;
import projectmd2.business.untils.InputMethods;
import projectmd2.presentation.admin.usermenu.UserMenuEditForAdmin;

import java.util.Scanner;

public class UserFeature {
    private static UserImpl userList = new UserImpl();
    public static void displayUser() {
        System.out.printf("%3s | %20s | %20s | %5s | %10s | %10s | %5s | %5s \n",
                "ID","User Name","Full Name","Role","Created","Updated","Status","Deleted");
        userList.findAll().forEach(User::displayData);
    }

    public static void editUser(Scanner sc) {
        System.out.print("Enter ID user you want to edit: ");
        int userId = InputMethods.getInteger();
        User user = userList.findById(userId);
        if(user!= null) {
            UserMenuEditForAdmin.showEditUserForUser(sc,user);
        }else{
            System.err.println("User not found");
        }
    }

    public static void deleteUser(Scanner sc) {
        System.out.println("Enter ID user you want to delete: ");
        int userId = InputMethods.getInteger();
        if(userList.findById(userId) != null) {
            userList.findById(userId).setDeleted(true);
            userList.save(userList.findById(userId));
        }else{
            System.err.println("User not found");
        }
    }

    public static void findByName(Scanner sc) {
        System.out.println("Enter user name you want to find");
        String userName = InputMethods.getString();
        User user = userList.findAll().stream().filter(u->u.getUserName().equals(userName)).findFirst().orElse(null);
        if(user != null) {
            user.displayData();
        }else{
            System.err.println("User not found");
        }
    }

    public static void displayAllRole() {
        RoleName[] roles = RoleName.values();
        int count = 1;
        System.out.println("***************LIST ROLES***************");
        for(RoleName role : roles) {
            System.out.println(count +". " +role);
            count++;
        }
        System.out.println("Has " + (count-1) + " roles");
    }
}
