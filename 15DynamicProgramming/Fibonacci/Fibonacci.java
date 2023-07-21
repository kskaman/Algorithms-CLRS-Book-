import java.util.Arrays;
import java.util.Scanner;

public class Fibonacci {

    int[] f;    // array to save Fibonacci sequence upto n

    // Constructor
    public Fibonacci(int n) {
        f = new int[n+1];
    }


    /**
     * Evaluates n-th number in Finacci sequence using 
     * recursion.
     * @param n the index of number in Fibonacci sequence to be calculated
     * @return n-th number in Fibonacci sequence
     */
    public static int recursion(int n) {

        if (n == 0 || n == 1) {
            return n;
        }
        return recursion(n-1) + recursion(n-2);
    }


    /**
     * Evaluates n-th number in Fibonacci sequence
     * using memoized top-down approach
     * 
     * @return n-th number in Fibonacci sequence
     */
    public int memoizedSolution() {
        
        Arrays.fill(f, 0);
        f[0] = 1;
        f[1] = 1;

        int n = f.length-1;

        return memoizedSolutionAux(n-1);
    }

    // helping method for memoized top down approach
    private int memoizedSolutionAux(int n) {
        if (f[n] > 0) return f[n];

        f[n] = memoizedSolutionAux(n-1) + memoizedSolutionAux(n-2);
        return f[n];
    }


    /**
     * Evaluates the n-th number in fibonacci sequence
     * using memoized bottom-up aproach
     * 
     * @return n-th number in fibonacci sequence
     */
    public int bottomUpApproach() {
        Arrays.fill(f, 0);
        f[0] = 0;
        f[1] = 1;

        int n = f.length-1;

        for (int i = 2; i <= n; i++) {
            f[i] = f[i-1] + f[i-2];
        }

        return f[n];
    }


    /**
     * Prints the solution first using recursion, then memoized top-down
     * approach and then using memoized bottom-up approach, along with the 
     * time taken using three approaches.Thus, comparing the time-efficiency.
     * 
     * @param n the index of number in fibonacci sequence
     */
    public static void printSolution(int n) {

        System.out.println("Solution using Recursive method : ");
        long start = System.currentTimeMillis();
        System.out.println("n-th nummber in Fibonacci sequence is " + recursion(n));
        long end = System.currentTimeMillis();
        System.out.println("Time taken : " + (end - start)/1000 + " seconds\n");

        Fibonacci f = new Fibonacci(n);

        System.out.println("Solution using memoized top-down approach : ");
        start = System.currentTimeMillis();
        System.out.println("n-th nummber in Fibonacci sequence is " + f.memoizedSolution());
        end = System.currentTimeMillis();
        System.out.println("Time taken : " + (end - start)/1000 + " seconds\n");

        System.out.println("Solution using bottom-up approach : ");
        start = System.currentTimeMillis();
        System.out.println("n-th nummber in Fibonacci sequence is " + f.bottomUpApproach());
        end = System.currentTimeMillis();
        System.out.println("Time taken : " + (end - start)/1000 + " seconds\n");
    }

    /**
     * Test function
     */
    public static void main(String[] args) {
        System.out.println("Enter a positive integer : ");
        Scanner in = new Scanner(System.in);
        int n;
        if (in.hasNextInt()) {
            n = in.nextInt();
            printSolution(n);
        } else {
            System.out.println("Invalid input.\n You need to enter a positive integer.\n Terminating program.");
            System.out.println(0);
        }

        in.close();
    }
}