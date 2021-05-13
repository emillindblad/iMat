package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InfoHelp extends AnchorPane {
    @FXML private ImageView closeImage;
    @FXML private Button backButton;

    private final Info parentController;

    public InfoHelp(Info parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/infoHelp.fxml"));
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
        parentController.closeInfo();
    }
}
