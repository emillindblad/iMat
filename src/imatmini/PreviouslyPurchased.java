package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/TidigareKop.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.parentController = parentController;
    }

    private PreviouslyPurchasedItem purchasedItem;

    // Called by Controller
    public void updateReceipts(List<Order> orders){

        //Testing
        /*
        Den ska hämta alla tidigare köp och lägga in i flowpane på ett snyggt sätt
         */
        flowPane.getChildren().clear();
        for (Order order: orders) {
        purchasedItem = new PreviouslyPurchasedItem(order, parentController);
        flowPane.getChildren().add(purchasedItem);
        }
    }

    @FXML
    public void onClose(Event event) {
        this.getParent().toBack();
    }
}
