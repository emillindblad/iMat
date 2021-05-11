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

    private final Model model;

    public MyInfo(Model model){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myInfo.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.model = model;
    }

    @FXML
    protected void onSave(Event event){
        firstName.getText();
        lastName.getText();
        address.getText();
        postNum.getText();
        city.getText();
        teleNum.getText();

        cardName.getText();
        cardNum.getText();
        cardMonth.getText();
        cardYear.getText();
        cardCVC.getText();
    }
}
