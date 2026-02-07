// 1st solution (brute force):
// 
//  public static int[] solution(int[] l, int t) {
//         for (int i = 0; i < l.length; ++i){
//             int sum = 0;
//             for (int j = i; j < l.length; ++j){
//                 sum += l[j];
//                 if (sum == t){return new int[] {i, j};}
//                 if (sum > t) {break;}
//             }
//         }
//         return new int[] {-1, -1};
//     }

// 2nd solution (sliding window):

public class Solution {
    public static int[] solution(int[] l, int t) { 
        int i1 = 0;                                     // subarray start index
        int i2 = 0;                                     // subarray end index
        int sum = l[0];                                 // sum of subarray elements
        
        while (i2 < l.length){
            if (sum == t) {return new int[] {i1, i2};}  // subarray sum matching target found
            
            if (sum < t || i1 == i2){                   // increase subarray size at its end
                ++i2;
                if (i2 < l.length) {sum += l[i2];}
            }
            else if (sum > t){                           // decrease subarray size at its start
                sum -= l[i1];
                ++i1;
            }
        }
        
        return new int[] {-1, -1};                      // no target matching subarray sum found
    }
}