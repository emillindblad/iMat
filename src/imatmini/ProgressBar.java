package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;

public class ProgressBar extends AnchorPane {
    @FXML private Circle first;
    @FXML private Circle second;
    @FXML private Circle third;

    @FXML private Label one;
    @FXML private Label two;
    @FXML private Label three;

    @FXML private Line firstLine;
    @FXML private Line secondLine;

    private final PurchaseStep parentController;

    public ProgressBar(PurchaseStep parentController, int step) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/progress_bar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setColors(step);

        this.parentController = parentController;
    }

    @FXML
    private void onInfo(Event event) {
        parentController.onInfo(event);
    }

    private void setColors(int step) {
        switch (step) {
            case 1 -> firstStep();
            case 2 -> secondStep();
            case 3 -> thirdStep();
            default -> System.out.println("You've fucked up.");
        }
    }

    private void firstStep() {
        setCircleActive(first);
        setCircleUncompleted(second);
        setCircleUncompleted(third);

        setLineUncompleted(firstLine);
        setLineUncompleted(secondLine);
    }

    private void secondStep() {
        setCircleCompleted(first);
        setCircleActive(second);
        setCircleUncompleted(third);

        setLineCompleted(firstLine);
        setLineUncompleted(secondLine);

        setNumberCompleted(one);
    }

    private void thirdStep() {
        setCircleCompleted(first);
        setCircleCompleted(second);
        setCircleActive(third);

        setLineCompleted(firstLine);
        setLineCompleted(secondLine);

        setNumberCompleted(one);
        setNumberCompleted(two);
    }

    private void setCircleActive(Circle circle) {
        circle.setStyle("-fx-stroke: #AD282F; -fx-stroke-width: 5px; -fx-fill: #FFFFFF");
    }

    private void setCircleCompleted(Circle circle) {
        circle.setStyle("-fx-fill: #AD282F");
    }

    private void setCircleUncompleted(Circle circle) {
        circle.setStyle("-fx-stroke: #646464; -fx-stroke-width: 5px; -fx-fill: #A5A5A5");
    }

    private void setLineCompleted(Line line) {
        line.setStyle("-fx-fill: #AD282F");
    }

    private void setLineUncompleted(Line line) {
        line.setStyle("-fx-fill: #FFFFFF");
    }

    private void setNumberCompleted(Label number) {
        number.setStyle("-fx-text-fill: #FFFFFF");
    }
}
