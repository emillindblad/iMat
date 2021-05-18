package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;

public class DeliveryStep extends AnchorPane implements PurchaseStep {

    @FXML private AnchorPane infoPane;
    @FXML private FlowPane flowPane;
    @FXML private AnchorPane progressBar;
    @FXML
    private Label timeLabel;

    private TimeSlot selectedTimeSlot;

    private final PurchaseProcess parentProcess;

    public DeliveryStep(boolean detailsMissing, PurchaseProcess parentProcess) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/delivery_step.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        addProgressBar(detailsMissing);
        this.timeLabel.setText("Välj en tid ovan");
        this.parentProcess = parentProcess;
        setupTimeSlots();
    }

    private void addProgressBar(boolean detailsMissing) {
        if (detailsMissing)
            progressBar.getChildren().add(new ProgressBarMissing(this, 3));
        else
            progressBar.getChildren().add(new ProgressBar(this, 2));
    }

    @Override
    public void next() {
        if(selectedTimeSlot == null){
            timeLabel.setText("Du måste välja en tid!");
            return;
        }
        parentProcess.finalStep();
    }

    @Override
    public void back() {
        parentProcess.firstOrSecondStep();
    }

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
    
    private void setupTimeSlots(){
        int h = 7; // Start hour

        for (int i = 0; i < 7; i++) { // Add a timeslot for each week
            for (int j = 0; j < 7; j++) { // Timeslot for each day
                TimeSlot newSlot = new TimeSlot(String.format("%02d:00", h+(2*i), h+2+(2*i)), this);
                flowPane.getChildren().add(newSlot);
            }
        }
    }

    public void chooseTime(String time, TimeSlot timeSlot)
    {
        if(selectedTimeSlot == timeSlot)
            removeCurrentTime();

        timeLabel.setText("Du har nu valt klockan " + time + "!");

        removeCurrentTime();
        selectedTimeSlot = timeSlot;
    }

    private void removeCurrentTime(){
        if(selectedTimeSlot == null)
            return;
        selectedTimeSlot.deselectTime();
    }
}
