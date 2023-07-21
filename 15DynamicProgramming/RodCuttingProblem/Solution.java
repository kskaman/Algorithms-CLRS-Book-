import java.util.Arrays;

/**
 * This class contains method that solve Rod Cutting problem.
 * We a given a rod of size {@code n}. We are also given 
 * the selling price of rod of length from 1 to n. 
 * We need to find in how many pieces of different lengthwe need
 * to cut the rod into pieces to maximize the selling price of rod.
 */

public class Solution {
    int[] r;
    int[] p;
    int[] firstCut;
    int cutCost;

    public Solution(int[] p, int c) {
        r = new int[p.length];
        Arrays.fill(r, Integer.MIN_VALUE);
        firstCut = new int[p.length];
        Arrays.fill(firstCut, 0);
        this.p = p; 
        cutCost = c;
    }

    /**
     * The function cutRod implements a recursive solution.
     * Though the solution is correct, it is inefficient for  
     * problems of large rod lengths.
     * @param p array containing cost of each size 
     *          (size is indicated by index number of array)
     * @param n length od rod
     * @return Maximum price the rod can be sold
     */
    public int recursiveCutRod(int n) {
        Arrays.fill(r, 0);

        if (n == 0) {
            return 0;
        }

        int q = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            if (i == n) 
                q = Integer.max(q, p[i] + recursiveCutRod(n-i));
            else 
                q = Integer.max(q, p[i] + recursiveCutRod(n-i) - cutCost);
        }

        return q;
    }


    public void memoizedCutRod(int n) {
        Arrays.fill(r, 0);

        for (int i = 0; i <= n; i++) {
            r[i] = Integer.MIN_VALUE;
        }

        memoizedCutRodAux(n);
    }


    public void memoizedCutRodAux(int n) {
        if (r[n] >= 0) {
            return;
        }

        int q;

        if (n == 0) {
            q = 0;
        } else {
            q = Integer.MIN_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            memoizedCutRodAux(n-i);
            int temp = 0;
            // q = Integer.max(q, p[i] + r[n-i]);
            if (i == n) temp = p[i] + r[n-i];
            else if (i < n ) temp = p[i] + r[n-i] - cutCost;

            if (q <= temp) {
                q = temp;
                firstCut[n] = i;
            }
        }
        r[n] = q;
    }


    public void bottomUpCutRod(int n) {
        Arrays.fill(r, 0);
        Arrays.fill(firstCut, 0);

        for (int i = 1; i <= n; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                // q = Integer.max(q, p[j] + r[i-j]);
                int newValue = 0;
                
                if (i == j) newValue = p[j] + r[i-j];
                else if (j < i ) newValue = p[j] + r[i-j] - cutCost;

                if (q <= newValue) {
                    q = newValue;
                    firstCut[i] = j;
                }
            }
            r[i] = q;
        }
    }

    public void printSolution(long t) {
        System.out.println("Length \t Solution \t First Cut Size");

        for (int i = 1; i < r.length; i++) {
            System.out.println((i) + " \t\t" +  r[i] + "\t\t" +  firstCut[i]);
        }
        System.out.println("Time taken for above computations : " + t + " second(s)");
    }

    /**
     * Test function
     */
    public static void main(String[] args) {
        int[] p = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int cost = 0;
        Solution solution = new Solution(p, cost);
        int n = p.length-1;

        int[] firstCutLength = new int[n];
        Arrays.fill(firstCutLength, 0);

        int[] s = new int[10];
        long timeTaken = Long.MIN_VALUE;

        System.out.println("Length \t Solution \t Time Taken (seconds)");
        for (int i = 1; i <= 10; i++) {
            long start = System.currentTimeMillis();
            s[i-1] = solution.recursiveCutRod(i);
            long end = System.currentTimeMillis();
            System.out.println(i + "\t\t" + s[i-1] + "\t\t" + (end-start)/1000);
        }


        long start = System.currentTimeMillis();
        solution.memoizedCutRod(n);
        long end = System.currentTimeMillis();
        timeTaken = (end-start)%1000;
        solution.printSolution(timeTaken);

        start = System.currentTimeMillis();
        solution.bottomUpCutRod(n);
        end = System.currentTimeMillis();
        timeTaken = (end-start)%1000;
        solution.printSolution(timeTaken);
    }

}