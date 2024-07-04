package projectmd2.business.feature.productsimpl.User;

import projectmd2.business.design.ICategory;
import projectmd2.business.design.IProducts;
import projectmd2.business.entity.Category;
import projectmd2.business.entity.Products;
import projectmd2.business.feature.categoryimpl.CategoryImpl;
import projectmd2.business.feature.productsimpl.Admin.ProductsImpl;
import projectmd2.business.untils.InputMethods;
import projectmd2.business.untils.ShopMessage;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductFeatureForUser {
    private static IProducts productsList = new ProductsImpl();
    private static ICategory categoryList = new CategoryImpl();
    public static void displayAllProductsUser(){
        System.out.println("****************LIST PRODUCTS *****************");
        productsList.findAll().stream().filter(p->p.getCategory().isStatus()).forEach(Products::displayDataForUser);
        System.out.println( "Has" + productsList.findAll().stream().filter(p->p.getCategory().isStatus()).count() +" products");
    }

    public static void showNewProductMenuForUser() {
        System.out.println("**************** PRODUCTS NEWEST ****************");
        productsList.findAll().stream().filter(p->p.getCategory().isStatus())
                .sorted(Comparator.comparing(Products::getCreatedAt).reversed())
                .forEach(Products::displayDataForUser);
        System.out.println("*************************************************");
    }

    public static void showProductsByCategories(Scanner sc) {
        System.out.println("**********LIST CATEGORIES**********");
        System.out.printf("%3s | %15s  \n","ID","Name");
        categoryList.findAll().stream().filter(Category::isStatus).forEach(Category::displayDataforUser);
        System.out.println("***********************************");
        System.out.println("Enter category ID you want to show products ");
        int choice = InputMethods.getInteger();
        Category category = categoryList.findById(choice);
        if(category != null && category.isStatus()){
            System.out.printf("%3s | %20s | %15s | %5s | %15s   \n","ID","Name","Description","Price","Category");
            productsList.findAll().stream().filter(p->p.getCategory().isStatus()
                            && p.getCategory().getCategoryId() == choice)
                    .forEach(Products::displayDataForUser);
        }else{
            System.err.println("Category not found");
        }
    }

    public static void showProductDetailById() {
        System.out.println("Enter product ID you want to show");
        int productId = InputMethods.getInteger();
        if(productsList.findById(productId) != null && productsList.findById(productId).getCategory().isStatus()){
            System.out.printf("%3s | %20s | %15s | %5s | %15s \n","ID","Name","Description","Price","Category");
            productsList.findById(productId).displayDataForUser();
        }else {
            System.err.println("Product not found");
        }
    }

    public static void searchProductByNameOrDescriptions() {
        System.out.println("Enter product name or description you want to search");
        String productName = InputMethods.getString();
        productsList.findAll().stream().filter(p->p.getCategory().isStatus()
                && (p.getProductName().contains(productName) || p.getDescription().contains(productName)))
                .forEach(Products::displayDataForUser);
    }
}
