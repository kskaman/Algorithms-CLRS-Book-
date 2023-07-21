public class LCS {
    private int[][] c;
    private int m;
    private int n;
    private int[] x;
    private int[] y;

    public LCS(int[] x, int[] y) {
        this.x = x;
        this.y = y;
        m = x.length;
        n = y.length;
        c = new int[m+1][n+1];
    }

    private void initializeC() {
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                c[i][j] = 0;
            }
        }
    }

    public void LCSLength() {

        initializeC();

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x[i-1] == y[j-1]) {
                    c[i][j] = c[i-1][j-1] + 1;
                } else if (c[i-1][j] >= c[i][j-1]) {
                    c[i][j] = c[i-1][j];
                } else {
                    c[i][j] = c[i][j-1];
                }
            }
        }
    }


    public void memoizedLCSLength() {
        initializeC();
        memoizedLCSLengthAux(m, n);
    }

    private void memoizedLCSLengthAux(int i, int j) {
        if (i == 0 || j == 0 || c[i][j] != 0) {
            return;
        }
        if (x[i-1] == y[j-1]) {
            memoizedLCSLengthAux(i-1, j-1);
            c[i][j] = c[i-1][j-1] + 1;
        } else {
            memoizedLCSLengthAux(i-1, j);
            memoizedLCSLengthAux(i, j-1);
            if (c[i-1][j] >= c[i][j-1]) {
                c[i][j] = c[i-1][j];
            } else {
                c[i][j] = c[i][j-1];
            }
        }
    }

    public void printSubsequenceNonRecursive() {
        int f = c[m][n];
        int i = m; 
        int j = n;
        int[] s = new int[f];

        while ((i > 0) && (j > 0)) {
            if (x[i-1] == y[j-1]) {
                s[--f] = x[i-1];
                i--;
                j--;
            } else if (c[i-1][j] >= c[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.print("Longest Monotonically Increasing Subsequence : ");
        for (int k = 0; k < s.length; k++) {
            System.out.print(s[k] + " ");
        }
        System.out.println();
    }
}