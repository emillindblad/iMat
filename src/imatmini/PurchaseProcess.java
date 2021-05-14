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
        this.detailsStep = new DetailsStep(detailsMissing, this);
        detailsStepPane.getChildren().add(detailsStep);
        detailsStepPane.toBack();

        this.deliveryStep = new DeliveryStep(detailsMissing, this);
        deliveryStepPane.getChildren().add(deliveryStep);
        deliveryStepPane.toBack();

        this.finalStep = new FinalStep(detailsMissing, this);
        finalStepPane.getChildren().add(finalStep);
        finalStepPane.toBack();

        this.cartStep = new CartStep(detailsMissing, this);
        cartStepPane.getChildren().add(cartStep);
    }

    public void firstStep() {
        cartStepPane.toFront();
    }

    public void secondStep() {
        if (detailsMissing)
            detailsStepPane.toFront();
        else
            deliveryStepPane.toFront();
    }

    public void firstOrSecondStep() {
        if (detailsMissing)
            detailsStepPane.toFront();
        else
            cartStepPane.toFront();
    }

    public void deliveryStep() {
        deliveryStepPane.toFront();
    }

    public void finalStep() {
        finalStepPane.toFront();
    }

}
