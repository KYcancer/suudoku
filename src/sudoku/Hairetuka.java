package sudoku;

import java.util.ArrayList;

public class Hairetuka {
	public int[][] hairetuka(ArrayList<String> list) {

		int[][] num = new int[9][9];
		for(int i = 0; i < 9; i++) {
			String[] split = list.get(i).split("");
			for(int j = 0; j < 9; j++) {
				num[j][i] = Integer.parseInt(split[j]);
			}
		}
		return num;
	}
}
