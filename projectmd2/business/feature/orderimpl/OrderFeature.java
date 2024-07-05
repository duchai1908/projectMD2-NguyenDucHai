package projectmd2.business.feature.orderimpl;

import projectmd2.business.design.IOrder;
import projectmd2.business.entity.Order;
import projectmd2.business.untils.Colors;
import projectmd2.presentation.run.Main;

public class OrderFeature {
    private static IOrder orderList = new OrderImpl();
    public static void showAllOrder() {
        System.out.println(Colors.CYAN+"*************** Order History ***************"+Colors.RESET);
        orderList.findAll().stream().filter(o->o.getUserId() == Main.userLogin.getId()).forEach(Order::displayData);
    }
}
