package at.az.ggt.main;

import at.az.ggt.algo.Utils;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Calculate RSA keys without external input.
 */
public class MainCalcRSAAdvanced {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        long tBegin0 = System.currentTimeMillis();

        // Call this, in order to get the RNG initialized and measure times more accurately below.
        @SuppressWarnings("unused")
        BigInteger dummyBigInteger = BigInteger.probablePrime(128, new SecureRandom());

        long tBegin = System.currentTimeMillis();

        BigInteger p = BigInteger.probablePrime(128, new SecureRandom());
        long t1 = System.currentTimeMillis();
        BigInteger q = BigInteger.probablePrime(128, new SecureRandom());
        long t2 = System.currentTimeMillis();
        BigInteger phiN = Utils.getPhiN(p, q);

        long t3 = System.currentTimeMillis();
        BigInteger e = Utils.deriveE(phiN, true);
        long tEnd = System.currentTimeMillis();

        System.out.println();
        System.out.println("Statistics:");
        System.out.println("-----------");
        System.out.printf("Finding p took %dms%n", (t1-tBegin));
        System.out.printf("Finding q took %dms%n", (t2-t1));
        System.out.printf("Finding e took %dms%n", (tEnd-t3));
        System.out.printf("Total time elapsed for key generation (without RNG initialization: %dms%n", (tEnd-tBegin));
        System.out.printf("Total time elapsed for key generation (including RNG initialization): %dms%n", (tEnd-tBegin0 - ((t1-tBegin + t2-t1)/2) ));
        System.out.println();
        System.out.println();

        MainCalcRSASimple.main(new String[]{p.toString(), q.toString(), e.toString()});
    }
}
