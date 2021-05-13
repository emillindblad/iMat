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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DetailsStep detailsStep = new DetailsStep();
        detailsStepPane.getChildren().add(detailsStep);
        detailsStepPane.toBack();

        DeliveryStep deliveryStep = new DeliveryStep();
        deliveryStepPane.getChildren().add(deliveryStep);
        deliveryStepPane.toBack();

        FinalStep finalStep = new FinalStep();
        finalStepPane.getChildren().add(finalStep);
        finalStepPane.toBack();

        CartStep cartStep = new CartStep();
        cartStepPane.getChildren().add(cartStep);
    }


}
