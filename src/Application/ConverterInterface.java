package Application;

import java.util.ArrayList;

/**
 * First iteration, only values without underline: input integer below 5000
 */
public interface ConverterInterface {

    // check if input is syntax-valid, can still be semantically invalid.
    boolean isInputValid(String input);

    // parse user input into respectively order of magnitude.
    ArrayList<String> parse(String input) throws Exception;

    // takes in a string representing value within the same order of magnitude and convert it
    // to the alternative representation.
    String convertSameMagnitude(String input) throws Exception;

    // given array of converted Strings, combine them into actual value
    String combineConvertedString(ArrayList<String> convertedInput) throws Exception;
}
