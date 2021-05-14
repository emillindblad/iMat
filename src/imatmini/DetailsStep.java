package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DetailsStep extends AnchorPane implements PurchaseStep {

    @FXML private AnchorPane infoPane;

    @FXML private AnchorPane progressBar;

    private final PurchaseProcess parentProcess;

    public DetailsStep(boolean detailsMissing, PurchaseProcess parentProcess) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/details_step.fxml"));
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
        progressBar.getChildren().add(new ProgressBarMissing(this, 2));
    }

    @Override
    public void next() {
        parentProcess.deliveryStep();
    }

    @Override
    public void back() {
        parentProcess.firstStep();
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
}
