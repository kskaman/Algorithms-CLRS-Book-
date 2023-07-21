public class MatrixChain {
    int[][] m;
    int[][] s;
    int[] p;
    int n;

    public MatrixChain(int[] p) {
        this.p = p;
        n = p.length - 1;
        m = new int[n][n];
        s = new int[n][n];
    }


    public void memoizedMatrixChain() {
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                m[i][j] = 0;
            }
            for (int j = i+1; j < n; j++) {
                m[i][j] = Integer.MAX_VALUE;
            }
        }
        // printm();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                s[i][j] = 0;
            }
        }

        int dump = lookUpChain(0, n-1);
    }

    
    private int lookUpChain(int i, int j) {
        if (m[i][j] < Integer.MAX_VALUE || i == j) {
            return m[i][j];
        } else {
            int q = 0;
            for (int k = i; k < j; k++) {
                int q1 = lookUpChain(i, k);
                int q2 = lookUpChain(k+1, j);
                q = q1 + q2 + p[i]*p[k+1]*p[j+1];
                if (q < m[i][j]) {
                    m[i][j] = q;
                    s[i][j] = k+1;
                }
            }
        }
        return m[i][j];
    }


    public void matrixChainOrder() {
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                m[i][j] = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                s[i][j] = 0;
            }
        }

        int q = 0;

        for (int l = 1; l < n; l++) {
            
            for (int i = 0; i < n-l; i++) {
                int j = i+l;
                m[i][j] = Integer.MAX_VALUE;
                q = 0;
                for (int k = i; k <= j-1; k++) {
                    q = m[i][k] + m[k+1][j] + (p[i]*p[k+1]*p[j+1]);
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k+1;
                    }
                }
            }
        }
    }


    public void printOptimalPairings(int i, int j) {
        if (i == j) {
            System.out.print("A[" + (i+1) + "]");
        } else {
            System.out.print("(");
            printOptimalPairings(i, s[i][j]-1);
            printOptimalPairings(s[i][j], j);
            System.out.print(")");            
        }
    }

    public void printm() {
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(m[i][j] + "  ");
            }
            System.out.println();
        }
    }


    public void prints() {
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(s[i][j] + "  ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        //int[] p = {30, 35, 15, 5, 10, 20, 25};
        int[] p = {5, 10, 3, 12, 5, 50, 6};
        MatrixChain order = new MatrixChain(p);
        order.matrixChainOrder();
        // order.printm();
        // order.prints();
        order.printOptimalPairings(0, p.length-2);
        System.out.println();
        order.memoizedMatrixChain();
        order.printOptimalPairings(0, p.length-2);
        // order.printm();
        // order.prints();
    }

}