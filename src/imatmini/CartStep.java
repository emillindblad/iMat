package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CartStep extends AnchorPane implements PurchaseSteps {

    @FXML private AnchorPane infoPane;

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
        try {
            Stage stage = (Stage) infoPane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("iMatMini.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
