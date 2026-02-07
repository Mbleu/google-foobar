import java.util.Arrays;

public class Solution {
    public static int solution(int[] x, int[] y) {
        if (x.length > y.length) {
            return solution(y, x); // Ensure y contains the unique element
        }
        
        Arrays.sort(x);
        Arrays.sort(y);
        
        for (int i = 0; i < x.length; ++i) { // Loop until mismatch found
            if (x[i] != y[i]) {
                return y[i];
            }
        }
        return y[y.length - 1]; // No mismatches found, final element of y must be the unique
    }
}