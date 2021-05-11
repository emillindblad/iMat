package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InfoHelp extends AnchorPane {
    @FXML private ImageView closeImage;
    @FXML private Button backButton;

    public InfoHelp(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("infoHelp.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
