package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class TimeSlot extends Pane {
    @FXML
    private Label timeLabel;
    @FXML private Pane selectPane;
    private DeliveryStep parent;
    public TimeSlot(String time, DeliveryStep parent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/Time_Slot.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        timeLabel.setText(time);
        selectPane.setOpacity(0);
        this.parent = parent;
    }

    @FXML
    private void onChooseTime(Event event){
        parent.chooseTime(timeLabel.getText(), this);
        selectPane.setOpacity(1);
    }

    public void deselectTime(){
    selectPane.setOpacity(0);
    }
}
