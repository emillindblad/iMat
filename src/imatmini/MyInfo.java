package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
    @FXML private TextField cardMonth;
    @FXML private TextField cardYear;
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
        model.setCardMonth(cardMonth.getText());
        model.setCardYear(cardYear.getText());
        model.setCVC(cardCVC.getText());

        savedPane.toFront();
    }

    @FXML
    public void onClose(Event event) {
        this.toBack();
    }
}
