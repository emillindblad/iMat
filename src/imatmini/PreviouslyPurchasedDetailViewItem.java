package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class PreviouslyPurchasedDetailViewItem extends AnchorPane {
    private iMatMiniController parentController;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label amountLabel;
    @FXML
    private Label unitLabel;
    @FXML
    private ImageView productImage;

    private final Model model = Model.getInstance();

    public PreviouslyPurchasedDetailViewItem(ShoppingItem item) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/TidigareKop_DetailView_Item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.productNameLabel.setText(item.getProduct().getName());
        this.priceLabel.setText(Commons.getCorrectDecimalFormat(item.getTotal()) + " kr");
        this.amountLabel.setText((int)item.getAmount() + " " + item.getProduct().getUnitSuffix());
        this.unitLabel.setText(item.getProduct().getPrice() + " " + item.getProduct().getUnit());
        this.productImage.setImage(model.getImage(item.getProduct()));

    }
}
