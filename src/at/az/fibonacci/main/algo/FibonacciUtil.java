package at.az.fibonacci.main.algo;

import java.math.BigInteger;
import java.util.ArrayList;

public class FibonacciUtil {
    public static FibonacciTuple getFibonacciTuple(int maxLoopCnt){
        ArrayList<BigInteger> fibonacciNumbers = new ArrayList<>();
        fibonacciNumbers.add(BigInteger.ONE);
        fibonacciNumbers.add(BigInteger.ONE);

        for (int i=2; i < maxLoopCnt+2; i++){
            fibonacciNumbers.add(fibonacciNumbers.get(i-2).add(fibonacciNumbers.get(i-1)));
        }

        return new FibonacciTuple(
                fibonacciNumbers.get(fibonacciNumbers.size()-2),
                fibonacciNumbers.get(fibonacciNumbers.size()-1)
        );
    }
}
