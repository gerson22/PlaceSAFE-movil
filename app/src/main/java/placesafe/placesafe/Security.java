package placesafe.placesafe;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Wihar on 01/12/2016.
 */

public class Security {

    private static Security mInstance;

    private static void makeSingleton(){
        if (mInstance == null) {
            mInstance = new Security();
        }
    }

    public static String encriptTo_md5(String input) {
        makeSingleton();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String md5 = number.toString(16);

            while (md5.length() < 32)
                md5 = "0" + md5;
            return md5;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

}
