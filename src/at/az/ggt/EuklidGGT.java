package at.az.ggt;

import java.util.ArrayList;

public class EuklidGGT {

    private int a;
    private int b;
    private int r;
    private int cancelAfterXSteps;

    public EuklidGGT(int a, int b, int cancelAfterXSteps) {
        this.a = a;
        this.b = b;
        this.cancelAfterXSteps = cancelAfterXSteps;
    }

    public int calcGGT(ArrayList<EUklidGGTStruct> euklidStructs){
        int a = Math.max(this.a, this.b);
        int b = Math.min(this.a, this.b);
        int r = -1;

        int i = 0;
        for(; i< cancelAfterXSteps; i++){
            int prevR = r;

            r = a % b;

            EUklidGGTStruct euklidStruct = new EUklidGGTStruct();
            euklidStruct.setA(a);
            euklidStruct.setB(b);
            euklidStruct.setQ(a/b);
            euklidStruct.setR(r);
            euklidStructs.add(euklidStruct);

            a = b;
            b = r;

            if (r <= 0){
                return prevR;
            }
        }

        if (i >= 1000){
            System.err.println("CANCELLED AFTER MAX STEPS FOR EUKLID GGT");
        }

        return 1;
    }

    private static void printBar() {
        for (int x=0; x<64;x++){
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * See: <a href="https://www.youtube.com/watch?v=nD6psV2vkRU">Christian Spannagel: Erweiterter Euklidischer Algorithmus Teil 3</a>
     * @param str structure list
     */
    public void calcReverse(ArrayList<EUklidGGTStruct> str) {
        if (str == null || str.size() == 0){
            throw new IllegalArgumentException("NO STRUCTS OR EMPTY STRUCT RECEIVED IN PARAMETER");
        }

        str.get(str.size()-1).setX(0);
        str.get(str.size()-1).setY(1);

        if (str.size() == 1){
            return;
        }

        for (int i = str.size() - 2; i >= 0; i--){
            str.get(i).setX(
                    str.get(i+1).getY()
            );

            str.get(i).setY(
                    str.get(i+1).getX() - str.get(i).getQ() * str.get(i+1).getY() // y = x_i+1 - q_i * y_i+1
            );
        }
    }
}
