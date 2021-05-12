package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreviouslyPurchased extends AnchorPane{
    @FXML private Button backButton;
    @FXML
    private FlowPane flowPane;

    private final Model model = Model.getInstance();
    private final iMatMiniController parentController;

    private ArrayList<PreviouslyPurchasedItem> items = new ArrayList<>();

    public PreviouslyPurchased(iMatMiniController parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TidigareKop.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.parentController = parentController;
    }

    private void updateReceipts(){

        //Testing
        PreviouslyPurchasedItem purchasedItem = new PreviouslyPurchasedItem("770", "27/4");
        items.add(purchasedItem);
        flowPane.getChildren().add(purchasedItem);

    }
    @FXML
    public void onClose(Event event) {
        this.toBack();
    }
}
