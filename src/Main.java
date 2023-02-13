import at.az.ggt.EUklidGGTStruct;
import at.az.ggt.EuklidGGT;

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
//        EuklidGGT euklidGGT = new EuklidGGT(21, 34, 1000); // fibonacci numbers (remind the rabbits!)
//        EuklidGGT euklidGGT = new EuklidGGT(128, 34, 1000); // example: https://www.youtube.com/watch?v=QORmBQo8-j0
//        EuklidGGT euklidGGT = new EuklidGGT(2, 4, 1000); // example: https://www.youtube.com/watch?v=QORmBQo8-j0

        EuklidGGT euklidGGT = new EuklidGGT(47, 60, 1000); // example: https://www.youtube.com/watch?v=QORmBQo8-j0


        ArrayList<EUklidGGTStruct> euklidStructs = new ArrayList<>();

        long t0 = System.currentTimeMillis();

        // --- STEP 1: CALCULATE GGT
        int ggt = euklidGGT.calcGGT(euklidStructs);

        long t1 = System.currentTimeMillis();

        // --- STEP 2: CALCULATE DIOPHANTIC LINEAR COMBINATION EQUATION
        euklidGGT.calcReverse(euklidStructs);

        long t2 = System.currentTimeMillis();

        printEuklid(euklidStructs);

        printGGT(ggt);

        EUklidGGTStruct eUklidGGTStruct0 = euklidStructs.get(0);
        printDiophant(
                ggt,
                eUklidGGTStruct0.getX(),
                eUklidGGTStruct0.getA(),
                eUklidGGTStruct0.getY(),
                eUklidGGTStruct0.getB()
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
    private static void printDiophant(int ggt, int x, int a, int y, int b) {
        System.out.println();
        System.out.printf("GGT(%d,%d) = %d = (%d) * %d + (%d) * %d%n", a, b, ggt, x, a, y, b);
    }

    private static void printGGT(int ggt) {
        System.out.println();
        System.out.printf("GGT --> %d%n", ggt);
    }

    private static void printEuklid(ArrayList<EUklidGGTStruct> euklidStructs) {
        System.out.printf("|%20s|%20s|%20s|%20s|%20s|%20s%n", "a", "b", "q", "r", "x", "y");
        for (int i = 0; i < 129; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (EUklidGGTStruct euklidStruct : euklidStructs) {
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
