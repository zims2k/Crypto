package at.az.gcd.main;

import at.az.fibonacci.main.algo.FibonacciTuple;
import at.az.fibonacci.main.algo.FibonacciUtil;
import at.az.gcd.algo.EuklidGCD;
import at.az.gcd.algo.EuklidGCDStruct;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * The Extended Euclidean Algorithm is used to determine the private key for RSA.
 */
public class MainEuklidFibonacci {

    private static final BigInteger CANCEL_AFTER_X_STEPS = new BigInteger("1000");

    public static void main(String[] args) {
        FibonacciTuple fibonacciTuple = FibonacciUtil.getFibonacciTuple(Integer.parseInt(args[0]));

        System.out.printf("Last Fibonacci numbers:%n%s%n%s%n",
                fibonacciTuple.getFibonacciNumber1(),
                fibonacciTuple.getFibonacciNumber2()
        );

        EuklidGCD euklidGCD = new EuklidGCD(
                fibonacciTuple.getFibonacciNumber1(),
                fibonacciTuple.getFibonacciNumber2(),
                CANCEL_AFTER_X_STEPS
        );

        ArrayList<EuklidGCDStruct> euklidStructs = new ArrayList<>();
        euklidGCD.calcGCD(euklidStructs);
        MainEuklid.printEuklid(euklidStructs);
    }
}
