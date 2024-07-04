package projectmd2.business.feature.productsimpl.Admin;

import projectmd2.business.design.IProducts;
import projectmd2.business.entity.Products;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.InputMethods;
import projectmd2.presentation.admin.productsmenu.EditProductMenu;

import java.util.Scanner;

public class ProductsFeature {
    private static IProducts productsList = new ProductsImpl();

    public static void displayAllProducts() {
        if(productsList.findAll().isEmpty()){
            System.err.println("Products list is empty");
            return;
        }
        System.out.println("-----------------------------------------------------");
        System.out.printf("%3s | %20s | %15s | %5s | %15s | %15s | %15s \n"
                , "ID", "Product Name", "Description", "Unit Price", "Category", "Created At", "Updated At");
        productsList.findAll().forEach(Products::displayData);
        System.out.println("-----------------------------------------------------");
        System.out.println("Has " + productsList.findAll().size() + " products");
    }

    public static void addNewProducts(Scanner sc) {
        System.out.println("Enter the number of the products you want to create: ");
        int productsNumber = InputMethods.getInteger();
        for (int i = 0; i < productsNumber; i++) {
            System.out.println(Colors.GREEN + "Add product " + (i + 1) + Colors.RESET);
            Products newProduct = new Products();
            newProduct.inputData(sc, true);
            productsList.save(newProduct);
        }
        System.out.println("Add " + productsNumber + " products successfully");

    }

    public static void editProductsById(Scanner sc) {
        if(productsList.findAll().isEmpty()){
            System.err.println("Products list is empty");
            return;
        }
        System.out.println("Enter product ID you want to edit: ");
        int productID = InputMethods.getInteger();
        Products newProduct = productsList.findById(productID);
        if (newProduct != null) {
            System.out.println("Old prodduct with ID " + productID + " information");
            System.out.println("-----------------------------------------------------");
            System.out.printf("%3s | %20s | %15s | %5s | %15s | %15s | %15s \n"
                    , "ID", "Product Name", "Description", "Unit Price", "Category", "Created At", "Updated At");
            newProduct.displayData();
            System.out.println("-----------------------------------------------------");
            EditProductMenu.showEditProductMenu(newProduct);
        } else {
            System.err.println("Cant find product with ID: " + productID);
        }
    }

    public static void deleteProductsById(Scanner sc) {
        if(productsList.findAll().isEmpty()){
            System.err.println("Products list is empty");
            return;
        }
        System.out.println("Enter product ID you want to delete: ");
        int productID = InputMethods.getInteger();
        Products newProduct = productsList.findById(productID);
        if (newProduct != null) {
            productsList.deleteById(productID);
            System.out.println("Deleted product with ID: " + productID + " successfully");
        }else{
            System.out.println("Cant find product with ID: " + productID);
        }
    }

    public static void searchProductsById(Scanner sc) {
        if(productsList.findAll().isEmpty()){
            System.err.println("Products list is empty");
            return;
        }
        System.out.println("Enter product ID you want to search: ");
        int productID = InputMethods.getInteger();
        if (productsList.findById(productID) != null) {
            productsList.findById(productID).displayData();
        }else{
            System.out.println("Cant find product with ID: " + productID);
        }
    }
}
