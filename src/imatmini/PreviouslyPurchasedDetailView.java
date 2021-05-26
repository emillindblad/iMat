package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;
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
    private Label creditDateLabel;

    @FXML private Label costLabel;
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
        this.order = order;
        populateItems(order.getItems());
        populateInfo();
    }

    private void populateInfo(){
        Customer customer = Model.getInstance().getCustomer();
        CreditCard card = Model.getInstance().getCreditCard();
        nameLabel.setText(customer.getFirstName() + " " + customer.getLastName());
        adressLabel.setText(customer.getAddress());
        cityLabel.setText(customer.getPostCode() + " " + customer.getPostAddress());

        creditNameLabel.setText(card.getHoldersName());
        creditCardLabel.setText(card.getCardNumber());
        creditDateLabel.setText(card.getValidMonth() + "/" + card.getValidYear());

        costLabel.setText(Commons.getCorrectDecimalFormat(Commons.getOrderTotalCost(order)) + " kr");
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
