package test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import Application.NumberConverter;

class NumberConverterTest {
    private final NumberConverter testConverter = new NumberConverter();
    ///////////////TEST PARSER//////////////////////////////
    @org.junit.jupiter.api.Test
    void testParseOneROMAN() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        result.add("I");
        String input = "I";
        assertEquals(testConverter.parse(input), result);
    }

    @org.junit.jupiter.api.Test
    void testParseTwoRepeatROMAN() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        result.add("II");
        String input = "II";
        assertEquals(testConverter.parse(input), result);
    }

    @org.junit.jupiter.api.Test
    void testParseThreeRepeatROMAN() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        result.add("III");
        String input = "III";
        assertEquals(testConverter.parse(input), result);
    }

    @org.junit.jupiter.api.Test
    void testParseFourVal() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        result.add("XL");
        result.add("V");
        String input = "XLV";
        assertEquals(testConverter.parse(input), result);
    }

    @org.junit.jupiter.api.Test
    void testParseNineVal() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        result.add("CM");
        result.add("XXX");
        result.add("IV");
        String input = "CMXXXIV";
        assertEquals(testConverter.parse(input), result);
    }

    @org.junit.jupiter.api.Test
    void testParseEightVal() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        result.add("MMM");
        result.add("DCCC");
        result.add("LXXX");
        result.add("III");
        String input = "MMMDCCCLXXXIII";
        assertEquals(testConverter.parse(input), result);
    }

    @org.junit.jupiter.api.Test
    void testParseEightAndNineVal() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        result.add("MMM");
        result.add("DCCC");
        result.add("XC");
        result.add("VIII");
        String input = "MMMDCCCXCVIII";
        assertEquals(testConverter.parse(input), result);
    }

    ///////////////////////TEST FOUR, EIGHT, NINE////////////////////////////////
    @org.junit.jupiter.api.Test
    void convertFour() throws Exception {
        String input = "IV";
        String result = "4";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertNine() throws Exception {
        String input = "XCIX";
        String result = "99";
        assertEquals(result, testConverter.convertFull(input));
    }

    ///////////////////////TEST LONG VALUES////////////////////////////////
    @org.junit.jupiter.api.Test
    void convertSIX() throws Exception {
        String input = "DCLXVI";
        String result = "666";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertSEVEN() throws Exception {
        String input = "DCCLXXVII";
        String result = "777";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertEIGHT() throws Exception {
        String input = "DCCCLXXXVIII";
        String result = "888";
        assertEquals(result, testConverter.convertFull(input));
    }

    ///////////////TEST FULL NUMBER///////////
    @org.junit.jupiter.api.Test
    void convertExample2024() throws Exception {
        String input = "MMXXIV";
        String result = "2024";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertExample800() throws Exception {
        String input = "DCCC";
        String result = "800";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertExample1000() throws Exception {
        String input = "M";
        String result = "1000";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertExample12() throws Exception {
        String input = "XII";
        String result = "12";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertExample21() throws Exception {
        String input = "XXI";
        String result = "21";
        assertEquals(result, testConverter.convertFull(input));
    }

    /////////////TEST INVALID INPUT AT PASRSING////////////////////////////
    @org.junit.jupiter.api.Test
    void parseWrongLetter() {
        String input = "XXXM9";
        assertFalse(testConverter.isInputValid(input));
    }

    @org.junit.jupiter.api.Test
    void parseWrongLetter2() {
        String input = "&&&";
        assertFalse(testConverter.isInputValid(input));
    }

    @org.junit.jupiter.api.Test
    void convertWrongRepeat() throws Exception {
        String input = "VV";
        try {
            testConverter.convertFull(input);
            assertFalse(testConverter.isInputValid(input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }

    /////////////TEST INVALID INPUT AFTER PARSING////////////////////////////
    @org.junit.jupiter.api.Test
    void convertFOURREPEATS() {
        String input = "XXXX";
        try {
            testConverter.convertFull(input);
            assertFalse(testConverter.isInputValid(input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }
    /////////////TEST FAILED CASES FROM FULL TESTING////////////////////////////
    @org.junit.jupiter.api.Test
    void convertCDI() throws Exception {
        String input = "CDI";
        String result = "401";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertCDII() throws Exception {
        String input = "CDII";
        String result = "402";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertWrongIncrMag() throws Exception {
        String input = "ID";
        String result = "499";
        assertEquals(result, testConverter.convertFull(input));
    }
}