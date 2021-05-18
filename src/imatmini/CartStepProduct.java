package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class CartStepProduct extends AnchorPane {
    private ShoppingItem shoppingItem;
    @FXML private Label productName;
    @FXML private ImageView productImage;
    @FXML private Label productCost;
    @FXML private Label productAmount;

    private CartStep parent;

    public CartStepProduct(ShoppingItem shoppingItem, CartStep parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/cart_step_product.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.shoppingItem = shoppingItem;
        this.parent = parent;
        populateProduct();
    }

    private void populateProduct(){
        this.productName.setText(shoppingItem.getProduct().getName());
        this.productImage.setImage(Model.getInstance().getImage(shoppingItem.getProduct()));
        this.productCost.setText(shoppingItem.getTotal() + " kr");
        this.productAmount.setText(shoppingItem.getAmount() + "");
    }

    @FXML
    private void onDeleteProduct(){
        parent.removeProduct(shoppingItem);
    }

}
