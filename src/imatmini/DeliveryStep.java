package imatmini;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DeliveryStep extends AnchorPane implements PurchaseSteps {

    public DeliveryStep(boolean detailsMissing) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/delivery_step.fxml"));
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

    @Override
    public void addNextStep(PurchaseSteps nextStep) {

    }

    @Override
    public void addPreviousStep(PurchaseSteps previousStep) {

    }

    @Override
    public void closeInfo() {

    }

    @Override
    public void onInfo(Event event) {

    }
}
