package hu.nive.ujratervezes.kepesitovizsga_pot_potvizsga.numberofdigits;

public class NumberOfDigits {

    public int getNumberOfDigits(int number) {
        String numberString = String.valueOf(number);
        int length = numberString.length();
        if (length == 1) {
            return number;
        } else {
            return getNumOfDigits(number, length);
        }
    }

    private int getNumOfDigits(int number, int length) {
        double last = (number - Math.pow(10, (length - 1)) + 1) * length;
        int first = 0;
        for (int i = 1; i < length; i++) {
            first += (Math.pow(10, i) - Math.pow(10, (i - 1))) * i;
        }
        return first + (int)last;
    }
}
