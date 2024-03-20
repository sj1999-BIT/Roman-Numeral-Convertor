package Application;

import Application.AbstractConverter;
import Application.NumberConverter;
import Application.RomanConverter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ConvertAPI {
    private static final AbstractConverter romanConverter = new RomanConverter();
    private static final AbstractConverter numberConverter = new NumberConverter();

    public static String convert(String userInput) throws Exception {
        AbstractConverter[] converters = {romanConverter, numberConverter};
        String result = "INVALID";
        // go through each converter to check if input can be converted
        for (AbstractConverter converter : converters) {
            if (!converter.isInputValid(userInput)) continue;
            result = converter.convertFull(userInput);
        }

        return result;
    }
}