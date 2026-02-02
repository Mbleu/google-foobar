import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;

public class Solution {
	private static int choose(final int n, final int k) // calculate combinations
	{
		int numerator = 1;
		int denominator = 1;
		for (int i = 0; i < k; ++i) {
			numerator *= (n - i);
			denominator *= (k - i);
		}
		return numerator / denominator;
	}
	
	public static int[][] solution(final int num_buns, final int num_required)
	{
		if (num_required == 0) return new int[num_buns][];              // no bunnies required
		
		BigInteger[] keyring = new BigInteger[num_buns];                // set of keys for each bunny
		Arrays.fill(keyring, BigInteger.ZERO);
		
		int unique_keys = choose(num_buns, num_required - 1);
		int copies_per_key = num_buns - num_required + 1;
		int keys_per_ring = unique_keys * copies_per_key / num_buns;
		
		// Distribute all the copies of each key:
		for (int i = 0; i < unique_keys; ++i) {
			BigInteger key = BigInteger.ZERO;
			key = key.setBit(i);                                                                        // key mask for checking if a keyring contains the key
			
			// Calculate combinations of distributing key copies to distinct bunnies:
			ArrayList<BigInteger> combos = new ArrayList<>();                                           // distribution combos for the key copies
			for (BigInteger j = BigInteger.ZERO; j.compareTo(BigInteger.ZERO.setBit(num_buns)) < 0; j = j.add(BigInteger.ONE)) {
				if (j.bitCount() == copies_per_key) {                                                   // valid combos distribute all the keys
					BigInteger matching_keys = BigInteger.ZERO.setBit(128).subtract(BigInteger.ONE);
					for (int k = 0; k < num_buns; ++k)
						if (j.testBit(k))                                                               // check other unique keys on keyrings of this combo
							matching_keys = matching_keys.and(keyring[k]);
					
					if (matching_keys.equals(BigInteger.ZERO)) combos.add(j);                           // valid combos can't distribute copies such that they are paired with all copies of another unique key
				}                                                                                       // this is because it would render one of the two redundant
			}
			// Reverse the valid combos (not strictly necessary but it makes comparison easy, since we can then take the largest):
			// We're endeavouring to find the combo that distributes to the least significant bunnies:
			ArrayList<BigInteger> reversed_combos = new ArrayList<>(combos);
			for (int j = 0; j < reversed_combos.size(); ++j) {
				BigInteger reverse = BigInteger.ZERO;
				for (int k = 0; k < 128; ++k) {
					if (reversed_combos.get(j).testBit(k))
						reverse = reverse.setBit(128 - k);
				}
				reversed_combos.set(j, reverse);
			}
			// Find the largest reversed combo:
			BigInteger largest = BigInteger.ZERO;
			int index = 0;
			for (int j = 0; j < reversed_combos.size(); ++j)
				if (reversed_combos.get(j).compareTo(largest) > 0) {
					largest = reversed_combos.get(j);
					index = j;
				}
			// Match the index to the original combo and distribute the key copies accordingly:
			for (int j = 0; j < num_buns; ++j)
				if (combos.get(index).testBit(j))
					keyring[j] = keyring[j].or(key);
		}
		
		// Translate from bit patterns to integer arrays:
		int[][] distribution = new int[num_buns][keys_per_ring];
		for (int i = 0; i < num_buns; ++i) {
			int j = 0;
			for (int k = 0; k < keyring[i].bitLength(); ++k) {
				if (keyring[i].testBit(k)) {
					distribution[i][j] = k;
					++j;
				}
			}
		}
		
		return distribution;
	}
}