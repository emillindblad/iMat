/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.io.IOException;
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
    private final ProductCategory category;
    private static final String foobar = "foobar";
    private static final String selected = "categorycardSelected";
    @FXML Label categoryName;
    @FXML AnchorPane categoryPanel;

    public CategoryPanel(ProductCategory category, iMatMiniController parentController) {
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
        categoryName.setText(Commons.getCategoryNameInSwedish(category));
    }

    @FXML
    private void categoryBrowse() {
        System.out.println("Browse " + category.name());
        List<Product> products = model.getProducts(category);
        //categoryName.getStyleClass().add(foobar); WIP
        //categoryPanel.getStyleClass().add(selected);
        parentController.updateProductList(products);
    }
}
