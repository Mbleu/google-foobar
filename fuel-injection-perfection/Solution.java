import java.util.ArrayList;

public class Solution {
    public static int solution(String x)
    {
        int[] digits = new int[x.length()];                             // convert to integer array
        for (int i = 0; i < x.length(); ++i) {
            digits[i] = x.charAt(i) - '0';
        }
        
        if (isZero(digits)) {                                           // special case for 0
            return 1;
        }
        
        ArrayList<Boolean> bits = new ArrayList<>();                    // convert to binary array, store backwards
        while (!(isZero(digits))) {
            bits.add(digits[digits.length - 1] % 2 != 0);
            halve(digits);
        }
        
        int steps = 0;                                                  // operations performed (/2, -1, +1)
        
        while (bits.size() > 1){                                        // loop until number equals 1
            if (!bits.get(0)) {                                         // if number is even:
                bits.remove(0);                                         // divide by 2
            }
            else if ((bits.size() == 2 && bits.get(0) && bits.get(1))   // if number equals 3,
            || (!bits.get(1) && bits.get(0)) ) {                        // or least sig bits equal 01:
                bits.set(0, false);                                     // subtract 1
            }
            else {                                                      // else add 1
                for(int i = 0; i < bits.size(); ++i){   // flip least sig bits, until first 0
                    if (bits.get(i)){
                        bits.set(i, false);
                    }
                    else {
                        bits.set(i, true); break;
                    }
                }
                if (!bits.get(bits.size() -1)) {        // if all bits get flipped to 0, add a most sig 1
                    bits.add(true);
                }
            }
            ++steps;                                                    // 1 operation per loop
        }
        
        return steps;
    }
    
    private static boolean isZero(int[] digits)    // Check if the number is equal to 0
    {
        for (int i = 0; i < digits.length; ++i){
            if (digits[i] != 0) {return false; }
        }
        return true;
    }
    
    private static void halve(int[] digits){   // Divide the number by 2
        boolean carry = false;
        for (int i = 0; i < digits.length; ++i){   // divide each digit by 2, carry 5 down when odd
            boolean next_carry = digits[i] % 2 == 0 ? false : true;
            digits[i] /= 2;
            if (carry) {digits[i] += 5; }
            carry = next_carry;
        }
    }
}