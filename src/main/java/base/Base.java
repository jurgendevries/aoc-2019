package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class Base {
    public BufferedReader input;

    public void mainMethod(String inputFile) throws IOException {
        File file = new File("D:\\projects\\adventofcode\\src\\main\\resources\\"+ inputFile);
        input = new BufferedReader(new FileReader(file));
    }

    public abstract void part1() throws IOException;
    public abstract void part2() throws IOException;

    public Character hashContainsSequence(String hash, int numberOfMatches, Character matchingChar) {
        for (int i = 0; i < hash.length() - numberOfMatches + 1; i++) {
            for (int j = 1; j < numberOfMatches; j++) {
                if (hash.charAt(i) == hash.charAt(i+j)) {
                    if (j == numberOfMatches - 1) {
                        if (matchingChar == null || matchingChar == hash.charAt(i)) {
                            return hash.charAt(i);
                        }
                    }
                } else {
                    break;
                }
            }

        }
        return null;
    }

    public Character hashContainsSequenceOf2Unique(String hash, int numberOfMatches) {
        for (int i = 0; i <= hash.length() - numberOfMatches; i++) {
            for (int j = 1; j < numberOfMatches; j++) {
                if (hash.charAt(i) == hash.charAt(i+j)) {
                    if (j == numberOfMatches - 1) {
                        if (i < hash.length() - numberOfMatches) {
                            if (i != 0) {
                                if (hash.charAt(i) != hash.charAt(i + j + 1) && hash.charAt(i) != hash.charAt(i - 1)) {
                                    return hash.charAt(i);
                                }
                            } else {
                                if (hash.charAt(i) != hash.charAt(i + j + 1)) {
                                    return hash.charAt(i);
                                }
                            }
                        } else {
                            if (hash.charAt(i) != hash.charAt(i - 1)) {
                                return hash.charAt(i);
                            }
                        }
                    }
                } else {
                    break;
                }
            }

        }
        return null;
    }
}
