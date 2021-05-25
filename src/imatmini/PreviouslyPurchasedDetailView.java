package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class PreviouslyPurchasedDetailView extends AnchorPane {
    private iMatMiniController parentController;

    @FXML
    private Label nameLabel;
    @FXML
    private Label adressLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label creditNameLabel;
    @FXML
    private Label creditCardLabel;

    @FXML
    private FlowPane flowPane;

    private Order order;

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

    public void populateView(Order order){
        populateItems(order.getItems());
        this.order = order;
    }

    private void populateItems(List<ShoppingItem> items){
        flowPane.getChildren().clear();
        for (ShoppingItem item:items) {
            PreviouslyPurchasedDetailViewItem productItem = new PreviouslyPurchasedDetailViewItem(item);
            flowPane.getChildren().add(productItem);
        }
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

    @FXML
    private void onAddToCart(Event event) {
        Model.getInstance().addPreviousPurchaseToCart(order);
        this.toBack();
        this.getParent().toBack();
    }
}
