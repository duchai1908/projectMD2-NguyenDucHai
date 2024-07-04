package projectmd2.business.feature.categoryimpl;

import projectmd2.business.design.ICategory;
import projectmd2.business.design.IProducts;
import projectmd2.business.entity.Category;
import projectmd2.business.entity.Products;
import projectmd2.business.feature.productsimpl.Admin.ProductsImpl;
import projectmd2.business.untils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryFeature {
    private static ICategory categoryList = new CategoryImpl();
    public static IProducts productList = new ProductsImpl();

    public static void displayAllCategory() {
        if(categoryList.findAll().isEmpty()){
            System.err.println("No Category Found");
            return;
        }
        System.out.println("-----------------------------------------------------");
        System.out.printf("%3s | %10s | %15s | %5s | %5s \n","ID","Name","Description","Status","Total Product");
        categoryList.findAll().forEach(Category::displayData);
        System.out.println("-----------------------------------------------------");
        System.out.println("Has " + categoryList.findAll().size() + " categories");
    }

    public static void addNewCategory(Scanner sc) {
        System.out.println("Enter the number of the category you want to create: ");
        int cateNumber = InputMethods.getInteger();
        for (int i = 0; i < cateNumber; i++) {
            System.out.println(Colors.GREEN + "Add category " + (i + 1) + Colors.RESET);
            Category category = new Category();
            category.inputData(sc, true);
            categoryList.save(category);
        }
        System.out.println("Add " + cateNumber + " category successfully");
    }

    public static void editCategoryById(Scanner sc) {
        if(categoryList.findAll().isEmpty()){
            System.err.println("No Category Found");
            return;
        }
        System.out.println("Enter the ID of the category you want to edit: ");
        int cateID = InputMethods.getInteger();
        Category category = categoryList.findById(cateID);
        if (category != null) {
            System.out.println(Colors.CYAN + " Old information of catrgory with ID: "+ cateID +Colors.RESET);
            category.displayData();
            System.out.println(Colors.CYAN + " New category information: "+Colors.RESET);
            category.inputData(sc,false);
            categoryList.save(category);
            System.out.println("Edit category with ID: " + cateID + " successfully");
        }else {
            System.err.println("Cant find category with ID: " + cateID);
        }
    }

    public static void deleteCategoryById(Scanner sc) {
        if(categoryList.findAll().isEmpty()){
            System.err.println("No Category Found");
            return;
        }
        System.out.println("Enter the ID of the category you want to delete: ");
        int cateID = InputMethods.getInteger();
        Category category = categoryList.findById(cateID);
        if (category != null) {
            categoryList.deleteById(cateID);
            List<Products> productsToRemove = new ArrayList<>();
            for (Products products : productList.findAll()) {
                if (products.getCategory().getCategoryId() == category.getCategoryId()) {
                    productsToRemove.add(products);
                }
            }
            for (Products products : productsToRemove) {
                productList.deleteById(products.getProductId());
            }
            System.out.println(Colors.GREEN +"Delete category with ID: "+ cateID + "successfully" +Colors.RESET);
        }else {
            System.err.println("Cant find category with ID: " + cateID);
        }
    }

    public static void searchCategoryById(Scanner sc) {
        if(categoryList.findAll().isEmpty()){
            System.err.println("No Category Found");
            return;
        }
        System.out.println("Enter the ID of the category you want to search: ");
        int cateID = InputMethods.getInteger();
        Category category = categoryList.findById(cateID);
        if (category != null) {
            System.out.println("-----------------------------------------------------");
            System.out.printf("%3s | %10s | %15s | %5s | %5s \n","ID","Name","Description","Status","Total Product");
            category.displayData();
            System.out.println("-----------------------------------------------------");
        }else {
            System.err.println("Cant find category with ID: " + cateID);
        }
    }
}
