package imatmini;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.Date;

public class PreviouslyPurchasedItem extends AnchorPane {
    private Order order;
    @FXML
    private Label receiptPrice;
    @FXML
    private Label receiptDate;
    @FXML
    private FlowPane flowPane;

    private final int imageSlotSize = 15;
    private final Model model = Model.getInstance();

    public PreviouslyPurchasedItem(Order order) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TidigareKop_Item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.order = order;
        this.receiptPrice.setText(calculateTotalCost(order) + " kr");
        updateOverviewImages();
        //this.receiptDate.setText("" + receiptDate);
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
    ImageView img;
    private void updateOverviewImages(){
        for (int i = 0; i < imageSlotSize; i++) {
            if(i >= order.getItems().size())
                break;
            ImageView image = new ImageView();
            image.setImage(model.getImage(order.getItems().get(i).getProduct(), 100, 100));
            flowPane.getChildren().add(image);
        }

        /*
        img = new ImageView();
        img.setImage(model.getImage(order.getItems().get(0).getProduct(), 100, 100));
        flowPane.getChildren().add(img);

         */
    }
}
