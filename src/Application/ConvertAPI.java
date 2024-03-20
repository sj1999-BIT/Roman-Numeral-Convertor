package Application;

import Application.AbstractConverter;
import Application.NumberConverter;
import Application.RomanConverter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ConvertAPI {
    private static AbstractConverter romanConverter = new RomanConverter();
    private static AbstractConverter numberConverter = new NumberConverter();

    public static String convert(String userInput) throws Exception {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        AbstractConverter[] converters = {romanConverter, numberConverter};
        String result = "INVALID";
        for (AbstractConverter converter : converters) {
            if (!converter.isInputValid(userInput)) continue;
            result = converter.convertFull(userInput);
        }

        return result;
    }
}