package projectmd2.business.untils.Validation;

import projectmd2.business.design.ICategory;
import projectmd2.business.entity.Category;
import projectmd2.business.feature.categoryimpl.CategoryImpl;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Scanner;

public class CategoryValidate {
    private static ICategory iCategory = new CategoryImpl();
    public static String inputCategoryName(Scanner sc) {
        while (true){
            System.out.println("Enter Category Name: ");
            String categoryName = InputMethods.getString();
            if(iCategory.findAll().stream().noneMatch(e-> (e.getCategoryName().equals(categoryName)))){
                return categoryName;
            }else{
                System.err.println(ShopMessage.ERROR_CATEGORY_EXIST);
            }
        }
    }

    public static String inputCategoryDescriptions() {
        System.out.println("Enter Category Descriptions: ");
        return InputMethods.getString();
    }

    public static boolean inputCategoryStatus() {
        System.out.println("Enter Category Status(true | false): ");
        return InputMethods.getBoolean();
    }
}
