package imatmini;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ShoppingCartItem extends AnchorPane {
    /**
     * This class is used for wrapping ShoppingItem into a SceneBuilder component
     */

    private final Model model = Model.getInstance();
    private final ShoppingItem item;
    private iMatMiniController parentController;
    @FXML
    private Label itemLabel;
    @FXML
    private Label priceLabel;
    @FXML private Label unitLabel;

    @FXML private TextField productAmount;

    public ShoppingCartItem(iMatMiniController parentController, ShoppingItem item, boolean gray) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/ShoppingCart_Item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.itemLabel.setText(item.getProduct().getName());
        this.priceLabel.setText(Commons.getCorrectDecimalFormat(item.getTotal()) + " kr");
        this.item = item;
        this.parentController = parentController;

        Commons.setTextLimit(productAmount, 2);
        Commons.allowIntegersOnly(productAmount);
        if (gray)
            this.setStyle("-fx-background-color: #D5D5D5");

        updateAmountText();
    }

    @FXML
    private void removeProduct(Event event) {
        setProductAmount(item.getAmount() - 1, item);
        parentController.shoppingCartChanged(new CartEvent(this));
        updateAmountText();
    }

    @FXML
    private void addProduct(Event event) {
        setProductAmount(item.getAmount() + 1, item);
        parentController.shoppingCartChanged(new CartEvent(this));

        updateAmountText();
    }

    @FXML
    private void updateAmount(ActionEvent event) {
        int amount;
        try {
            amount = Integer.parseInt(getNumbersFromString(productAmount.getText()));
        }
        catch (NumberFormatException e)
        {
            amount = 1;
        }
        setProductAmount(amount, item);
        productAmount.positionCaret(productAmount.getLength());
        parentController.shoppingCartChanged(new CartEvent(this));
    }

    private void setProductAmount(double amount, ShoppingItem item) {
        if (amount > 0)
            item.setAmount(amount);
        else
            onDeleteProduct();
    }

    private String getNumbersFromString(String text){
        return text.replaceAll("[^0-9]", "");
    }

    private void updateAmountText() {
        productAmount.setText((int)item.getAmount() + "");

        productAmount.positionCaret(productAmount.getLength());
        unitLabel.setText(item.getProduct().getUnit().substring(3));
    }

    @FXML
    private void onDeleteProduct(){
        System.out.println("Deleting product " + item.getProduct().getName() + " from the cart");
        model.getShoppingCart().removeItem(item);
        parentController.shoppingCartChanged(new CartEvent(this));
        parentController.updateProductList(model.getProducts());
    }
}
