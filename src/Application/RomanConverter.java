package Application;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class RomanConverter extends AbstractConverter {
    @Override
    public boolean isInputValid(String input) {
        // Check if the string is a valid number and does not start with 0
        return input.matches("[1-9][0-9]*");
    }

    /**
     * The parse method is designed to transform a string representation of a numerical value into an array of strings.
     * Each element in the resultant array represents a constituent part of the original number, segmented according to powers of ten.
     * For instance, given an input of "4444", the output would be an array of ["4000", "400", "40", "4"].
     *
     * @param input A string that represents the arabic numerical value to be parsed.
     * @return An array of strings, each element representing a segment of the original number
     * of the same magnitude.
     */
    @Override
    public ArrayList<String>  parse(String input) {
        ArrayList<String> result = new ArrayList<String>();
        StringBuilder sb;
        for (int i=0; i < input.length(); i++) {
            sb = new StringBuilder();
            sb.append(input.charAt(i));
            for (int j = 0; j < input.length() - i - 1; j++) {
                sb.append("0");
            }
            result.add(sb.toString());
        }
        return result;
    }

    /**
     * Given arabic int value of single magnitude in string, convert to its respective Roman
     * representation.
     * @param input A string that represents the arabic numerical value of same magnitude to be parsed.
     * @return An array of strings, each element representing a segment of the original number
     * of the same magnitude.
     * E.g. "4" -> "IV"
     */
    @Override
    public String convertSameMagnitude(String input) throws Exception {
        int curVal = input.charAt(0) - '0';
        if (curVal == 0) {
            return "";
        } else if (curVal < 0) {
            throw new Exception("current input " + input + " is not a valid number");
        }

        // get the char representing smallest value possible for current magnitude (1, 10, 50, 500)
        int curMagRomanSmallCharIndex = this.getMagnitudeOneRomeIndex(input.length()-1);
        char oneRomeChar;
        try {
            // if curVal is 1 to 3, just repeat one value
            oneRomeChar = ROMAN_CHAR_LIST[curMagRomanSmallCharIndex];
        }catch (Exception e) {
            throw new Exception("Value too large!");
        }

        StringBuilder sb = new StringBuilder();
        if (curVal < 4) {
            return this.repeatChar(oneRomeChar, curVal);
        }

        if (curVal > 8) {
            // for value 9, we need a the tenChar and one char
            char tenRomeChar = ROMAN_CHAR_LIST[curMagRomanSmallCharIndex+2];
            sb.append(oneRomeChar);
            sb.append(tenRomeChar);
            return sb.toString();
        }

        // if curVal is 4 to 8, need both one and five roman value for current magnitude
        char fiveRomeChar = ROMAN_CHAR_LIST[curMagRomanSmallCharIndex+1];

        // if its 4, has a one rome char before the five rome char
        if (curVal == 4) sb.append(oneRomeChar);
        // all
        sb.append(fiveRomeChar);

        int repeatedOnes = curVal - 5;

        sb.append(this.repeatChar(oneRomeChar, repeatedOnes));

        return sb.toString();
//        char largerChar = ROMAN_CHAR_LIST[input.length()];
    }

    @Override
    public String combineConvertedString(ArrayList<String> convertedInput) throws Exception{
        StringBuilder sb = new StringBuilder();
        for (String str : convertedInput) {
            sb.append(str);
        }
        return sb.toString();
    }
}
