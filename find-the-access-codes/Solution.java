public class Solution {
    public static int solution(int[] l) {
        if (l.length < 3) {return 0;}
        int tuples = 0;
        
        for(int i = 0; i < l.length; ++i){
            for(int j = i + 1; j < l.length; ++j){
                if (l[j] % l[i] == 0){
                    for(int k = j + 1; k < l.length; ++k){
                        if (l[k] % l[j] == 0) {++tuples;}
                    }
                }
            }
        }
        
        return tuples;
    }
}