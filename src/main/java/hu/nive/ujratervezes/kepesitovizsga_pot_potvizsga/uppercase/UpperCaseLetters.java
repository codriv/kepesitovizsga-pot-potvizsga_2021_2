package hu.nive.ujratervezes.kepesitovizsga_pot_potvizsga.uppercase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class UpperCaseLetters {

    public int getNumberOfUpperCase(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);
            return getNumber(lines);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read File!");
        }
    }

    private int getNumber(List<String> lines) {
        int number = 0;
        for (String line: lines) {
            number += getNumberPerLine(line);
        }
        return number;
    }

    private int getNumberPerLine(String line) {
        int number = 0;
        for (Character character: line.toCharArray()) {
            if (Character.isUpperCase(character)) {
                number++;
            }
        }
        return number;
    }


}
