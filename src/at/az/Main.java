package at.az;

import at.az.ggt.EuklidGGTStruct;
import at.az.ggt.EuklidGGT;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * <p>
 *     RSA example. Will develop it further from time to time... At the moment, only the extended euclidean algorithm (EEA)
 *     is implemented. This algorithm is used to determine the private key for RSA.
 * </p>
 * <p>
 *     In general, RSA contains the following steps:
 *     <ol>
 *         <li>
 *             Evaluate 2 very very high prime numbers
 *         </li>
 *         <li>
 *             Calculate RSA module (N=p*q)
 *         </li>
 *         <li>
 *             Determine phi(N) = (p-1)*(q-1)
 *         </li>
 *         <li>
 *             Choose a random number &quot;e&quot; that satisfies 1<e<phi(N) and GGT(e,phi(N))=1
 *         </li>
 *         <li>
 *             public key is: {e,N}
 *         </li>
 *         <li>
 *             Calculate private key: Determine a &quot;d&quot; with e * d = 1 mod phi(N). This is the part that the
 *             EEA comes into place! That means: Apply EEA with a=e and b=phi(N).
 *         </li>
 *     </ol>
 * </p>
 */
public class Main {
    public static void main(String[] args) {
//        EuklidGGT euklidGGT = new EuklidGGT(new BigInteger("21"), new BigInteger("34"), new BigInteger("1000")); // fibonacci numbers (remind the rabbits!)
//        EuklidGGT euklidGGT = new EuklidGGT(new BigInteger("128"), new BigInteger("34"), new BigInteger("1000")); // example: https://www.youtube.com/watch?v=QORmBQo8-j0
//        EuklidGGT euklidGGT = new EuklidGGT(new BigInteger("2"), new BigInteger("4"), new BigInteger("1000")); // example: https://www.youtube.com/watch?v=QORmBQo8-j0

//        EuklidGGT euklidGGT = new EuklidGGT(new BigInteger("47"), new BigInteger("60"), new BigInteger("1000"));

        EuklidGGT euklidGGT = new EuklidGGT(new BigInteger("148894445742041325547806458472397916603026273992795324185271289425213239361064475310309971132180337174752834401423587560"), new BigInteger("10"), new BigInteger("1000"));
//        EuklidGGT euklidGGT = new EuklidGGT(new BigInteger("3"), new BigInteger("11"), new BigInteger("1000"));
        // example: https://www.youtube.com/watch?v=QORmBQo8-j0


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
        System.out.printf("|%20s|%20s|%20s|%20s|%20s|%20s%n", "a", "b", "q", "r", "x", "y");
        for (int i = 0; i < 129; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (EuklidGGTStruct euklidStruct : euklidStructs) {
            System.out.printf(
                    "|%20d|%20d|%20d|%20d|%20d|%20d|%n",
                    euklidStruct.getA(),
                    euklidStruct.getB(),
                    euklidStruct.getQ(),
                    euklidStruct.getR(),
                    euklidStruct.getX(),
                    euklidStruct.getY());
        }
    }
}
