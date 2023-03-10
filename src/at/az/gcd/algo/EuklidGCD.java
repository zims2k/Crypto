package at.az.gcd.algo;

import java.math.BigInteger;
import java.util.ArrayList;

public class EuklidGCD {

    private final BigInteger a;
    private final BigInteger b;
    private final BigInteger cancelAfterXSteps;

    public EuklidGCD(BigInteger a, BigInteger b, BigInteger cancelAfterXSteps) {
        this.a = a;
        this.b = b;
        this.cancelAfterXSteps = cancelAfterXSteps;
    }

    public BigInteger calcGCD(ArrayList<EuklidGCDStruct> euklidStructs){
        BigInteger a = this.a;
        BigInteger b = this.b;
        BigInteger r = new BigInteger("-1");

        BigInteger i = new BigInteger("0");
        for(; i.compareTo(cancelAfterXSteps) < 0; i = i.add(new BigInteger("1"))){
            BigInteger prevR = r;

            r = a.mod(b);

            EuklidGCDStruct euklidStruct = new EuklidGCDStruct();
            euklidStruct.setA(a);
            euklidStruct.setB(b);
            euklidStruct.setQ(a.divide(b));
            euklidStruct.setR(r);
            euklidStructs.add(euklidStruct);

            a = b;
            b = r;

            if (r.compareTo(BigInteger.ZERO) <= 0){
                return prevR;
            }
        }

        if (i.compareTo(cancelAfterXSteps) > 0){
            System.err.println("CANCELLED AFTER MAX STEPS FOR EUKLID GCD");
        }

        return new BigInteger("1");
    }

    /**
     * See: <a href="https://www.youtube.com/watch?v=nD6psV2vkRU">Christian Spannagel: Erweiterter Euklidischer Algorithmus Teil 3</a>
     * @param str structure list
     */
    public void calcReverse(ArrayList<EuklidGCDStruct> str) {
        if (str == null || str.size() == 0){
            throw new IllegalArgumentException("NO STRUCTS OR EMPTY STRUCT RECEIVED IN PARAMETER");
        }

        str.get(str.size()-1).setX(new BigInteger("0"));
        str.get(str.size()-1).setY(new BigInteger("1"));

        if (str.size() == 1){
            return;
        }

        for (int i = str.size() - 2; i >= 0; i--){
            str.get(i).setX(
                    str.get(i+1).getY()
            );

            str.get(i).setY(
                    str.get(i+1).getX().subtract(str.get(i).getQ().multiply(str.get(i+1).getY()))
            ); // y = x_i+1 - q_i * y_i+1
        }
    }
}
