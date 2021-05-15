package imatmini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.IOException;

public class ProgressBarMissing extends AnchorPane {
    @FXML private Circle first;
    @FXML private Circle second;
    @FXML private Circle third;
    @FXML private Circle fourth;

    @FXML private Label one;
    @FXML private Label two;
    @FXML private Label three;
    @FXML private Label four;

    @FXML private Line firstLine;
    @FXML private Line secondLine;
    @FXML private Line thirdLine;

    private final PurchaseStep parentController;

    public ProgressBarMissing(PurchaseStep parentController, int step) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/views/progress_bar_missing.fxml"));
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
            case 4 -> fourthStep();
            default -> System.out.println("You've fucked up.");
        }
    }

    private void firstStep() {
        setCircleActive(first);
        setCircleUncompleted(second);
        setCircleUncompleted(third);
        setCircleUncompleted(fourth);

        setLineUncompleted(firstLine);
        setLineUncompleted(secondLine);
        setLineUncompleted(thirdLine);
    }

    private void secondStep() {
        setCircleCompleted(first);
        setCircleActive(second);
        setCircleUncompleted(third);
        setCircleUncompleted(fourth);

        setLineCompleted(firstLine);
        setLineUncompleted(secondLine);
        setLineUncompleted(thirdLine);

        setNumberCompleted(one);
    }

    private void thirdStep() {
        setCircleCompleted(first);
        setCircleCompleted(second);
        setCircleActive(third);
        setCircleUncompleted(fourth);

        setLineCompleted(firstLine);
        setLineCompleted(secondLine);
        setLineUncompleted(thirdLine);

        setNumberCompleted(one);
        setNumberCompleted(two);
    }

    private void fourthStep() {
        setCircleCompleted(first);
        setCircleCompleted(second);
        setCircleCompleted(third);
        setCircleActive(fourth);

        setLineCompleted(firstLine);
        setLineCompleted(secondLine);
        setLineCompleted(thirdLine);

        setNumberCompleted(one);
        setNumberCompleted(two);
        setNumberCompleted(three);
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
        line.setStyle("-fx-stroke: #AD282F");
    }

    private void setLineUncompleted(Line line) {
        line.setStyle("-fx-stroke: #FFFFFF");
    }

    private void setNumberCompleted(Label number) {
        number.setStyle("-fx-text-fill: #FFFFFF");
    }
}
