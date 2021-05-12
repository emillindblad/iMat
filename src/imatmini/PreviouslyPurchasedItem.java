package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PreviouslyPurchasedItem extends AnchorPane {
    @FXML
    private ImageView recipeListItemCuisine;
    @FXML
    private Label receiptPrice;
    @FXML
    private Label receiptDate;
    public PreviouslyPurchasedItem(String receiptPrice, String receiptDate) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TidigareKop_Item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.receiptPrice.setText(receiptPrice);
        this.receiptDate.setText(receiptDate);
    }

    private void updateOverviewImages(){
        // Update the overview images
    }
}
