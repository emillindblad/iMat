/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
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

    // Shopping Cart Pane
    @FXML
    private FlowPane cartFlowPane;
    
    // Account Pane
    @FXML
    private AnchorPane accountPane;
    @FXML 
    ComboBox cardTypeCombo;
    @FXML
    private TextField numberTextField;
    @FXML
    private TextField nameTextField;
    @FXML 
    private ComboBox monthCombo;
    @FXML
    private ComboBox yearCombo;
    @FXML
    private TextField cvcField;
    @FXML
    private Label purchasesLabel;

    // History Pane
    @FXML
    private AnchorPane historyPane;

    private PreviouslyPurchased previouslyPurchased;

    // Use to remember which pane is under an overlay
    private AnchorPane currentPane = shopPane;
    
    // Other variables
    private final Model model = Model.getInstance();


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
        model.placeOrder();
        costLabel.setText("Köpet klart!");
    }

    // Account pane actions
     @FXML
    private void handleDoneAction(ActionEvent event) {
        closeAccountView();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        model.getShoppingCart().addShoppingCartListener(this);

        updateProductList(model.getProducts());
        updateBottomPanel();
        
        setupAccountPane();
        setupPreviouslyPurchasedPane();

        /*
         Testing purposes
         */
        //MyInfo myInfo = new MyInfo(this);
        //myInfo.toFront();

    }    

    private void setupPreviouslyPurchasedPane(){
        previouslyPurchased = new PreviouslyPurchased(this);
        historyPane.getChildren().add(previouslyPurchased);
        /*
         Hard coding to get the layout slightly right
         */
        previouslyPurchased.setLayoutX(60);
        previouslyPurchased.setLayoutY(55);
        historyPane.toBack();
    }

    // Navigation
    public void openAccountView() {
        updateAccountPanel();
        accountPane.toFront();
    }

    public void closeAccountView() {
        updateCreditCard();
        shopPane.toFront();
    }

    public void openPurchaseHistoryView() {
        previouslyPurchased.updateReceipts(model.getOrders());
        historyPane.toFront();
    }
    
    // Shop pane methods
    @Override
     public void shoppingCartChanged(CartEvent evt) {
        updateBottomPanel();
    }
   
    // Updated by search function
    private void updateProductList(List<Product> products) {
        productsFlowPane.getChildren().clear();
        for (Product product : products) {
            productsFlowPane.getChildren().add(new ProductPanel(product));
        }
    }

    // Updates every time a product gets added
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
    
    private void updateAccountPanel() {
        
        CreditCard card = model.getCreditCard();
        
        numberTextField.setText(card.getCardNumber());
        nameTextField.setText(card.getHoldersName());
        
        cardTypeCombo.getSelectionModel().select(card.getCardType());
        monthCombo.getSelectionModel().select(""+card.getValidMonth());
        yearCombo.getSelectionModel().select(""+card.getValidYear());

        cvcField.setText(""+card.getVerificationCode());
        
        purchasesLabel.setText(model.getNumberOfOrders()+ " tidigare inköp hos iMat");
        
    }
    
    private void updateCreditCard() {
        
        CreditCard card = model.getCreditCard();
        
        card.setCardNumber(numberTextField.getText());
        card.setHoldersName(nameTextField.getText());
        
        String selectedValue = (String) cardTypeCombo.getSelectionModel().getSelectedItem();
        card.setCardType(selectedValue);
        
        selectedValue = (String) monthCombo.getSelectionModel().getSelectedItem();
        card.setValidMonth(Integer.parseInt(selectedValue));
        
        selectedValue = (String) yearCombo.getSelectionModel().getSelectedItem();
        card.setValidYear(Integer.parseInt(selectedValue));
        
        card.setVerificationCode(Integer.parseInt(cvcField.getText()));

    }
    
    private void setupAccountPane() {
                
        cardTypeCombo.getItems().addAll(model.getCardTypes());
        
        monthCombo.getItems().addAll(model.getMonths());
        
        yearCombo.getItems().addAll(model.getYears());
        
    }

    public void setCurrentPane(AnchorPane currentPane) {
        this.currentPane = currentPane;
    }

    public void showCurrentPane() {
        this.currentPane.toFront();
    }
}
