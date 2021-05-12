package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ShoppingCartItem extends AnchorPane {
    /**
     * This class is used for wrapping ShoppingItem into a SceneBuilder component
     */

    private ShoppingItem item;
    @FXML
    private Label itemLabel;
    @FXML
    private Label priceLabel;

    public ShoppingCartItem(ShoppingItem item) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingCart_Item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.itemLabel.setText(item.getProduct().getName());
        this.priceLabel.setText(item.getProduct().getPrice() + " kr");
        this.item = item;
    }
}
