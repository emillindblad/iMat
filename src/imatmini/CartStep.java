package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.ShoppingCart;

import java.io.IOException;

public class CartStep extends AnchorPane implements PurchaseStep {

    @FXML private AnchorPane infoPane;

    @FXML private AnchorPane progressBar;

    @FXML private Label sumLabel;

    private final Model model = Model.getInstance();
    private ShoppingCart shoppingCart;

    private PurchaseProcess parentProcess;

    public CartStep(boolean detailsMissing, PurchaseProcess parentProcess) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/cart_step.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.parentProcess = parentProcess;
        addProgressBar(detailsMissing);

        updateCartStep();
    }

    public void  updateCartStep() {
        this.shoppingCart = model.getShoppingCart();
        sumLabel.setText("" + shoppingCart.getTotal() + " kr");
    }

    @Override
    public void next() {
        parentProcess.firstOrSecondStep();
    }

    @Override
    public void back() {
        try {
            Stage stage = (Stage) infoPane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("resources/views/iMatMini.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addProgressBar(boolean detailsMissing) {
        if (detailsMissing)
            progressBar.getChildren().add(new ProgressBarMissing(this, 1));
        else
            progressBar.getChildren().add(new ProgressBar(this, 1));
    }

    @Override
    public void closeInfo() {
        infoPane.getChildren().clear();
        infoPane.toBack();
    }

    @Override
    public void onInfo(Event event) {
        infoPane.getChildren().add(new InfoHelp(this));
        infoPane.toFront();
    }
}
