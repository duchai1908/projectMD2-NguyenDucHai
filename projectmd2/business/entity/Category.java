package projectmd2.business.entity;

import projectmd2.business.design.IOData;
import projectmd2.business.design.IProducts;
import projectmd2.business.feature.productsimpl.ProductsImpl;
import projectmd2.business.untils.Colors;
import projectmd2.business.untils.IOFile;
import projectmd2.business.untils.ShopConstant;
import projectmd2.business.untils.Validation.CategoryValidate;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Category implements IOData<Category,String>, Serializable {
    private static IProducts productsList = new ProductsImpl();
    private int categoryId;
    private String categoryName,descriptions;
    private boolean status;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String descriptions, boolean status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.descriptions = descriptions;
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc, boolean isAdd) {
        List<Category> categories = null;
        if(isAdd){
            this.categoryId = getNewId(categories, ShopConstant.CATEGORY_PATH);
        }
        this.categoryName = CategoryValidate.inputCategoryName(sc);
        this.descriptions = CategoryValidate.inputCategoryDescriptions();
        this.status = CategoryValidate.inputCategoryStatus();
    }

    @Override
    public void displayData() {
        System.out.printf("%3s | %10s | %15s | %5s | %5s \n",this.categoryId,this.categoryName,this.descriptions,this.status?"Active":"inActive",totalPrduct());
    }

    @Override
    public int getNewId(List<Category> list, String path) {
        list = IOFile.readFromFile(ShopConstant.CATEGORY_PATH);
        int idMax =0;
        for (Category c : list) {
            if (c.getCategoryId() > idMax) {
                idMax = c.getCategoryId();
            }
        }
        return idMax+1;
    }

    @Override
    public String toString() {
        return Colors.BLUE+ "ID: "+this.categoryId +" | Name: "+this.categoryName +Colors.RESET;
    }
    private int totalPrduct(){
        int count = 0;
        for(Products products: productsList.findAll()){
            if(products.getCategory().getCategoryId()== this.categoryId){
                count++;
            }
        }
        return count;
    }

}
