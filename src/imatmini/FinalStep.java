package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FinalStep extends AnchorPane implements PurchaseSteps {

    @FXML private AnchorPane progressBar;

    public FinalStep(boolean detailsMissing) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/final_step.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        addProgressBar(detailsMissing);
    }

    private void addProgressBar(boolean detailsMissing) {
        if (detailsMissing)
            progressBar.getChildren().add(new ProgressBarMissing(this, 1));
        else
            progressBar.getChildren().add(new ProgressBar(this, 1));
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
