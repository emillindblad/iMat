package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FinalStep extends AnchorPane implements PurchaseStep {

    @FXML private AnchorPane infoPane;
    @FXML private AnchorPane mainPane;
    @FXML private AnchorPane progressBar;

    @FXML private AnchorPane purchaseCompletePane;
    @FXML private Label endMessage;

    @FXML private AnchorPane detailsPane;
    @FXML private Label fullName;
    @FXML private Label address;
    @FXML private Label cityPostNum;

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
            progressBar.getChildren().add(new ProgressBarMissing(this, 4));
        else
            progressBar.getChildren().add(new ProgressBar(this, 3));
    }



    @FXML private void changeDetails(Event event) {
        MyInfo myInfo = new MyInfo();
        detailsPane.getChildren().add(myInfo);
        myInfo.setLayoutX(60);
        myInfo.setLayoutY(55);
        detailsPane.toFront();
    }

    // ----------- Methods for completing purchase -----------
    @Override
    public void next() {
        Model model = Model.getInstance();
        model.placeOrder();
        endMessage.setText("Tack " + model.getCustomer().getFirstName() + " för att du har handlat hos oss.");
        purchaseCompletePane.toFront();
    }

    @FXML private void backToStart(Event event) {
        try {
            Stage stage = (Stage) infoPane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("resources/views/iMatMini.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void back() {
        parentProcess.deliveryStep();
    }


    // ----------- Methods for infoPane -----------
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
