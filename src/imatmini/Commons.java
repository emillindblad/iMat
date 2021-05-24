package imatmini;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.text.DecimalFormat;

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

    public static String getCategoryNameInSwedish(ProductCategory category){
        String name;
        switch (category){
            case POD -> name = "Baljväxter";
            case FISH -> name = "Fisk";
            case HERB -> name = "Örter";
            case MEAT -> name = "Kött";
            case BERRY -> name = "Bär";
            case BREAD -> name = "Bröd";
            case FRUIT -> name = "Frukt";
            case PASTA -> name = "Pasta";
            case SWEET -> name = "Sötsaker";
            case MELONS -> name = "Meloner";
            case CABBAGE -> name = "Kål";
            case DAIRIES -> name = "Mejeri";
            case HOT_DRINKS -> name = "Varma drycker";
            case COLD_DRINKS -> name = "Kalla drycker";
            case POTATO_RICE -> name = "Potatis & Ris";
            case CITRUS_FRUIT -> name = "Citrusfrukter";
            case EXOTIC_FRUIT -> name = "Exotiska frukter";
            case NUTS_AND_SEEDS -> name = "Nötter & Frön";
            case ROOT_VEGETABLE -> name = "Rotfrukter";
            case VEGETABLE_FRUIT -> name = "Grönsaker";
            case FLOUR_SUGAR_SALT -> name = "Skafferi";
            default -> name = "unknown";
        }
        return name;
    }

    public static String getCorrectDecimalFormat(double number){
        return new DecimalFormat("#.##").format(number);
    }
}
