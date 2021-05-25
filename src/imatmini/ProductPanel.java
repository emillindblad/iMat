/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

/**
 *
 * @author oloft
 */
public class ProductPanel extends AnchorPane {
    private iMatMiniController parentController;

    @FXML ImageView imageView;
    @FXML Label nameLabel;
    @FXML Label prizeLabel;
    @FXML Label ecoLabel;

    @FXML private Button addButton;
    @FXML private AnchorPane addAnchor;

    @FXML private TextField productAmount;
    @FXML private Label unitLabel;
    
    private Model model = Model.getInstance();

    private Product product;
    
    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;

    public ProductPanel(Product product, iMatMiniController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/ProductPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
        this.product = product;
        nameLabel.setText(product.getName());
        prizeLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
        imageView.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));
        if (!product.isEcological()) {
            ecoLabel.setText("");
        }

        correctViewToFront();
        updateAmountText();
    }

    @FXML
    private void openDetailView() {
        System.out.println("Open " + product.getName());
        parentController.openProductDetailView(product, kImageWidth, kImageRatio);
    }

    @FXML
    private void handleAddAction(ActionEvent event) {
        System.out.println("Add " + product.getName());
        model.addToShoppingCart(product);
        parentController.shoppingCartChanged(new CartEvent(this));
        updateAmountText();
        addAnchor.toFront();
    }

    @FXML
    private void removeProduct(Event event) {
        ShoppingItem item = model.getShoppingItem(product);

        setProductAmount(item.getAmount() - 1, item);
        parentController.shoppingCartChanged(new CartEvent(this));

        correctViewToFront();
        updateAmountText();
    }

    @FXML
    private void addProduct(Event event) {
        ShoppingItem item = model.getShoppingItem(product);

        setProductAmount(item.getAmount() + 1, item);
        parentController.shoppingCartChanged(new CartEvent(this));

        updateAmountText();
    }

    @FXML
    private void updateAmount(ActionEvent event) {
        int amount;
        try {
            amount = Integer.parseInt(getNumbersFromString(productAmount.getText()));
        }
        catch (NumberFormatException e)
        {
            amount = 1;
        }
        setProductAmount(amount, model.getShoppingItem(product));
        productAmount.positionCaret(productAmount.getLength());
    }

    private void setProductAmount(double amount, ShoppingItem item) {
        if (amount > 0)
            item.setAmount(amount);
        else
            model.getShoppingCart().removeItem(item);
    }

    private String getNumbersFromString(String text){
        return text.replaceAll("[^0-9]", "");
    }

    private void updateAmountText() {
        ShoppingItem item = model.getShoppingItem(product);

        if (item != null)
            productAmount.setText((int)item.getAmount() + "");

        productAmount.positionCaret(productAmount.getLength());
        unitLabel.setText(product.getUnit().substring(3));
    }

    private void correctViewToFront() {
        if (model.getShoppingItem(product) != null)
            addAnchor.toFront();
        else
            addButton.toFront();
    }
}
