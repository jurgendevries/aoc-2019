package year2016.day5;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    private static final String INPUT = "ojvtpuvg";

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        int counter = 0;

        Character[] sa = new Character[8];

        for (long i = 0; counter < 8; i++) {
            md.update((INPUT + i).getBytes());
            byte[] digest = md.digest();

            String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

            if ("00000".equals(hash.substring(0,5))) {
                int pos = Character.getNumericValue(hash.charAt(5));
                if (pos >= 0 && pos < 8) {
                    if (sa[pos] == null) {
                        sa[pos] = hash.charAt(6);
                        counter++;
                    }
                }
            }
        }

        for (char c : sa) {
            System.out.print(c);
        }

    }
}
