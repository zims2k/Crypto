package at.az;

import at.az.ggt.EuklidGGT;
import at.az.ggt.EuklidGGTStruct;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Calculate private key {d,N}. No prime checks are made inside this main().
 */
public class MainCalcRSASimple {

    private static final BigInteger CANCEL_AFTER_X_STEPS = new BigInteger("1000");

    public static void main(String[] args) {
        if (args.length != 3){
            System.out.println("Args: [0]: p, [1]: q, [2]: e");
            System.exit(-1);
        }

        BigInteger p = new BigInteger(args[0]);
        BigInteger q = new BigInteger(args[1]);
        BigInteger e = new BigInteger(args[2]);

        BigInteger n = p.multiply(q);

        BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        EuklidGGT euklidGGT = new EuklidGGT(e, phiN, CANCEL_AFTER_X_STEPS);

        ArrayList<EuklidGGTStruct> euklidStructs = new ArrayList<>();
        euklidGGT.calcGGT(euklidStructs);
        euklidGGT.calcReverse(euklidStructs);

        BigInteger d = euklidStructs.get(0).getX();

        System.out.printf("Public key {e,N} = {%d,%d}%n", e, n);
        System.out.printf("Private key {d,N} = {%d,%d}%n", d, n);
    }
}
