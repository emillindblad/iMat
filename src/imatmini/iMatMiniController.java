/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CartEvent;
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
    private ScrollPane productScrollPane; // Denna skall upp varje gång man väljer en kategori
    @FXML
    private TextField searchField;
    @FXML
    private Label itemsLabel;
    @FXML
    private Label costLabel;
    @FXML
    private FlowPane productsFlowPane;

    @FXML
    private Button searchButton;

    @FXML
    private FlowPane cartFlowPane;
    @FXML
    private ScrollPane cartScrollPane; // Eventuellt om man vill att skrollen ska hamna längst ned, på senaste vara

    @FXML
    private AnchorPane historyPane;

    @FXML
    private AnchorPane myAccountPane;

    // Detail View
    @FXML private AnchorPane productDetailPane;
    @FXML private ImageView detailImageView;
    @FXML private Label detailNameLabel;
    @FXML private Label detailPrizeLabel;
    @FXML private Label detailEcoLabel;
    private Product product;

   @FXML private FlowPane categoryFlowPane;
   private CategoryPanel selectedCategoryPanel;

    // Other variables
    private final Model model = Model.getInstance();
    private CategoryPanel viewAllCategory;
    private PreviouslyPurchased previouslyPurchased;
    private PreviouslyPurchasedDetailView previouslyPurchasedDetailView;

    private MyInfo myInfo;
    // Shop pane actions
    @FXML
    private void handleShowAccountAction(ActionEvent event) {
        openAccountView();
    }
    /*
         SEARCH METHODS
     */
    @FXML private void onEnterSearch(ActionEvent ae){
        handleSearchAction(ae);
    }

    @FXML private void onSearchTextChanged(Event event){
        if(searchField.getLength() > 0)
            searchButton.toFront();
        else
            searchButton.toBack();
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
        updateProductList(model.getProducts());
    }

    @FXML
    private void handleBuyItemsAction(ActionEvent event) {
        if(model.getShoppingCart().getItems().size() == 0)
            return;

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
    @FXML public void showAllProducts() {
        updateProductList(model.getProducts());
        selectCategoryPanel(viewAllCategory);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        model.getShoppingCart().addShoppingCartListener(this);

        updateProductList(model.getProducts());
        updateCategoryList();
        updateCartPanel();
        
        setupPreviouslyPurchasedPane();
        setupMyInfoView();
        setupPreviousPurchasedDetailView();

        shopPane.toFront();
        cartScrollPane.setVvalue(0);
    }

    private void setupMyInfoView() {
        myInfo = new MyInfo();
        myAccountPane.getChildren().add(myInfo);
        myInfo.setLayoutX(60);
        myInfo.setLayoutY(55);
        myAccountPane.toBack();
    }

    // Navigation
    public void openAccountView() {
        myInfo.updateMyInfo();
        myAccountPane.toFront();
    }
    
    // Shope pane methods
    @Override
    public void shoppingCartChanged(CartEvent evt) {
        updateCartPanel();
    }
    
    public void updateProductList(List<Product> products) {
        productsFlowPane.getChildren().clear();
        for (Product product : products) {
            productsFlowPane.getChildren().add(new ProductPanel(product, this));
        }
    }

    public void updateCategoryList() {
        categoryFlowPane.getChildren().clear();
        viewAllCategory = new CategoryPanel("Visa alla produkter", this);
        categoryFlowPane.getChildren().add(viewAllCategory);
        selectCategoryPanel(viewAllCategory);
        categoryFlowPane.getChildren().add(new CategoryPanel(model.getPantryCategory(), "Skafferi", this));
        categoryFlowPane.getChildren().add(new CategoryPanel(model.getProteinCategory(), "Kött & Fisk", this));
        categoryFlowPane.getChildren().add(new CategoryPanel(model.getGreeniesCategory(),"Grönsaker & Kål", this));
        categoryFlowPane.getChildren().add(new CategoryPanel(model.getFruitCategory(), "Frukt, Meloner & Bär", this));
        categoryFlowPane.getChildren().add(new CategoryPanel(model.getAltGreeniesCategory(), "Rotfrukter & Baljväxter", this));
        categoryFlowPane.getChildren().add(new CategoryPanel(model.getDairiesCategory(), "Mejeri", this));
        categoryFlowPane.getChildren().add(new CategoryPanel(model.getDrinksCategory(), "Dryck", this));
        categoryFlowPane.getChildren().add(new CategoryPanel(model.getSweetsAndNutsCategory(), "Nötter & Sötsaker", this));
    }

    private ArrayList<ProductCategory> getCategories(List<Product> products) {
        ArrayList<ProductCategory> categories = new ArrayList<>();
        for (Product product : products) {
            if (!categories.contains(product.getCategory())) {
               categories.add(product.getCategory());
            }
        }
        return categories;
    }

    private void updateCartPanel() {
        ShoppingCart shoppingCart = model.getShoppingCart();
        itemsLabel.setText("Antal varor: " + shoppingCart.getItems().size());
        costLabel.setText("Total summa: " + String.format("%.2f kr",shoppingCart.getTotal()));

        /*
         Adds all products in the shopping cart
         */
        cartFlowPane.getChildren().clear();
        boolean gray = true;
        for (ShoppingItem item : model.getShoppingCartItems()) {
            cartFlowPane.getChildren().add(new ShoppingCartItem(this, item, gray));
            gray = !gray;
        }

    }

    /*
        Previous Purchases Detail View
     */
    private void setupPreviouslyPurchasedPane(){
        previouslyPurchased = new PreviouslyPurchased(this);

        historyPane.getChildren().add(previouslyPurchased);
        previouslyPurchased.setLayoutX(60);
        previouslyPurchased.setLayoutY(55);

        historyPane.toBack();
    }
    public void openPurchaseHistoryView() {
        previouslyPurchased.updateReceipts(model.getOrders());
        historyPane.toFront();
    }

    private void setupPreviousPurchasedDetailView()
    {
        previouslyPurchasedDetailView = new PreviouslyPurchasedDetailView(this);
        historyPane.getChildren().add(previouslyPurchasedDetailView);
        previouslyPurchasedDetailView.setLayoutX(60);
        previouslyPurchasedDetailView.setLayoutY(55);
        previouslyPurchasedDetailView.toBack();

    }

    public void openPreviousPurchasedDetailView(Order order){
        previouslyPurchasedDetailView.populateView(order);
        previouslyPurchasedDetailView.toFront();
    }


    public void openProductDetailView(Product product, double kImageWidth, double kImageRatio) {
        this.product = product;
        System.out.println("Open " + product.getName() + "\n Category: " + product.getCategory());
        detailNameLabel.setText(product.getName());
        detailPrizeLabel.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
        detailImageView.setImage(model.getImage(product, kImageWidth, kImageWidth*kImageRatio));
        if (!product.isEcological()) {
            detailEcoLabel.setText("");
        }
        productDetailPane.toFront();
    }

    public void closeProductDetailView() {
        productDetailPane.toBack();
    }

    public void productDetailViewAddToCart(ActionEvent actionEvent) {
        System.out.println("Add " + product.getName());
        model.addToShoppingCart(product);
        closeProductDetailView();
    }

    public void selectCategoryPanel(CategoryPanel categoryPanel){
        if(selectedCategoryPanel != null){
            selectedCategoryPanel.setSelectedOverlay(false);
        }

        productScrollPane.setVvalue(0);
        searchField.setText("");
        selectedCategoryPanel = categoryPanel;
        selectedCategoryPanel.setSelectedOverlay(true);
    }
}
