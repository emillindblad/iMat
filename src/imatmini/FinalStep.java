package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FinalStep extends AnchorPane implements PurchaseStep {

    @FXML private AnchorPane infoPane;

    @FXML private AnchorPane progressBar;

    private final PurchaseProcess parentProcess;

    public FinalStep(boolean detailsMissing, PurchaseProcess parentProcess) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/final_step.fxml"));
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
            progressBar.getChildren().add(new ProgressBarMissing(this, 1));
        else
            progressBar.getChildren().add(new ProgressBar(this, 1));
    }

    @Override
    public void next() {

    }

    @Override
    public void back() {
        parentProcess.deliveryStep();
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
