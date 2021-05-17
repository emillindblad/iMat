package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PreviouslyPurchasedDetailView extends AnchorPane {
    private iMatMiniController parentController;

    public PreviouslyPurchasedDetailView(iMatMiniController parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/TidigareKop_DetailView.fxml"));
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
        this.toBack();
        this.getParent().toBack();
    }

    @FXML
    public void onPrevious(Event event){
        this.toBack();
    }
}
