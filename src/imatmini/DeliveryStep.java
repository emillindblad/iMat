package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DeliveryStep extends AnchorPane implements PurchaseStep {

    @FXML private AnchorPane infoPane;

    @FXML private AnchorPane progressBar;

    private final PurchaseProcess parentProcess;

    public DeliveryStep(boolean detailsMissing, PurchaseProcess parentProcess) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/delivery_step.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        addProgressBar(detailsMissing);
        this.parentProcess = parentProcess;
    }

    private void addProgressBar(boolean detailsMissing) {
        if (detailsMissing)
            progressBar.getChildren().add(new ProgressBarMissing(this, 3));
        else
            progressBar.getChildren().add(new ProgressBar(this, 2));
    }

    @Override
    public void next() {
        parentProcess.finalStep();
    }

    @Override
    public void back() {
        parentProcess.firstOrSecondStep();
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
