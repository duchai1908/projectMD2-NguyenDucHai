package projectmd2.business.feature.orderimpl;

import projectmd2.business.design.IOrder;
import projectmd2.business.entity.Order;
import projectmd2.business.entity.Status;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.presentation.run.Main;

public class OrderFeature {
    private static IOrder orderList = new OrderImpl();

    //Display All Order From UserLogin
    public static void showAllOrder() {
        if(orderList.findAll().stream().noneMatch(o->o.getUserId() == Main.userLogin.getId())){
            System.err.println("Order list is empty");
            return;
        }
        System.out.println(Colors.CYAN+"*************** Order History ***************"+Colors.RESET);
        System.out.printf(Colors.GREEN+"%3s | %15s | %15s | %15s | %10s | %10s | %10s \n"
                ,"ID","ADDRESS","RECEIVE PHONE","PRICE","ORDER AT", "RECEIVE AT","STATUS"+Colors.RESET);
        orderList.findAll().stream().filter(o->o.getUserId() == Main.userLogin.getId()).forEach(Order::displayData);
    }

    //Display Order Detail By Order Id And UserLogin
    public static void detailOrder() {
        if(orderList.findAll().isEmpty()){
            System.err.println("Order list is empty");
            return;
        }
        System.out.println(Colors.CYAN+"Enter Order ID you want to show the details of the order"+Colors.RESET);
        int orderId = InputMethods.getInteger();
        if(orderList.findById(orderId) != null && orderList.findById(orderId).getUserId() == Main.userLogin.getId()) {
            System.out.println(orderList.findById(orderId));
        }else{
            System.err.println("Order not found");
        }
    }

    //Display All Order By Status
    public static void displayOrderByStatus() {
        if(orderList.findAll().isEmpty()){
            System.err.println("Order list is empty");
            return;
        }
        System.out.println(Colors.CYAN+"**********LIST STATUS ***********"+Colors.RESET);
        Status[] statuses = Status.values();
        int count =1;
        for(Status status : statuses) {
            System.out.println(count+". "+status);
            count++;
        }
        System.out.println(Colors.CYAN+"Enter status you want to show"+Colors.RESET);
        String status = InputMethods.getString();
        Status inputStatus = Status.valueOf(status);
        if(orderList.findAll().stream().anyMatch(o -> o.getStatus().equals(inputStatus) && o.getUserId() == Main.userLogin.getId())){
            orderList.findAll().stream().filter(o->o.getStatus().equals(inputStatus) && o.getUserId() == Main.userLogin.getId()).forEach(Order::displayData);
        }else{
            System.err.println("Order not found");
        }
    }
}
