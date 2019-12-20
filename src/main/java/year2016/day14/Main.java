package year2016.day14;

import base.Base;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Main extends Base {
    private static final String INPUT = "jlmsuwbz";

    private static Map<Long, String> indexes = new HashMap<>();

    private static MessageDigest md;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.part1();
    }

    @Override
    public void part1() throws IOException {
        try {
            int counter = 0;
            long sixtyFourthIndex = 0;
            md = MessageDigest.getInstance("MD5");

            for (long i = 0; counter < 64; i++) {
                String hash = findHash(i);
                Character sequenceChar = hashContainsSequence(hash, 3, null);
                if (sequenceChar != null) {
                    for (int j = 1; j <= 1000; j++) {
                        String fiveHash = findHash(i+j);

                        Character fiveSequenceChar = hashContainsSequence(fiveHash,5, sequenceChar);
                        if (fiveSequenceChar != null) {
                            indexes.put(i, hash);
                            counter++;
                            sixtyFourthIndex = i;
                            break;
                        }
                    }
                }
            }

            System.out.println(sixtyFourthIndex);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private String findHash(long i) {
        String hash;
        md.update((INPUT + i).getBytes());
        byte[] digest = md.digest();
        hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

        for (int x = 0; x < 2016; x++) {
            md.update((hash).getBytes());
            digest = md.digest();
            hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
        }

        return hash;
    }



    @Override
    public void part2() throws IOException {

    }
}
