package at.az.ggt.main;

import at.az.ggt.algo.EuklidGGTStruct;
import at.az.ggt.algo.EuklidGGT;

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



        EuklidGGT euklidGGT = new EuklidGGT(new BigInteger(args[0]), new BigInteger(args[1]), CANCEL_AFTER_X_STEPS); // example: https://www.youtube.com/watch?v=QORmBQo8-j0

        ArrayList<EuklidGGTStruct> euklidStructs = new ArrayList<>();

        long t0 = System.currentTimeMillis();

        // --- STEP 1: CALCULATE GGT
        BigInteger ggt = euklidGGT.calcGGT(euklidStructs);

        long t1 = System.currentTimeMillis();

        // --- STEP 2: CALCULATE DIOPHANTIC LINEAR COMBINATION EQUATION
        euklidGGT.calcReverse(euklidStructs);

        long t2 = System.currentTimeMillis();

        printEuklid(euklidStructs);

        printGGT(ggt);

        EuklidGGTStruct euklidGGTStruct0 = euklidStructs.get(0);
        printDiophant(
                ggt,
                euklidGGTStruct0.getX(),
                euklidGGTStruct0.getA(),
                euklidGGTStruct0.getY(),
                euklidGGTStruct0.getB()
        );

        printTimeConsumption(t0, t1, t2);
    }

    private static void printTimeConsumption(long t0, long t1, long t2) {
        System.out.println();
        System.out.printf("Time consumption for GGT calculation:                        %10dms%n", (t1-t0));

        System.out.printf("Time consumption for diophantic linear equation calculation: %10dms%n", (t2-t1));

        System.out.printf("Total consumption for extended euclidean algorithm:          %10dms%n", (t2-t0));

    }

    /**
     * Print GGT(a, b) as diophantic linear equation.
     */
    private static void printDiophant(BigInteger ggt, BigInteger x, BigInteger a, BigInteger y, BigInteger b) {
        System.out.println();
        System.out.printf("GGT(%d,%d) = %d = (%d) * %d + (%d) * %d%n", a, b, ggt, x, a, y, b);
    }

    private static void printGGT(BigInteger ggt) {
        System.out.println();
        System.out.printf("GGT --> %d%n", ggt);
    }

    private static void printEuklid(ArrayList<EuklidGGTStruct> euklidStructs) {
        System.out.printf("|%40s|%40s|%40s|%40s|%40s|%40s%n", "a", "b", "q", "r", "x", "y");
        for (int i = 0; i < 247; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (EuklidGGTStruct euklidStruct : euklidStructs) {
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
