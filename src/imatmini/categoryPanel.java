/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

/**
 *
 * @author oloft
 */
public class categoryPanel extends AnchorPane {
    private iMatMiniController parentController;
    private Model model = Model.getInstance();
    private ProductCategory category;
    @FXML Label categoryName;

    public categoryPanel(ProductCategory category, iMatMiniController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/categoryPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
        this.category = category;
        categoryName.setText(category.name());
    }

    @FXML
    private void categoryBrowse() {
        System.out.println("Browse " + category.name());
        List<Product> products = model.getProducts(category);
        parentController.updateProductList(products);
    }
}
