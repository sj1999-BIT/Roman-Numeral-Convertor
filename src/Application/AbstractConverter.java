package Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public abstract class AbstractConverter implements ConverterInterface{

    public static final char[] ROMAN_CHAR_LIST = {'I','V', 'X', 'L', 'C', 'D', 'M'};

    public static final HashMap<Character, Integer> MAP_ROMAN_CHAR_TO_MAGNITUDE = new HashMap<Character, Integer>() {{
        put('I', 0);
        put('V', 0);
        put('X', 1);
        put('L', 1);
        put('C', 2);
        put('D', 2);
        put('M', 3);
    }};

    public static final HashMap<Character, Integer> MAP_ROMAN_CHAR_TO_VALUE = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public static final HashSet<Character> REPEATABLE_ROMAN = new HashSet<Character>(Arrays.asList('I','X','C','M')) ;


    // standardize for both converters to follow
    // pipeline to convert
    public String convertFull(String input) throws Exception {
        if (!this.isInputValid(input)) throw new Exception("Invalid input");
        ArrayList<String> inputList  = this.parse(input);
        for (int i = 0; i < inputList.size(); i++) {
            // convert each input into correct value
            inputList.set(i, this.convertSameMagnitude(inputList.get(i)));
        }
        return this.combineConvertedString(inputList);
    }

    // create a string of repeated x of length n. If n < 0, return empty string
    public String repeatChar(char x, int n) {
        if (n < 0) return "";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(x);
        }
        return sb.toString();
    }

    // returns the Rome one value representing one at current magnitude
    // e.g. magnitude = 2, return 4, which in ROMAN_CHAR_LIST is C, represent 100 (magnitude 2)
    public int getMagnitudeOneRomeIndex(int magnitude) {
        return magnitude * 2;
    }

}
