public class FunWithLoops {
    public static void main(String[] args) {
        System.out.println("Print to n");
        printToN(10);

        System.out.println("\nPrint -1 series");
        printMinusOneSeries(-2, 4);

        System.out.println("\nPrint +2 series");
        printAddTwoSeries(6, 18);

        System.out.println("\nPrint *10 series");
        printMultiplesofTenSeries(5);

        System.out.println("\nPrint -3 series");
        printMinusThreeSeries(68, 44);

        System.out.println("\nPrint x series");
        printSeriesOfX(8);

        System.out.println("\nWhile loop");
        printSeriesWithWhileLoop(10);

        System.out.println("\nASCII Art");
        printASCIIArt(10);

        System.out.println("\nFull Triangle");
        printFullTriangle(6);

        System.out.println("\nSquare");
        printSquare(6);

        System.out.println("\nHollow Triangle");
        printHollowTriangle(6);
    }

    public static void printToN(int n) {
        for(int i=1; i<=n; ) {
            System.out.println(i);
            i+=1;
        }
    }

    public static void printMinusOneSeries(int min, int max){
    //-2,-1,0,1,2...
        for(int j=min; j<=max; j++){
            System.out.print(j + ",");
        }
    }

    public static void printAddTwoSeries(int min, int max) {
        //6, 8, 10, 12...
        for(int k=min; k<=max; ) {
            System.out.print(k + ",");
            k+=2;
        }
    }

    public static void printMultiplesofTenSeries(int n) {
        //10,20,30...
        for(int l=1; l<=n; l++) {
            System.out.print(l*10 + ",");
        }
    }

    public static void printMinusThreeSeries(int min, int max) {
        //68, 65, 62, 59...
        for(int m=min; m<=max; ) {
            System.out.print(m + ",");
            m-=3;
        }
    }

    public static void printSeriesOfX(int n) {
        //x, xx, xxx, xxxx
        for(int i=1; i<=n; i++){
            for(int j=1; j<=i;) {
                System.out.print("x");
                j++;
            }
            System.out.print(",");
        }
    }

    public static void printSeriesWithWhileLoop(int n) {
        int loop = 1;
        while(loop<=n) {
            System.out.println(loop);
            loop++;
        }
    }

    public static void printASCIIArt(int n) {
        //ASCII art
        for(int i=1; i<=n;) {
            System.out.print("*");
            i++;
        }
    }

    public static void printFullTriangle(int rows) {
        for(int i=1; i<=rows; i++) {
            for (int j = rows - 1; j >= i; j--) {
                System.out.print(" ");
            }
            for (int k = 1; k <= i; k++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }


    public static void printSquare(int size) {
        for(int i=1; i<=size; i++) {
            if(i==1 || i==size) {
                for(int j=1; j<=size; j++) {
                    System.out.print("* ");
                }
            } else {
                System.out.print("* ");
                for(int k=1; k<=(size-2) ; k++) {
                    System.out.print("  ");
                }
                System.out.print("* ");
            }
           System.out.println();
        }
    }

//    * * * * *
//    *       *
//    *     *
//    * * * *
//
//    1 0 4
//    2 2 2
//    3 2 2
//    4 0 4

    public static void printHollowTriangle(int size) {
        for (int i = 1; i <= size; i++) {
            for (int j = size; j > i; j--) {
                System.out.print(" ");
            }

            if (i == 1 || i == size) {
                for (int k = 1; k <= i; k++) {
                    System.out.print("* ");
                }
            } else {
                for (int l = 1; l <= 2; l++) {
                    System.out.print("* ");
                    for(int m = 1; m < i-1; m++) {
                        System.out.print("  ");
                    }
                }
            }
            System.out.println();
        }
    }
}
