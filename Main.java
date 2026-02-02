public class Main {
	public static void main(String[] args){
		int[][] distribution = Solution.solution(9, 6);
		
		for (int i = 0; i < distribution.length; ++i){
			System.out.print("Bunny " + i + ": [ ");
			for (int j = 0; j < distribution[i].length; ++j){
				if (distribution[i][j] < 100) System.out.print(' ');
				if (distribution[i][j] < 10) System.out.print(' ');
				System.out.print(distribution[i][j]);
				if (j + 1 < distribution[i].length) {
					System.out.print(", ");
				}
			}
			System.out.print(" ]\n");
		}
	}
}
