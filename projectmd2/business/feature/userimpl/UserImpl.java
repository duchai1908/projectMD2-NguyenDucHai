package projectmd2.business.feature.userimpl;

import projectmd2.business.design.IGenericDesign;
import projectmd2.business.design.IUser;

import projectmd2.business.entity.User;
import projectmd2.business.untils.IOFile;

import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopConstant;
import projectmd2.presentation.admin.DashBoardView;
import projectmd2.presentation.admin.HomePageView;

import java.util.List;
import java.util.Scanner;

public class UserImpl implements IUser, IGenericDesign<User,Integer> {
    private static List<User> userList;

    public UserImpl() {
        userList = IOFile.readFromFile(ShopConstant.USER_PATH);
    }

    @Override
    public void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("******************LOGIN******************");
        System.out.println("Enter your username: ");
        String username = InputMethods.getString();
        System.out.println("Enter your password: ");
        String password = InputMethods.getString();
        User userLogin = userList.stream().filter(u->u.getUserName().equals(username) && u.getPassword().equals(password)).findFirst().orElse(null);
        if(userLogin != null) {
            switch (userLogin.getRoleName()){
                case ADMIN:
                case MOD:
                    System.out.println("Login successful");
                    DashBoardView.showDashBoardView(sc);
                    break;
                    case USER:
                        HomePageView.showHomePageViewMenu();
            }
        }
    }

    @Override
    public void register() {
        System.out.println("******************REGISTER******************");
        Scanner sc = new Scanner(System.in);
        User user = new User();
        user.inputData(sc,true);
        userList.add(user);
        System.out.println("Registered Successfully");
        IOFile.writeToFile(ShopConstant.USER_PATH,userList);
        login();
    }

    @Override
    public boolean existByUsername(String username) {
        return userList.stream().anyMatch(t -> t.getUserName().equals(username));
    }


    @Override
    public List<User> findAll() {
        return userList;
    }

    @Override
    public User findById(Integer id) {
        return userList.stream().filter(u->u.getId()==id).findFirst().orElse(null);
    }

    @Override
    public void save(User user) {
        if(findById(user.getId())==null) {
            userList.add(user);
        }else {
            userList.set(userList.indexOf(findById(user.getId())), user);
        }
        IOFile.writeToFile(ShopConstant.USER_PATH, userList);
    }

    @Override
    public void deleteById(Integer id) {
        userList.removeIf(u->u.getId()==id);
        IOFile.writeToFile(ShopConstant.USER_PATH, userList);
    }

    @Override
    public void forgotPassword() {
        System.out.println("******************VERIFY ACCOUNT******************");
        System.out.println("Enter UserName");
        String username = InputMethods.getString();
        if(userList.stream().anyMatch(u->u.getUserName().equals(username))) {
            
        }else{
            System.err.println("User not found");
        }
    }
}
