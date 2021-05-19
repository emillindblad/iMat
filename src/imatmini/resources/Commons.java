package imatmini.resources;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class Commons {
    public static void setTextLimit(TextField textField, int length) {
        textField.setOnKeyTyped(event -> {
            String string = textField.getText();

            if (string.length() > length) {
                textField.setText(string.substring(0, length));
                textField.positionCaret(string.length());
                System.out.println("Done");
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
}
