package imatmini;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CartStep extends AnchorPane implements PurchaseSteps {


    public CartStep() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cart_step.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void next() {

    }

    @Override
    public void back() {

    }
}
