package at.az;

import at.az.ggt.EuklidGGT;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Utils {

    private static final BigInteger CANCEL_AFTER_X_STEPS = new BigInteger("1000");

    public static BigInteger getPhiN(BigInteger p, BigInteger q){
        return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    }

    public static BigInteger deriveE(BigInteger phiN, boolean debug) throws NoSuchAlgorithmException {
        int bitLength = phiN.bitLength();
        SecureRandom random = SecureRandom.getInstanceStrong();

        BigInteger ggT = BigInteger.ZERO;
        BigInteger probableE = null;

        long tries = 0;
        while(!ggT.equals(BigInteger.ONE)) {
            tries++;

            probableE = new BigInteger(bitLength, random);

            EuklidGGT euklidGGT = new EuklidGGT(probableE, phiN, CANCEL_AFTER_X_STEPS);
            ggT = euklidGGT.calcGGT(new ArrayList<>());

            if (debug){
                System.out.printf(">> DEBUG: ggt(%s,%s)=%s%n", probableE, phiN, ggT);
            }
        }

        if (debug){
            System.out.printf(">> DEBUG: Tries taken to find a random e: %d%n", tries);
        }

        return probableE;
    }
}
