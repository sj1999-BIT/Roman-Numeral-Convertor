package test;

import static org.junit.jupiter.api.Assertions.*;

import Application.NumberConverter;
import Application.RomanConverter;

public class FinalConverterTest {
    RomanConverter romanConverter = new RomanConverter();
    NumberConverter numberConverter = new NumberConverter();

    @org.junit.jupiter.api.Test
    public void testStandization() {
        try {
            for (int i = 1; i < 4000; i++) {
                String input = String.valueOf(i);
                System.out.println(romanConverter.convertFull(input));
                assertEquals(input, numberConverter.convertFull(romanConverter.convertFull(input)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
