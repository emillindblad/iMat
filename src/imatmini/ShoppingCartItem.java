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

    public ShoppingCartItem(ShoppingItem item, boolean gray) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/ShoppingCart_Item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.itemLabel.setText(item.getProduct().getName());
        this.priceLabel.setText(Commons.getCorrectDecimalFormat(item.getTotal()) + " kr");
        this.item = item;

        if (gray)
            this.setStyle("-fx-background-color: #D5D5D5");
    }
}
