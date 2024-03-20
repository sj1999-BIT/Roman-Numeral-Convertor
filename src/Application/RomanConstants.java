package Application;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class RomanConstants {
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
}
