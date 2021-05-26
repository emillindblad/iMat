package imatmini;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.DecimalFormat;

public class CartStepProduct extends AnchorPane {
    private ShoppingItem shoppingItem;
    @FXML private Label productName;
    @FXML private ImageView productImage;
    @FXML private Label productCost;
    //@FXML private Label productAmount;
    @FXML private TextField productAmount;

    private CartStep parent;

    public CartStepProduct(ShoppingItem shoppingItem, CartStep parent, boolean gray) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/cart_step_product.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.shoppingItem = shoppingItem;
        this.parent = parent;
        populateProduct();

        if (gray)
            this.setStyle("-fx-background-color: #D5D5D5");
    }

    private void populateProduct(){
        this.productName.setText(shoppingItem.getProduct().getName());
        this.productImage.setImage(Model.getInstance().getImage(shoppingItem.getProduct()));
        updateCostText();
        updateAmountText();
    }

    @FXML
    private void onIncreaseProductAmount(Event event){
        setProductAmount(shoppingItem.getAmount()+1);
    }

    @FXML
    private void onDecreaseProductAmount(Event event){
        setProductAmount(shoppingItem.getAmount()-1);
    }

    @FXML
    private void onSetProductAmount(Event event){
        int amount;
        try {
            amount = Integer.parseInt(getNumbersFromString(productAmount.getText()));
            }
        catch (NumberFormatException e)
        {
            amount = 1;
        }
            setProductAmount(amount);
            productAmount.positionCaret(productAmount.getLength());
    }

    @FXML
    private void onDeleteProduct(){
        parent.removeProduct(shoppingItem);

        if (Model.getInstance().getShoppingCart().getItems().size() == 0)
            parent.back();
    }
    private void setProductAmount(double amount){
        shoppingItem.setAmount(amount);
        updateAmountText();
        updateCostText();

        if(shoppingItem.getAmount() <= 0)
            onDeleteProduct();
    }

    private void updateCostText(){
        this.productCost.setText(Commons.getCorrectDecimalFormat(shoppingItem.getTotal())  + " kr");
    }

    private void updateAmountText(){
        this.productAmount.setText((int)shoppingItem.getAmount() + "");
    }

    private String getNumbersFromString(String text){
        return text.replaceAll("[^0-9]", "");
    }


}
