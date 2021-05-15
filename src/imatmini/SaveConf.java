package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SaveConf extends AnchorPane {
    private final MyInfo parentController;

    @FXML private Button okButton;

    public SaveConf(MyInfo parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/saveConf.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
    }

    @FXML
    public void onClose(Event event) {
        parentController.onClose(event);
    }

    @FXML private void mouseTrap(Event event) {event.consume();}
}
