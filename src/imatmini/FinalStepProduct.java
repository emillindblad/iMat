package imatmini;

import imatmini.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class FinalStepProduct extends AnchorPane {

    @FXML private ImageView productImage;
    @FXML private Label productName;
    @FXML private Label productAmount;
    @FXML private Label productSum;

    public FinalStepProduct(ShoppingItem shoppingItem, boolean gray) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/final_step_product.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Product product = shoppingItem.getProduct();

        productImage.setImage(Model.getInstance().getImage(product, 150, 150));
        productName.setText(product.getName());
        productAmount.setText(shoppingItem.getAmount() + " st");
        productSum.setText(Commons.getCorrectDecimalFormat(shoppingItem.getTotal()) + " kr");

        if (gray)
            this.setStyle("-fx-background-color: #D5D5D5");
    }

}
