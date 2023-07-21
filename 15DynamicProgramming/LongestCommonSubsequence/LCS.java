public class LCS {
    private int[][] c;
    private int[][] b;
    private int m;
    private int n;
    private String x;
    private String y;

    public LCS(String x, String y) {
        this.x = x;
        this.y = y;
        m = x.length();
        n = y.length();
        c = new int[m+1][n+1];
        b = new int[m][n];
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
                if (x.charAt(i-1) == y.charAt(j-1)) {
                    c[i][j] = c[i-1][j-1] + 1;
                    b[i-1][j-1] = -1;
                } else if (c[i-1][j] >= c[i][j-1]) {
                    c[i][j] = c[i-1][j];
                    b[i-1][j-1] = 0;
                } else {
                    c[i][j] = c[i][j-1];
                    b[i-1][j-1] = 1;
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
        if (x.charAt(i-1) == y.charAt(j-1)) {
            memoizedLCSLengthAux(i-1, j-1);
            c[i][j] = c[i-1][j-1] + 1;
            b[i-1][j-1] = -1;
        } else {
            memoizedLCSLengthAux(i-1, j);
            memoizedLCSLengthAux(i, j-1);
            if (c[i-1][j] >= c[i][j-1]) {
                c[i][j] = c[i-1][j];
                b[i-1][j-1] = 0;
            } else {
                c[i][j] = c[i][j-1];
                b[i-1][j-1] = 1;
            }
        }
    }


    public void printSubsequence() {
        System.out.print("Longest Common Subsequence : ");
        printLCS(m, n);
        System.out.println();
    }

    private void printLCS(int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (b[i-1][j-1] == -1) {
            printLCS(i-1, j-1);
            System.out.print(x.charAt(i-1));
        } else if (b[i-1][j-1] == 0) {
            printLCS(i-1, j);
        } else {
            printLCS(i, j-1);
        }
    }

    public void printSubsequenceNonRecursive() {
        int f = c[m][n];
        int i = m; 
        int j = n;
        char[] s = new char[f];

        while ((i > 0) && (j > 0)) {
            if (x.charAt(i-1) == y.charAt(j-1)) {
                s[--f] = x.charAt(i-1);
                i--;
                j--;
            } else if (c[i-1][j] >= c[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }

        System.out.print("Longest Common Subsequence : ");
        for (int k = 0; k < s.length; k++) {
            System.out.print(s[k]);
        }
        System.out.println();
    }


    private void printC() {
        System.out.println();
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println(); 
        }
    }


    private void printB() {
        System.out.println();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println(); 
        }
    }


    public static void main(String[] args) {
        // String x = "ABCBDAB";
        // String y = "BDCABA"; 
        String x = "10010101";
        String y = "010110110";
        // x = "ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";
        // y = "GTCGTTCGGAATGCCGTTGCTCTGTAAA";

        LCS obj = new LCS(x, y);
        obj.LCSLength();
        obj.printSubsequence();
        obj.printSubsequenceNonRecursive();
        // obj.printC();
        // obj.printB();
        
        
        obj.memoizedLCSLength();
        obj.printSubsequence();
        obj.printSubsequenceNonRecursive();
        obj.printC();
        // obj.printB();
    }
}