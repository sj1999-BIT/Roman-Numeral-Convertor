package Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public abstract class AbstractConverter implements ConverterInterface{




    // standardize for both converters to follow
    public String convertFull(String input) throws Exception {
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



}
