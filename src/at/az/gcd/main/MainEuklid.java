package at.az.gcd.main;

import at.az.gcd.algo.EuklidGCDStruct;
import at.az.gcd.algo.EuklidGCD;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * The Extended Euclidean Algorithm is used to determine the private key for RSA.
 */
public class MainEuklid {

    private static final BigInteger CANCEL_AFTER_X_STEPS = new BigInteger("1000");

    public static void main(String[] args) {
        if (args.length != 2){
            System.out.println("Args: [0]: a, [1]: b");
            System.exit(-1);
        }

        EuklidGCD euklidGCD = new EuklidGCD(new BigInteger(args[0]), new BigInteger(args[1]), CANCEL_AFTER_X_STEPS); // example: https://www.youtube.com/watch?v=QORmBQo8-j0

        ArrayList<EuklidGCDStruct> euklidStructs = new ArrayList<>();

        long t0 = System.currentTimeMillis();

        // --- STEP 1: CALCULATE GCD
        BigInteger gcd = euklidGCD.calcGCD(euklidStructs);

        long t1 = System.currentTimeMillis();

        // --- STEP 2: CALCULATE DIOPHANTIC LINEAR COMBINATION EQUATION
        euklidGCD.calcReverse(euklidStructs);

        long t2 = System.currentTimeMillis();

        printEuklid(euklidStructs);

        printGCD(gcd);

        EuklidGCDStruct euklidGCDStruct0 = euklidStructs.get(0);
        printDiophant(
                gcd,
                euklidGCDStruct0.getX(),
                euklidGCDStruct0.getA(),
                euklidGCDStruct0.getY(),
                euklidGCDStruct0.getB()
        );

        printTimeConsumption(t0, t1, t2);
    }

    private static void printTimeConsumption(long t0, long t1, long t2) {
        System.out.println();
        System.out.printf("Time consumption for GCD calculation:                        %10dms%n", (t1-t0));

        System.out.printf("Time consumption for diophantic linear equation calculation: %10dms%n", (t2-t1));

        System.out.printf("Total consumption for extended euclidean algorithm:          %10dms%n", (t2-t0));

    }

    /**
     * Print GCD(a, b) as diophantic linear equation.
     */
    private static void printDiophant(BigInteger gcd, BigInteger x, BigInteger a, BigInteger y, BigInteger b) {
        System.out.println();
        System.out.printf("GCD(%d,%d) = %d = (%d) * %d + (%d) * %d%n", a, b, gcd, x, a, y, b);
    }

    private static void printGCD(BigInteger gcd) {
        System.out.println();
        System.out.printf("GCD --> %d%n", gcd);
    }

    private static void printEuklid(ArrayList<EuklidGCDStruct> euklidStructs) {
        System.out.printf("|%40s|%40s|%40s|%40s|%40s|%40s%n", "a", "b", "q", "r", "x", "y");
        for (int i = 0; i < 247; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (EuklidGCDStruct euklidStruct : euklidStructs) {
            System.out.printf(
                    "|%40d|%40d|%40d|%40d|%40d|%40d|%n",
                    euklidStruct.getA(),
                    euklidStruct.getB(),
                    euklidStruct.getQ(),
                    euklidStruct.getR(),
                    euklidStruct.getX(),
                    euklidStruct.getY());
        }
    }
}
