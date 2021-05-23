package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class PreviouslyPurchasedItem extends AnchorPane {
    private iMatMiniController parentController;
    private Order order;
    @FXML
    private Label receiptPrice;
    @FXML
    private Label receiptDate;
    @FXML
    private FlowPane flowPane;

    private final int imageSlotSize = 15;
    private final Model model = Model.getInstance();

    public PreviouslyPurchasedItem(Order order, iMatMiniController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/TidigareKop_Item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.parentController = parentController;
        this.order = order;
        this.receiptPrice.setText(calculateTotalCost(order) + " kr");
        updateOverviewImages();
        this.receiptDate.setText("Ordernummer: " + order.getOrderNumber());
    }

    @FXML
    private void onOpenReceipt(Event event){
        parentController.openPreviousPurchasedDetailView(order);
    }


    /*
    Den här delen kanske skall sitta i model
 */
    public float calculateTotalCost(Order order){
        float cost = 0;
        for (ShoppingItem item: order.getItems()) {
            cost += item.getProduct().getPrice();
        }
        return cost;
    }

    private void updateOverviewImages(){
        for (int i = 0; i < imageSlotSize; i++) {
            if(i >= order.getItems().size())
                break;

            PreviouslyPurchasedItemImage image = new PreviouslyPurchasedItemImage(model.getImage(order.getItems().get(i).getProduct()));
            flowPane.getChildren().add(image);


            if(i+1 == imageSlotSize && order.getItems().size() > imageSlotSize) {
                image.setExtraOverlay(order.getItems().size() - imageSlotSize);
                break;
            }

        }
    }
}

class PreviouslyPurchasedItemImage extends AnchorPane{
    @FXML private ImageView img;
    @FXML private Label extraLabel;

    public PreviouslyPurchasedItemImage(Image image)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/TidigareKop_ItemImage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        img.setImage(image);
        img.toFront();
    }

    public void setExtraOverlay(int amount){
        img.toBack();
        extraLabel.setText("+" + amount);
    }
}
