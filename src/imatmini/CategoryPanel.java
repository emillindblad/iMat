/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

/**
 *
 * @author oloft
 */
public class CategoryPanel extends AnchorPane {
    private iMatMiniController parentController;
    private Model model = Model.getInstance();
    private final List<ProductCategory> categories;
    private static final String foobar = "foobar";
    private static final String selected = "categorycardSelected";
    @FXML Label categoryName;
    @FXML AnchorPane categoryPanel;

    public CategoryPanel(List<ProductCategory> categories, String categoryName, iMatMiniController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/categoryPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
        this.categories = categories;
        this.categoryName.setText(categoryName);
    }
    public CategoryPanel(String categoryName, iMatMiniController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/categoryPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        categories = null;
        this.parentController = parentController;
        this.categoryName.setText(categoryName);
    }

    @FXML
    private void categoryBrowse() {
        System.out.println("Browse " + categoryName.getText());
        ArrayList<Product> products = new ArrayList<>();

        if(categories != null)
            for (ProductCategory category : categories)
                products.addAll(model.getProducts(category));
        else
            products.addAll(model.getProducts());


        parentController.selectCategoryPanel(this);
        parentController.updateProductList(products);
    }

    public void setSelectedOverlay(boolean bool){
        if(bool)
            categoryPanel.getStyleClass().add(selected);
        else
            categoryPanel.getStyleClass().remove(selected);
    }
}
