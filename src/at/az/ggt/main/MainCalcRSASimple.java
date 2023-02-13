package at.az.ggt.main;

import at.az.ggt.algo.Utils;
import at.az.ggt.algo.EuklidGGT;
import at.az.ggt.algo.EuklidGGTStruct;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Calculate RSA keys. No prime checks are made inside this main(). Pass p, q and e and get the keys.
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

        BigInteger phiN = Utils.getPhiN(p, q);

        EuklidGGT euklidGGT = new EuklidGGT(e, phiN, CANCEL_AFTER_X_STEPS);

        ArrayList<EuklidGGTStruct> euklidStructs = new ArrayList<>();
        euklidGGT.calcGGT(euklidStructs);
        euklidGGT.calcReverse(euklidStructs);

        BigInteger d = euklidStructs.get(0).getX();
        if (d.compareTo(BigInteger.ZERO) < 0){
            BigInteger newD = d.mod(phiN);
            System.out.printf("Eliminating negative d and reassign it with mod(phi(N)): %s   -->   %s%n", d, newD);
            d = newD;
        }

        System.out.printf("p=     %s%n", p);
        System.out.printf("q=     %s%n", q);
        System.out.printf("N=     %s%n", q.multiply(q));
        System.out.printf("d=     %s%n", d);
        System.out.printf("e=     %s%n", e);
        System.out.printf("phi(N)=%s%n", phiN);
        System.out.println("-->");
        System.out.printf("Public key {e,N} = {%d,%d}%n", e, n);
        System.out.printf("Private key {d,N} = {%d,%d}%n", d, n);
    }
}
