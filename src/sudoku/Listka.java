package sudoku;

import java.util.ArrayList;

public class Listka {
	public ArrayList<String> listka(int[][] num){
		String line = "";
		ArrayList<String> lists = new ArrayList<String>();
		for(int tate = 0; tate < 9; tate++) {
			for(int yoko = 0; yoko < 9; yoko++) {
				line += String.valueOf(num[yoko][tate]);
			}
			lists.add(line);
			line = "";
		}
		return lists;
	}

}
