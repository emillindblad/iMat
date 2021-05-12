package imatmini;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PreviouslyPurchasedItem extends AnchorPane {
    public PreviouslyPurchasedItem() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TidigareKop_Item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
