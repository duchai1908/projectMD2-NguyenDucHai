package projectmd2.business.feature.userimpl;

import projectmd2.business.design.IGenericDesign;
import projectmd2.business.design.ISecurityQuestion;
import projectmd2.business.design.IUser;

import projectmd2.business.entity.Address;
import projectmd2.business.entity.SecurityQuestion;
import projectmd2.business.entity.User;
import projectmd2.business.feature.designImpl.SecurityQuestionImpl;
import projectmd2.business.untils.IOFile;

import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopConstant;
import projectmd2.presentation.admin.DashBoardView;
import projectmd2.presentation.run.Main;
import projectmd2.presentation.user.HomePageView;

import java.util.List;
import java.util.Scanner;

public class UserImpl implements IUser, IGenericDesign<User, Integer> {
    private static List<User> userList;
    private static ISecurityQuestion securityQuestion = new SecurityQuestionImpl();

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
        User userLogin = userList.stream().filter(u -> u.getUserName().equals(username)
                && u.getPassword().equals(password) && !u.isDeleted()
                && u.isStatus()).findFirst().orElse(null);
        if (userLogin != null) {
            switch (userLogin.getRoleName()) {
                case ADMIN:
                case MOD:
                    System.out.println("Login successful");
                    DashBoardView.showDashBoardView(sc);
                    break;
                case USER:
                    Main.userLogin = userLogin;
                    HomePageView.showHomePageViewMenu();
                    break;
                default:
            }
        }
    }

    @Override
    public void register() {
        System.out.println("******************REGISTER******************");
        Scanner sc = new Scanner(System.in);
        User user = new User();
        user.inputData(sc, true);
        userList.add(user);
        System.out.println("Registered Successfully");
        IOFile.writeToFile(ShopConstant.USER_PATH, userList);
        List<Address> addressList = IOFile.readFromFile(ShopConstant.ADDRESS_PATH);
        Address address = new Address();
        address.setAddressId(getNewAddressId(addressList));
        address.setUserId(user.getId());
        address.setFullAddress(user.getAddress());
        address.setPhone(user.getPhone());
        address.setReceiveName(user.getFullName());
        addressList.add(address);
        IOFile.writeToFile(ShopConstant.ADDRESS_PATH, addressList);
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
        return userList.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(User user) {
        if (findById(user.getId()) == null) {
            userList.add(user);
        } else {
            userList.set(userList.indexOf(findById(user.getId())), user);
        }
        IOFile.writeToFile(ShopConstant.USER_PATH, userList);
    }

    @Override
    public void deleteById(Integer id) {
        userList.removeIf(u -> u.getId() == id);
        IOFile.writeToFile(ShopConstant.USER_PATH, userList);
    }

    @Override
    public void forgotPassword() {
        System.out.println("******************VERIFY ACCOUNT******************");
        System.out.println("Enter UserName");
        String username = InputMethods.getString();
        User user = userList.stream().filter(u -> u.getUserName().equals(username)).findFirst().orElse(null);
        if (user != null) {
            System.out.println("Security Question");
            securityQuestion.findAll().forEach(SecurityQuestion::displayData);
            System.out.println("Enter Id Security Question");
            int choice = InputMethods.getInteger();
            System.out.println("Enter your answer");
            String answer = InputMethods.getString();
            SecurityQuestion secu = securityQuestion.findById(choice);
            if (secu.getQuestion().equals(user.getSecurityQuestion().keySet().stream().findFirst().get()) &&
                    user.getSecurityQuestion().values().stream().findFirst().get().equals(answer)) {
                System.out.println("Enter new password");
                String newPassword = InputMethods.getString();
                user.setPassword(newPassword);
                userList.set(userList.indexOf(findById(user.getId())), user);
                IOFile.writeToFile(ShopConstant.USER_PATH, userList);
            } else {
                System.err.println("Security Question and answer does not match");
            }
        } else {
            System.err.println("User not found");
        }
    }
    public int getNewAddressId(List<Address> list){
        int idMax =0;
        for(Address address : list){
            if(address.getAddressId() > idMax){
                idMax = address.getAddressId();
            }
        }
        return idMax+1;
    }
}
