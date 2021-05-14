package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseProcess implements Initializable {
    @FXML private AnchorPane cartStepPane;
    @FXML private AnchorPane detailsStepPane;
    @FXML private AnchorPane deliveryStepPane;
    @FXML private AnchorPane finalStepPane;

    private DetailsStep detailsStep;
    private DeliveryStep deliveryStep;
    private FinalStep finalStep;
    private CartStep cartStep;

    private final boolean detailsMissing = Model.getInstance().isDetailMissing();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cartStepPane.getChildren().add(new CartStep(detailsMissing, this));
        cartStepPane.toFront();
    }

    public void firstStep() {
        detailsStepPane.getChildren().clear();
        detailsStepPane.toBack();
        cartStepPane.getChildren().add(new CartStep(detailsMissing, this));
        cartStepPane.toFront();
    }

    public void secondStep() {
        if (detailsMissing) {
            cartStepPane.getChildren().clear();
            cartStepPane.toBack();
            detailsStepPane.getChildren().add(new DetailsStep(true, this));
            detailsStepPane.toFront();
        }
        else {
            detailsStepPane.getChildren().clear();
            detailsStepPane.toBack();
            deliveryStepPane.getChildren().add(new DeliveryStep(false, this));
            deliveryStepPane.toFront();
        }
    }

    public void firstOrSecondStep() {
        if (detailsMissing) {
            deliveryStepPane.getChildren().clear();
            deliveryStepPane.toBack();
            detailsStepPane.getChildren().add(new DetailsStep(true, this));
            detailsStepPane.toFront();
        }
        else {
            deliveryStepPane.getChildren().clear();
            deliveryStepPane.toBack();
            cartStepPane.getChildren().add(new CartStep(false, this));
            cartStepPane.toFront();
        }
    }

    public void deliveryStep() {
        finalStepPane.getChildren().clear();
        detailsStepPane.getChildren().clear();
        deliveryStepPane.getChildren().add(new DeliveryStep(detailsMissing, this));
        deliveryStepPane.toFront();
    }

    public void finalStep() {
        deliveryStepPane.getChildren().clear();
        finalStepPane.getChildren().add(new FinalStep(detailsMissing, this));
        finalStepPane.toFront();
    }

}
