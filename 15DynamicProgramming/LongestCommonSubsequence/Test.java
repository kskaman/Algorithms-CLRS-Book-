public class Test {
    public static void main(String[] args) {
        // String x = "ABCBDAB";
        // String y = "BDCABA"; 
        String x = "10010101";
        String y = "010110110";

        LCS obj = new LCS(x, y);
        obj.LCSLength();
        obj.printSubsequence();
        // obj.printSubsequenceII();

        /* You cannot access private fields or methods outside the class.
         * Commented code will give error */
        // obj.printC();
        // obj.printB();
        
        
        // obj.memoizedLCSLength();
        // obj.printSubsequence();
        // obj.printSubsequenceII();

        /* You cannot access private fields or methods outside the class.
         * Commented code will give error */
        // obj.printC();
        // obj.printB();
        // System.out.println(obj.m);
    }
}
