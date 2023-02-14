package at.az.gcd.algo;

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
        if (debug){
            System.out.printf(">> DEBUG: Finding an e with 1 < e < phi(N) and gcd(e, phi(n)) = 1, whereby phi(N)=%s%n", phiN);
        }

        int bitLength = phiN.bitLength();
        SecureRandom random = SecureRandom.getInstanceStrong();

        BigInteger gcd = BigInteger.ZERO;
        BigInteger probableE = null;

        long tries = 0;
        while(!gcd.equals(BigInteger.ONE)) {
            tries++;

            probableE = new BigInteger(bitLength, random);
            if (probableE.compareTo(BigInteger.ONE)<=0 || probableE.compareTo(phiN) >= 0){
                if (debug){
                    System.out.printf(">> DEBUG: probable e is 1 or >= phi(N)!! Must find a new e! Whereby e=%s%n", probableE);
                    continue;
                }
            }

            EuklidGCD euklidGCD = new EuklidGCD(probableE, phiN, CANCEL_AFTER_X_STEPS);
            gcd = euklidGCD.calcGCD(new ArrayList<>());

            if (debug){
                System.out.printf(">> DEBUG: gcd(e,phi(N))=%s, whereby e=%s%n", gcd, probableE);
            }
        }

        if (debug){
            System.out.printf(">> DEBUG: Tries taken to find a random e: %d%n", tries);
        }

        return probableE;
    }
}
