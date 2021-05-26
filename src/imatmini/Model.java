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

    public void addPreviousPurchaseToCart(Order order) {
        getShoppingCart().clear();

        for (ShoppingItem item : order.getItems())
            getShoppingCart().addItem(item);
    }

    public ShoppingItem getShoppingItem(Product p) {
        ShoppingItem newItem = new ShoppingItem(p);

        for (ShoppingItem item : getShoppingCart().getItems())
            if(item.getProduct().equals(newItem.getProduct()))

                return item;

        return null;
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

    public ArrayList<ProductCategory> getPantryCategory(){
        ArrayList<ProductCategory> pantry = new ArrayList<>();
        pantry.add(ProductCategory.POTATO_RICE);
        pantry.add(ProductCategory.PASTA);
        pantry.add(ProductCategory.FLOUR_SUGAR_SALT);
        return pantry;
    }
    public ArrayList<ProductCategory> getProteinCategory(){
        ArrayList<ProductCategory> protein = new ArrayList<>();
        protein.add(ProductCategory.MEAT);
        protein.add(ProductCategory.FISH);
        return protein;
    }
    public ArrayList<ProductCategory> getGreeniesCategory(){
        ArrayList<ProductCategory> greenies = new ArrayList<>();
        greenies.add(ProductCategory.VEGETABLE_FRUIT);
        greenies.add(ProductCategory.CABBAGE);
        return greenies;
    }
    public ArrayList<ProductCategory> getFruitCategory(){
        ArrayList<ProductCategory> fruit = new ArrayList<>();
        fruit.add(ProductCategory.FRUIT);
        fruit.add(ProductCategory.BERRY);
        fruit.add(ProductCategory.EXOTIC_FRUIT);
        fruit.add(ProductCategory.CITRUS_FRUIT);
        fruit.add(ProductCategory.MELONS);
        return fruit;
    }
    public ArrayList<ProductCategory> getAltGreeniesCategory(){
        ArrayList<ProductCategory> altGreenies = new ArrayList<>();
        altGreenies.add(ProductCategory.ROOT_VEGETABLE);
        altGreenies.add(ProductCategory.POD);
        return altGreenies;
    }
    public ArrayList<ProductCategory> getDairiesCategory(){
        ArrayList<ProductCategory> mejeri = new ArrayList<>();
        mejeri.add(ProductCategory.DAIRIES);
        return mejeri;
    }
    public ArrayList<ProductCategory> getDrinksCategory(){
        ArrayList<ProductCategory> drinks = new ArrayList<>();
        drinks.add(ProductCategory.COLD_DRINKS);
        drinks.add(ProductCategory.HOT_DRINKS);
        return drinks;
    }
    public ArrayList<ProductCategory> getSweetsAndNutsCategory(){
        ArrayList<ProductCategory> sweetsAndNuts = new ArrayList<>();
        sweetsAndNuts.add(ProductCategory.NUTS_AND_SEEDS);
        sweetsAndNuts.add(ProductCategory.SWEET);
        return sweetsAndNuts;
    }
}
