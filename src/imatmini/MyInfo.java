package imatmini;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.Customer;

import java.io.IOException;

public class MyInfo extends AnchorPane implements Info {
    @FXML private TextField personNum;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField address;
    @FXML private TextField postNum;
    @FXML private TextField city;
    @FXML private TextField phoneNumber;

    @FXML private TextField cardName;
    @FXML private TextField cardNum;
    @FXML private ComboBox<Integer> cardMonth;
    @FXML private ComboBox<Integer> cardYear;
    @FXML private TextField cardCVC;

    @FXML private Button getInfoButton;
    @FXML private Button backButton;
    @FXML private Button saveButton;

    @FXML private AnchorPane savedPane;
    @FXML private AnchorPane infoPane;

    private final Model model = Model.getInstance();
    private final iMatMiniController parentController;

    public MyInfo(iMatMiniController parentController){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/myInfo.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;

        cardMonth.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
        cardMonth.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                model.getCreditCard().setValidMonth(newValue);
            }
        });
        cardYear.getItems().addAll(2021,2022,2023,2024,2025);
        cardYear.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                model.getCreditCard().setValidYear(newValue);
            }
        });

        cardName.setFont(Font.font("System", FontWeight.NORMAL, 25));
        cardNum.setFont(Font.font("System", FontWeight.NORMAL, 25));
        cardCVC.setFont(Font.font("System", FontWeight.NORMAL, 25));

        personNum.setFont(Font.font("System", FontWeight.NORMAL, 25));
        firstName.setFont(Font.font("System", FontWeight.NORMAL, 25));
        lastName.setFont(Font.font("System", FontWeight.NORMAL, 25));
        address.setFont(Font.font("System", FontWeight.NORMAL, 25));
        postNum.setFont(Font.font("System", FontWeight.NORMAL, 25));
        city.setFont(Font.font("System", FontWeight.NORMAL, 25));
        phoneNumber.setFont(Font.font("System", FontWeight.NORMAL, 25));

        cardMonth.setButtonCell(new ListCell(){

            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if(empty || item==null){
                    setStyle("-fx-font-size:25");
                } else {
                    setStyle("-fx-font-size:25");
                    setText(item.toString());
                }
            }

        });
        cardYear.setButtonCell(new ListCell(){

            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                if(empty || item==null){
                    setStyle("-fx-font-size:25");
                } else {
                    setStyle("-fx-font-size:25");
                    setText(item.toString());
                }
            }

        });
    }

    @FXML
    private void onSave(Event event){
        Customer customer = model.getCustomer();
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setAddress(address.getText());
        customer.setPostCode(postNum.getText());
        customer.setPostAddress(city.getText());
        customer.setPhoneNumber(phoneNumber.getText());

        CreditCard card = model.getCreditCard();
        card.setHoldersName(cardName.getText());
        card.setCardNumber(cardNum.getText());
        card.setVerificationCode(Integer.parseInt(cardCVC.getText()));

        SaveConf saveConf = new SaveConf(this);
        this.savedPane.getChildren().add(saveConf);
        saveConf.setLayoutX(60);
        saveConf.setLayoutY(50);
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

        cardMonth.getSelectionModel().select(card.getValidMonth());
        cardYear.getSelectionModel().select(card.getValidYear());

        cardCVC.setText(""+card.getVerificationCode());
    }

    private void updateContactInfo(){
        Customer customer = model.getCustomer();

        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        address.setText(customer.getAddress());
        postNum.setText(customer.getPostCode());
        city.setText(customer.getPostCode());
        phoneNumber.setText(customer.getPhoneNumber());
    }

    @FXML
    public void onClose(Event event) {
        savedPane.getChildren().clear();
        savedPane.toBack();
        this.getParent().toBack();
    }

    @FXML @Override
    public void onInfo(Event event) {
        InfoHelp infoHelp = new InfoHelp(this);
        infoPane.getChildren().add(infoHelp);
        infoHelp.setLayoutX(-250);
        infoHelp.setLayoutY(0);
        infoPane.toFront();
    }

    @Override
    public void closeInfo() {
        infoPane.getChildren().clear();
        infoPane.toBack();
    }

    @FXML private void  mouseTrap(Event event) {event.consume();}
}
