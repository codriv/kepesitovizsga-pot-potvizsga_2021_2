package hu.nive.ujratervezes.kepesitovizsga_pot_potvizsga.numberofdigits;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumberOfDigitsTest {

    @Test
    public void testGetNumberOfDigits() {
        Assertions.assertEquals(13, new NumberOfDigits().getNumberOfDigits(11));
    }

    @Test
    public void testGetNumberOfDigits2() {
        Assertions.assertEquals(261, new NumberOfDigits().getNumberOfDigits(123));
    }

    @Test
    public void testGetNumberOfDigits3() {
        Assertions.assertEquals(3829, new NumberOfDigits().getNumberOfDigits(1234));
    }
}
