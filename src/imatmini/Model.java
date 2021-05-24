/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imatmini;

/**
 *
 * @author oloft
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;


/**
 * Wrapper around the IMatDataHandler. The idea is that it might be useful to
 * add an extra layer that can provide special features
 *
 */
public class Model {

    private static Model instance = null;
    private IMatDataHandler iMatDataHandler;

     private final ArrayList<String> availableCardTypes = new ArrayList<String>(Arrays.asList("MasterCard", "Visa"));
     private final ArrayList<String> months = new ArrayList<String>(Arrays.asList("1", "2","3", "4", "5", "6"));
     private final ArrayList<String> years = new ArrayList<String>(Arrays.asList("19", "20", "21", "22", "23", "24", "25"));
    /**
     * Constructor that should never be called, use getInstance() instead.
     */
    protected Model() {
        // Exists only to defeat instantiation.
    }

    /**
     * Returns the single instance of the Model class.
     */
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
            instance.init();
        }
        return instance;
    }

    private void init() {

        iMatDataHandler = IMatDataHandler.getInstance();

    }

    public List<Product> getProducts() {
        return iMatDataHandler.getProducts();
    }

    public List<Product> getProducts(ProductCategory pc) {
        return iMatDataHandler.getProducts(pc);
    }

    public Product getProduct(int idNbr) {
        return iMatDataHandler.getProduct(idNbr);
    }
    
    public List<Product> findProducts(java.lang.String s) {
        return iMatDataHandler.findProducts(s);
    }

    public Image getImage(Product p) {
        return iMatDataHandler.getFXImage(p);
    }

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }

    /*
        Add product to the shopping cart
     */
    public void addToShoppingCart(Product p) {
        ShoppingItem newItem = new ShoppingItem(p);
        /*
            Duplicate check
         */
        for (ShoppingItem item : getShoppingCart().getItems()) {
            if(item.getProduct().equals(newItem.getProduct()))
            {
                item.setAmount(item.getAmount() + 1);
                return;
            }
        }
        getShoppingCart().addItem(newItem);
    }

    public List<String> getCardTypes() {
        return availableCardTypes;
    }
    
    public List<String> getMonths() {
        return months;
    }
    
    public List<String> getYears() {
        return years;
    }
    
    public CreditCard getCreditCard() {
        return iMatDataHandler.getCreditCard();
    }
    
    public Customer getCustomer() {
        return iMatDataHandler.getCustomer();
    }

    public ShoppingCart getShoppingCart() {
        return iMatDataHandler.getShoppingCart();
    }

    public void clearShoppingCart() {

        iMatDataHandler.getShoppingCart().clear();

    }

    public void placeOrder() {

        iMatDataHandler.placeOrder();

    }

    public boolean isFirstRun() {
        return iMatDataHandler.isFirstRun();
    }
    
    public int getNumberOfOrders() {
        return iMatDataHandler.getOrders().size();
    }

    public List<Order> getOrders(){
        return iMatDataHandler.getOrders();
    }

    public void shutDown() {
        iMatDataHandler.shutDown();
    }

    public boolean isDetailMissing() {
        return isContactDetailMissing() || isPaymentDetailMissing();
    }

    private boolean isContactDetailMissing() {
        Customer customer = getCustomer();

        return customer.getAddress().isEmpty() || customer.getFirstName().isEmpty() || customer.getLastName().isEmpty() ||
                customer.getPhoneNumber().isEmpty() || customer.getPostAddress().isEmpty() || customer.getPostCode().isEmpty();
    }

    private boolean isPaymentDetailMissing() {
        CreditCard card = getCreditCard();

        return card.getCardNumber().isEmpty() || card.getHoldersName().isEmpty();
    }
}
