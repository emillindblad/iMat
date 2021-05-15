/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.*;


/**
 *
 * @author oloft
 */
public class iMatMiniController implements Initializable, ShoppingCartListener {
    
    // Shopping Pane
    @FXML
    private AnchorPane shopPane;
    @FXML
    private TextField searchField;
    @FXML
    private Label itemsLabel;
    @FXML
    private Label costLabel;
    @FXML
    private FlowPane productsFlowPane;


    @FXML
    private FlowPane cartFlowPane;

    @FXML
    private AnchorPane historyPane;

    @FXML
    private AnchorPane myAccountPane;

    
    // Other variables
    private final Model model = Model.getInstance();

    private PreviouslyPurchased previouslyPurchased;

    private MyInfo myInfo;
    // Shop pane actions
    @FXML
    private void handleShowAccountAction(ActionEvent event) {
        openAccountView();
    }

    @FXML
    private void handleSearchAction(ActionEvent event) {

        List<Product> matches = model.findProducts(searchField.getText());
        updateProductList(matches);
        System.out.println("# matching products: " + matches.size());
    }

    @FXML
    private void handleClearCartAction(ActionEvent event) {
        model.clearShoppingCart();
    }

    @FXML
    private void handleBuyItemsAction(ActionEvent event) {
        /*model.placeOrder();
        costLabel.setText("Köpet klart!");*/
        Stage stage;
        Parent root;
        try {
            stage = (Stage) cartFlowPane.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("resources/views/purchase_process.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        model.getShoppingCart().addShoppingCartListener(this);

        updateProductList(model.getProducts());
        updateBottomPanel();
        
        setupPreviouslyPurchasedPane();
        setupMyInfoView();

        /*
         Testing purposes
         */
        //MyInfo myInfo = new MyInfo(this);
        //myInfo.toFront();

    }

    private void setupMyInfoView() {
        myInfo = new MyInfo();
        myAccountPane.getChildren().add(myInfo);
        myInfo.setLayoutX(60);
        myInfo.setLayoutY(55);
        myAccountPane.toBack();
    }

    private void setupPreviouslyPurchasedPane(){
        previouslyPurchased = new PreviouslyPurchased(this);

        historyPane.getChildren().add(previouslyPurchased);
        previouslyPurchased.setLayoutX(60);
        previouslyPurchased.setLayoutY(55);

        historyPane.toBack();
    }

    // Navigation
    public void openAccountView() {
        myInfo.updateMyInfo();
        myAccountPane.toFront();
    }

    public void openPurchaseHistoryView() {
        previouslyPurchased.updateReceipts(model.getOrders());
        historyPane.toFront();

    }
    
    // Shope pane methods
    @Override
     public void shoppingCartChanged(CartEvent evt) {
        updateBottomPanel();
    }
   
    
    private void updateProductList(List<Product> products) {
        productsFlowPane.getChildren().clear();
        for (Product product : products) {
            productsFlowPane.getChildren().add(new ProductPanel(product));
        }
    }
    
    private void updateBottomPanel() {
        ShoppingCart shoppingCart = model.getShoppingCart();
        itemsLabel.setText("Antal varor: " + shoppingCart.getItems().size());
        costLabel.setText("Kostnad: " + String.format("%.2f",shoppingCart.getTotal()));

        /*
         Add to the shopping cart
         Currently does not check if we already have the same item in the cart
         */
        cartFlowPane.getChildren().clear();
        for (ShoppingItem item : model.getShoppingCart().getItems()) {
            cartFlowPane.getChildren().add(new ShoppingCartItem(item));
        }
    }
}
