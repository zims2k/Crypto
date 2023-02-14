package at.az.fibonacci.main;

import at.az.fibonacci.main.algo.FibonacciTuple;
import at.az.fibonacci.main.algo.FibonacciUtil;


public class MainFibonacci {
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("args[0]: maxLoopCnt for Fibonacci numbers");
        }

        int maxLoopCnt = Integer.parseInt(args[0]);

        FibonacciTuple fibonacciTuple = FibonacciUtil.getFibonacciTuple(maxLoopCnt);

        System.out.printf("Last Fibonacci numbers:%n%s%n%s%n",
                fibonacciTuple.getFibonacciNumber1(),
                fibonacciTuple.getFibonacciNumber2()
        );
    }
}
