package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.ShoppingCart;

import java.io.IOException;

public class CartStep extends AnchorPane implements PurchaseSteps {

    @FXML private AnchorPane infoPane;

    @FXML private Label sumLabel;

    private final Model model = Model.getInstance();
    private ShoppingCart shoppingCart;

    public CartStep() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/cart_step.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        updateCartStep();
    }

    public void  updateCartStep() {
        this.shoppingCart = model.getShoppingCart();
        sumLabel.setText("" + shoppingCart.getTotal() + " kr");
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
            System.out.println(e.getMessage());
        }
    }
}
