package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class CartStep extends AnchorPane implements PurchaseStep {

    @FXML private AnchorPane infoPane;

    @FXML private AnchorPane progressBar;

    @FXML private AnchorPane mainPane;

    @FXML private Label sumLabel;

    @FXML private FlowPane flowPane;

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
        mainPane.toFront();
    }

    public void  updateCartStep() {
        this.shoppingCart = model.getShoppingCart();
        populateProductList();
    }

    private void populateProductList()
    {
        sumLabel.setText("" + (float)shoppingCart.getTotal() + " kr");
        flowPane.getChildren().clear();
        for(ShoppingItem item : shoppingCart.getItems()){
            CartStepProduct product = new CartStepProduct(item, this);
            flowPane.getChildren().add(product);
        }
    }

    @Override
    public void next() {
        parentProcess.secondStep();
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
        InfoHelp infoHelp = new InfoHelp(this);
        infoPane.getChildren().add(infoHelp);
        infoHelp.setLayoutX(-250);
        infoHelp.setLayoutY(0);
        infoPane.toFront();
    }

    public void removeProduct(ShoppingItem shoppingItem){
        shoppingCart.removeItem(shoppingItem);
        populateProductList();
    }
}
