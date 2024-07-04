package projectmd2.presentation.user.productforuser;

import projectmd2.business.feature.productsimpl.Admin.ProductsFeature;
import projectmd2.business.feature.productsimpl.User.ProductFeatureForUser;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Scanner;

public class ProductMenuForUser {
    public static void showProductMenuForUser(Scanner sc) {
        while (true){
            System.out.println("****************PRODUCTS MENU***************");
            System.out.println("1. Display All Products");
            System.out.println("2. Products New");
            System.out.println("3. Products By Categories");
            System.out.println("4. Show Product Detail By Id");
            System.out.println("5. Search Product By Name Or Description");
            System.out.println("6. Back");
            System.out.println("********************************************");
            System.out.println("Enter your choice: ");
            byte choiceProducts = InputMethods.getByte();
            switch (choiceProducts) {
                case 1:
                    ProductFeatureForUser.displayAllProductsUser();
                    break;
                case 2:
                    ProductFeatureForUser.showNewProductMenuForUser();
                    break;
                case 3:
                    ProductFeatureForUser.showProductsByCategories(sc);
                    break;
                case 4:
                    ProductFeatureForUser.showProductDetailById();
                    break;
                case 5:
                    ProductFeatureForUser.searchProductByNameOrDescriptions();
                    break;
                case 6:
                    return;
                default:
                    System.err.println(ShopMessage.ERROR_ALERT);
            }
        }
    }
}
