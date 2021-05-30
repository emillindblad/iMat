package imatmini;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import org.w3c.dom.Text;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.function.UnaryOperator;

public class Commons {
    public static void setTextLimit(TextField textField, int length) {
        textField.setOnKeyTyped(event -> {
            String string = textField.getText();

            if (string.length() > length) {
                textField.setText(string.substring(0, length));
                textField.positionCaret(string.length());
            }
        });
    }

    /*
    Called by the credit card textfield to automatically split numbers
     */
    public static void setTextSplitAndLimit(TextField textField, int splitNumber, int limit){
        textField.setOnKeyTyped(event -> {
            int caretPos = textField.getCaretPosition(); // Get caret position
            String input = textField.getText().replaceAll("\\s+","");

            if (input.length() > limit) {
                input = input.substring(0, limit);
            }
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < input.length(); i++) {
                    if (i % splitNumber == 0 && i != 0) {
                        result.append(" ");
                    }
                    result.append(input.charAt(i));
                }
                textField.setText(result.toString());
                textField.positionCaret(caretPos+1); //Put it last

        });
    }

    public static double getOrderTotalCost(Order order){
        double cost = 0;
        for (ShoppingItem item: order.getItems()) {
            cost += item.getTotal();
        }
        return cost;
    }

    public static void allowIntegersOnly(TextField textField) {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        textField.setTextFormatter(new TextFormatter<String>(integerFilter));
    }
    public static String getCorrectDecimalFormat(double number){
        return new DecimalFormat("#.##").format(number);
    }

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static void setEmptyWarning(TextField textField) {
            if (textField.getText().isEmpty()) {
                textField.getStyleClass().add("text-field-error");
            }
    }
}
