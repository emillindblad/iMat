package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;

import java.io.IOException;

public class MyInfo extends AnchorPane {
    @FXML private TextField personNum;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField address;
    @FXML private TextField postNum;
    @FXML private TextField city;
    @FXML private TextField teleNum;

    @FXML private TextField cardName;
    @FXML private TextField cardNum;
    @FXML private ComboBox<Integer> cardMonth;
    @FXML private ComboBox<Integer> cardYear;
    @FXML private TextField cardCVC;

    @FXML private Button getInfoButton;
    @FXML private Button backButton;
    @FXML private Button saveButton;

    @FXML private AnchorPane savedPane;
    @FXML private Button okButton;

    private final Model model = Model.getInstance();
    private final iMatMiniController parentController;

    public MyInfo(iMatMiniController parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myInfo.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;

        cardMonth.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        cardYear.getItems().addAll(12);
    }

    @FXML
    protected void onSave(Event event){
        model.setFirstName(firstName.getText());
        model.setLastName(lastName.getText());
        model.setAddress(address.getText());
        model.setPostCode(postNum.getText());
        model.setCity(city.getText());
        model.setTeleNum(teleNum.getText());

        model.setCardName(cardName.getText());
        model.setCardNumber(cardNum.getText());
        //model.setCardMonth(cardMonth.get());
        //model.setCardYear(cardYear.getText());
        model.setCVC(cardCVC.getText());

        this.savedPane.getChildren().add(new SaveConf(this));
        savedPane.toFront();
    }

    public void updateMyInfo() {
        updateCardInfo();
        updateContactInfo();
    }

    private void updateCardInfo() {
        CreditCard card = model.getCreditCard();

        cardNum.setText(card.getCardNumber());
        cardName.setText(card.getHoldersName());

        //cardMonth.setText("" + card.getValidMonth());
        //cardYear.setText("" + card.getValidYear());
        //yearCombo.getSelectionModel().select(""+card.getValidYear());

        cardCVC.setText(""+card.getVerificationCode());
    }

    private void updateContactInfo(){
        Customer customer = model.getCustomer();

        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        address.setText(customer.getAddress());
        postNum.setText(customer.getPostCode());
        city.setText(customer.getPostCode());
        teleNum.setText(customer.getPhoneNumber());
    }

    @FXML
    public void onClose(Event event) {
        savedPane.getChildren().clear();
        savedPane.toBack();
        this.getParent().toBack();
    }
}
