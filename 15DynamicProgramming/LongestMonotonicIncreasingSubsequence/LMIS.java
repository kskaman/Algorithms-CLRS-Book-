import java.util.Arrays;

public class LMIS {
    int[] seq;
    int n;

    public LMIS(int[] s) {
        n = s.length;
        seq = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = s[i];
        }
    } 


    public void findUsingLCS() {
        int[] seqCopy = new int[n];

        for (int i = 0; i < n; i++) {
            seqCopy[i] = seq[i];
        }

        Arrays.sort(seqCopy, 0, n);

        
        LCS obj1 = new LCS(seq, seqCopy);
        obj1.memoizedLCSLength();
        obj1.printSubsequenceNonRecursive();
    }


    public void findSubsequence() {
        
        int[] b = new int[n];
        Arrays.fill(b, Integer.MAX_VALUE);
        IntList[] c = new IntList[n];

        int L = 0;

        for (int i = 0; i < n; i++) {
            if (seq[i] < b[0]) {
                b[0] = seq[i]; 
                c[0] = new IntList();
                c[0].insert(seq[i]);
            } else {
                
                int j = 0;
                while (b[j] < seq[i] && j < n) j++;

                if (j <= L) continue;

                b[j] = seq[i];
                c[j] = c[j-1];
                c[j].insert(seq[i]);
                if (j+1 > L ) L++;
            }
        }

        System.out.print("Longest Monotonically Increasing Subsequence : ");
        System.out.print(c[L]);
        System.out.println();
    }


    public static void main(String[] args) {
        int[] s = {1, -1, 3, 4, 2, 6, 7, -6, -7, -5, 9, 11};
        LMIS obj = new LMIS(s);
        obj.findUsingLCS();
        obj.findSubsequence();
    }
}