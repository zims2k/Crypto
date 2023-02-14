package at.az.fibonacci.main.algo;

import java.math.BigInteger;

public class FibonacciTuple {
    private final BigInteger fibonacciNumber1;
    private final BigInteger fibonacciNumber2;

    public FibonacciTuple(BigInteger fibonacciNumber1, BigInteger fibonacciNumber2) {
        this.fibonacciNumber1 = fibonacciNumber1;
        this.fibonacciNumber2 = fibonacciNumber2;
    }

    public BigInteger getFibonacciNumber1() {
        return fibonacciNumber1;
    }

    public BigInteger getFibonacciNumber2() {
        return fibonacciNumber2;
    }
}
