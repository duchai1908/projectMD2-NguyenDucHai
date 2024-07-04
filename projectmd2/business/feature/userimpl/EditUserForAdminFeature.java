package projectmd2.business.feature.userimpl;

import projectmd2.business.entity.RoleName;
import projectmd2.business.entity.User;
import projectmd2.business.untils.Validation.UserValidate;

import java.util.Map;
import java.util.Scanner;

public class EditUserForAdminFeature {
    public static void changeFullName(User user) {
        user.setFullName(UserValidate.inputFullName());
    }

    public static void changePassword(Scanner sc,User user) {
        user.setPassword(UserValidate.inputPassword(sc));
    }

    public static void changeEmail(Scanner sc, User user) {
        user.setEmail(UserValidate.inputEmail(sc));
    }


    public static void changePhone(Scanner sc, User user) {
        user.setPhone(UserValidate.inputPhone(sc));
    }

    public static void changeAddress( User user) {
        user.setAddress(UserValidate.inputAddress());
    }

    public static void changeSecurityQuestion(Scanner sc, User user) {
        user.setSecurityQuestion((Map<String, String>)UserValidate.inputSecurityQuestion(sc));
    }

    public static void changeRole(User user) {
        if(user.getRoleName() == RoleName.USER){
            user.setRoleName(RoleName.ADMIN);
        }else if(user.getRoleName() == RoleName.MOD){
            user.setRoleName(RoleName.USER);
        }
    }

    public static void changeStatus(User user) {
        user.setStatus(!user.isStatus());
    }
}
