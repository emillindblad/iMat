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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boolean detailsMissing = Model.getInstance().isDetailMissing();
        this.detailsStep = new DetailsStep(detailsMissing);
        detailsStepPane.getChildren().add(detailsStep);
        detailsStepPane.toBack();

        this.deliveryStep = new DeliveryStep(detailsMissing);
        deliveryStepPane.getChildren().add(deliveryStep);
        deliveryStepPane.toBack();

        this.finalStep = new FinalStep(detailsMissing);
        finalStepPane.getChildren().add(finalStep);
        finalStepPane.toBack();

        this.cartStep = new CartStep(detailsMissing);
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
