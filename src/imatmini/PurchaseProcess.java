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

    private final DetailsStep detailsStep = new DetailsStep();
    private final DeliveryStep deliveryStep = new DeliveryStep();
    private final FinalStep finalStep = new FinalStep();
    private final CartStep cartStep = new CartStep();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        detailsStepPane.getChildren().add(detailsStep);
        detailsStepPane.toBack();

        deliveryStepPane.getChildren().add(deliveryStep);
        deliveryStepPane.toBack();

        finalStepPane.getChildren().add(finalStep);
        finalStepPane.toBack();

        cartStepPane.getChildren().add(cartStep);

        addSteps();
    }

    private void addSteps() {
        Model model = Model.getInstance();

        if (model.isFirstRun()) {
            cartStep.addNextStep(detailsStep);
            deliveryStep.addPreviousStep(detailsStep);
        }
    }


}
