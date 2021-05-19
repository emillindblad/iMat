package imatmini;

import imatmini.resources.views.FinalStepProduct;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class FinalStep extends AnchorPane implements PurchaseStep {

    @FXML private AnchorPane infoPane;
    @FXML private AnchorPane progressBar;
    @FXML private AnchorPane detailsPane;

    @FXML private AnchorPane mainPane;
    @FXML private Label fullName;
    @FXML private Label address;
    @FXML private Label cityPostNum;
    @FXML private Label cardName;
    @FXML private Label cardNumber;
    @FXML private Label cardValidDateCVC;

    @FXML private FlowPane productFlowPane;
    @FXML private Label totalSum;

    @FXML private AnchorPane purchaseCompletePane;
    @FXML private Label endMessage;


    private final PurchaseProcess parentProcess;

    public FinalStep(boolean detailsMissing, PurchaseProcess parentProcess) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/final_step.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        addProgressBar(detailsMissing);
        updateDetails();
        updateProductList();
        this.parentProcess = parentProcess;
    }

    private void updateProductList() {
        List<ShoppingItem> items = Model.getInstance().getShoppingCart().getItems();
        boolean gray = false;
        for (ShoppingItem item : items) {
            productFlowPane.getChildren().add(new FinalStepProduct(item, gray));
            gray = !gray;
        }
        totalSum.setText(Model.getInstance().getShoppingCart().getTotal() + " kr");
    }

    private void addProgressBar(boolean detailsMissing) {
        if (detailsMissing)
            progressBar.getChildren().add(new ProgressBarMissing(this, 4));
        else
            progressBar.getChildren().add(new ProgressBar(this, 3));
    }

    public void updateDetails() {
        Customer customer = Model.getInstance().getCustomer();
        CreditCard card = Model.getInstance().getCreditCard();

        fullName.setText(customer.getFirstName() + " " + customer.getLastName());
        address.setText(customer.getAddress());
        cityPostNum.setText(customer.getPostCode() + " " + customer.getPostAddress());

        cardName.setText(card.getHoldersName());
        cardNumber.setText(card.getCardNumber());
        cardValidDateCVC.setText(card.getValidMonth() + "/" + card.getValidYear() + "\t" + card.getVerificationCode());
    }

    @FXML private void changeDetails(Event event) {
        MyInfo myInfo = new MyInfo(this);
        myInfo.updateMyInfo();
        detailsPane.getChildren().add(myInfo);
        myInfo.setLayoutX(60);
        myInfo.setLayoutY(55);
        detailsPane.toFront();
    }

    // ----------- Methods for completing purchase -----------
    @Override
    public void next() {
        Model model = Model.getInstance();
        model.placeOrder();
        endMessage.setText("Tack " + model.getCustomer().getFirstName() + ", för att du har handlat hos oss. Välkommen åter!");
        purchaseCompletePane.toFront();
    }

    @FXML private void backToStart(Event event) {
        try {
            Stage stage = (Stage) infoPane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("resources/views/iMatMini.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void back() {
        parentProcess.deliveryStep();
    }


    // ----------- Methods for infoPane -----------
    @Override
    public void closeInfo() {
        infoPane.getChildren().clear();
        infoPane.toBack();
    }

    @Override
    public void onInfo(Event event) {
        InfoHelp infoHelp = new InfoHelp(this);
        infoPane.getChildren().add(infoHelp);
        infoHelp.setLayoutX(-250);
        infoHelp.setLayoutY(0);
        infoPane.toFront();
    }
}
