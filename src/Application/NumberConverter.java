package Application;

import java.util.ArrayList;

public class NumberConverter extends AbstractConverter {

    @Override
    public boolean isInputValid(String input) {
        String regex = "^[IVXLCDM]*$";
        return input.matches(regex);
    }

    /**
     * The parse method is designed to transform a string representation of a ROMAN value into an array of strings.
     * Each String should consist of ROMAN VALUE that can be converted into values of only a single magnitude
     *
     * @param input A string that represents the arabic numerical value to be parsed.
     * @return An array of strings, each element representing a segment of the original number
     * of the same magnitude.
     */
    @Override
    public ArrayList<String> parse(String input) throws Exception {
        ArrayList<String> result = new ArrayList<>();
        int expectedMaxRomanVal = MAP_ROMAN_CHAR_TO_VALUE.get(input.charAt(0));
        int expectedMagnitude = MAP_ROMAN_CHAR_TO_MAGNITUDE.get(input.charAt(0));
        int curCharPos = 0;
        int curStrStartPos = curCharPos; // mark the start of the substring


        while (curCharPos < input.length()) {
            // within each magnitude, there can only be at max one decrase in magnitude
            char curChar = input.charAt(curCharPos);
            int curVal = MAP_ROMAN_CHAR_TO_VALUE.get(curChar);
            int curMag = MAP_ROMAN_CHAR_TO_MAGNITUDE.get(curChar);
            if (curStrStartPos < curCharPos && curMag < expectedMagnitude) {
                // means the start of next magnitude
                // record the substring
                result.add(input.substring(curStrStartPos, curCharPos));
                curStrStartPos = curCharPos; // start of next substring
                expectedMagnitude = curMag;
                expectedMaxRomanVal = curVal;
            }else if (curVal > expectedMaxRomanVal){
                // only one case where same mag and expectVal increase, need to end the substring now
                result.add(input.substring(curStrStartPos, curCharPos+1));
                curStrStartPos = curCharPos+1; // cur Char is included

                if (curCharPos+1 >= input.length()) break;

                expectedMagnitude = MAP_ROMAN_CHAR_TO_MAGNITUDE.get(input.charAt(curCharPos+1));
                expectedMaxRomanVal = MAP_ROMAN_CHAR_TO_VALUE.get(input.charAt(curCharPos+1));

            }
            curCharPos++;
        }
        if (curStrStartPos < curCharPos) {
            // in case got edge cases
            result.add(input.substring(curStrStartPos, curCharPos));
        }
        return result;
    }

    @Override
    public String convertSameMagnitude(String input) throws Exception {
        if (input.length() > 4) throw new Exception("current input way too long: " + input);
        if (input.isEmpty()) return "";
        int repeatCount = 0;
        int result = MAP_ROMAN_CHAR_TO_VALUE.get(input.charAt(0));
        boolean canValIncrease = true;
        for (int i = 1; i < input.length(); i++) {
            int prevVal = MAP_ROMAN_CHAR_TO_VALUE.get(input.charAt(i-1));
            int curVal = MAP_ROMAN_CHAR_TO_VALUE.get(input.charAt(i));
            if (curVal > prevVal) {
                if (!canValIncrease) throw new Exception("INVALID SYNTAX: " + input);
                result = curVal - prevVal;
            } else {
                result += curVal;
                canValIncrease = false;
            }
            if (curVal == prevVal) {
                // only one valued Char at each magnitude can repeat
                if (!REPEATABLE_ROMAN.contains(input.charAt(i))) throw new Exception("Cannot repeat: " +
                        input.charAt(i) + "in current input: "+ input);
                repeatCount++;
                // cannot have more than 3 consecutive repeats
                if (repeatCount > 2) throw new Exception("TWO MANY REPEATS: " + input);
            }
        }
        return String.valueOf(result);
    }

    @Override
    public String combineConvertedString(ArrayList<String> convertedInput) throws Exception {
        int result = 0;
        for (String curInput : convertedInput) {
            result += Integer.parseInt(curInput);
        }
        return String.valueOf(result);
    }
}
