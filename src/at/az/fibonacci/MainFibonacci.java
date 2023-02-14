package at.az.fibonacci;

import java.math.BigInteger;
import java.util.ArrayList;

public class MainFibonacci {
    public static void main(String[] args) {
        int maxLoopCnt = 1000;

        ArrayList<BigInteger> fibonacciNumbers = new ArrayList<>();
        fibonacciNumbers.add(BigInteger.ONE);
        fibonacciNumbers.add(BigInteger.ONE);

        for (int i=2; i < maxLoopCnt+2; i++){
            fibonacciNumbers.add(fibonacciNumbers.get(i-2).add(fibonacciNumbers.get(i-1)));
        }

        System.out.printf("Last Fibonacci numbers:%n%s%n%s%n",
                fibonacciNumbers.get(fibonacciNumbers.size()-2),
                fibonacciNumbers.get(fibonacciNumbers.size()-1)
        );
    }
}
