package imatmini;

import imatmini.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

public class FinalStepProduct extends AnchorPane {

    @FXML private ImageView productImage;
    @FXML private Label productName;
    @FXML private Label productAmount;
    @FXML private Label productSum;

    public FinalStepProduct(ShoppingItem shoppingItem, boolean gray) {
        Product product = shoppingItem.getProduct();

        productImage.setImage(Model.getInstance().getImage(product, 150, 150));
        productName.setText(product.getName());
        productAmount.setText(shoppingItem.getAmount() + " st");
        productSum.setText(shoppingItem.getTotal() + " kr");

        if (gray)
            this.setStyle("-fx-background-color: #D5D5D5");
    }

}
