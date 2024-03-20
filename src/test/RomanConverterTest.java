package test;

import Application.RomanConverter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RomanConverterTest {

    private RomanConverter testConverter = new RomanConverter();

    ////////////////TEST PARSER//////////////////////////////
    @org.junit.jupiter.api.Test
    void testParseOneDigit() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        result.add("1");
        String input = "1";
        assertEquals(testConverter.parse(input), result);
    }

    @org.junit.jupiter.api.Test
    void testParseTwoDigit() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        result.add("90");
        result.add("2");
        String input = "92";
        assertEquals(testConverter.parse(input), result);
    }

    @org.junit.jupiter.api.Test
    void testParseThreeDigit() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        result.add("100");
        result.add("20");
        result.add("9");
        String input = "129";
        assertEquals(testConverter.parse(input), result);
    }

    @org.junit.jupiter.api.Test
    void testParseFourDigit() throws Exception {
        ArrayList<String> result = new ArrayList<>();
        result.add("4000");
        result.add("500");
        result.add("70");
        result.add("3");
        String input = "4573";
        assertEquals(testConverter.parse(input), result);
    }



    ///////////////////////TEST FIVE////////////////////////////////
    @org.junit.jupiter.api.Test
    void convertFive() throws Exception {
        String input = "5";
        String result = "V";
        assertEquals(result, testConverter.convertFull(input));
    }
    @org.junit.jupiter.api.Test
    void convertFifty() throws Exception {
        String input = "50";
        String result = "L";
        assertEquals(result, testConverter.convertFull(input));
    }
    @org.junit.jupiter.api.Test
    void convertFiveHundred() throws Exception {
        String input = "500";
        String result = "D";
        assertEquals(result, testConverter.convertFull(input));
    }


    ///////////////////TEST REPEATED VALUES///////////////////////
    @org.junit.jupiter.api.Test
    void convertOne() throws Exception {
        String input = "1";
        String result = "I";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertTwenty() throws Exception {
        String input = "20";
        String result = "XX";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertThreeHundred() throws Exception {
        String input = "300";
        String result = "CCC";
        assertEquals(result, testConverter.convertFull(input));
    }

    ///////// TEST NINE///////////////////////
    @org.junit.jupiter.api.Test
    void convertNINE() throws Exception {
        String input = "9";
        String result = "IX";
        assertEquals(result, testConverter.convertFull(input));
    }
    @org.junit.jupiter.api.Test
    void convertNINTY() throws Exception {
        String input = "90";
        String result = "XC";
        assertEquals(result, testConverter.convertFull(input));
    }
    @org.junit.jupiter.api.Test
    void convertNineHundred() throws Exception {
        String input = "900";
        String result = "CM";
        assertEquals(result, testConverter.convertFull(input));
    }

    ///////// TEST VALUES FOUR TO EIGHT///////////////////////
    @org.junit.jupiter.api.Test
    void convertFOUR() throws Exception {
        String input = "4";
        String result = "IV";
        assertEquals(result, testConverter.convertFull(input));
    }
    @org.junit.jupiter.api.Test
    void convertSIX() throws Exception {
        String input = "6";
        String result = "VI";
        assertEquals(result, testConverter.convertFull(input));
    }
    @org.junit.jupiter.api.Test
    void convertSEVEN() throws Exception {
        String input = "7";
        String result = "VII";
        assertEquals(result, testConverter.convertFull(input));
    }
    @org.junit.jupiter.api.Test
    void convertEIGHT() throws Exception {
        String input = "8";
        String result = "VIII";
        assertEquals(result, testConverter.convertFull(input));
    }


    ///////////////TEST FULL NUMBER///////////
    @org.junit.jupiter.api.Test
    void convertExampleFourRepeated() throws Exception {
        String input = "3333";
        String result = "MMMCCCXXXIII";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertExampleFourDIFFERENT() throws Exception {
        String input = "3495";
        String result = "MMMCDXCV";
        assertEquals(result, testConverter.convertFull(input));
    }

    @org.junit.jupiter.api.Test
    void convertExampleFourWithZero() throws Exception {
        String input = "1001";
        String result = "MI";
        assertEquals(result, testConverter.convertFull(input));
    }

    /////////////TEST INVALID INPUT////////////////////////////
    @org.junit.jupiter.api.Test
    void convertNegativeValue() {
        String input = "-1001";
        assertFalse(testConverter.isInputValid(input));
    }

    @org.junit.jupiter.api.Test
    void convertZeroStartValue() {
        String input = "001001";
        assertFalse(testConverter.isInputValid(input));
    }
}