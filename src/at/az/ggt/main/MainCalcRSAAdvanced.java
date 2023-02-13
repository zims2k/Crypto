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
        long t1 = System.currentTimeMillis();

        BigInteger p = BigInteger.probablePrime(128, new SecureRandom());
        BigInteger q = BigInteger.probablePrime(128, new SecureRandom());
        BigInteger phiN = Utils.getPhiN(p, q);

        BigInteger e = Utils.deriveE(phiN, true);

        long t2 = System.currentTimeMillis();

        System.out.println();
        System.out.printf("Total time elapsed for key generation: %dms%n", (t2-t1));
        System.out.println();

        MainCalcRSASimple.main(new String[]{p.toString(), q.toString(), e.toString()});
    }
}
