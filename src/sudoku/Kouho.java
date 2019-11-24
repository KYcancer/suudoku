package sudoku;

import java.util.ArrayList;
import java.util.HashMap;

public class Kouho {
	public ArrayList<String> kouho(ArrayList<String> list) {

		Hairetuka hairetu = new Hairetuka();
		Listka listka = new Listka();
		int check = 0;

		do {
			check = 0;
			//listを配列に格納
			int[][] num = hairetu.hairetuka(list);

			//問題を解くアルゴリズム
			HashMap<Integer,String> map = new HashMap<Integer, String>();
			for(int n = 0; n < 9; n++) {
				for(int h = 0; h < 9; h++) {
					if(num[h][n] == 0) {
						//num[n][h]に入る候補を作成(1つのブロック内)
						int[] block = {1,2,3,4,5,6,7,8,9};
						int sn = n / 3 + 1;
						int sh = h / 3 + 1;
						for(int k = n / 3 * 3; k < 3 * sn; k++) {
							for(int d = h / 3 * 3; d < 3 * sh; d++) {
								if(num[d][k] != 0) {
									for(int l = 0; l < 9; l++) {
										if(block[l] == num[d][k]) {
											block[l] = 0;
										}
									}
								}
							}
						}
						ArrayList<Integer> numList = new ArrayList<Integer>();
						for(int t = 0; t < 9; t++) {
							if(block[t] != 0) {
								numList.add(block[t]);
							}
						}
						//↑まででnumListにブロック内にある数字は除外した候補を格納した
						//さらに縦と横からも除外する
						for(int o = 0; o < 9; o++) {
							for(int e = 0; e < numList.size(); e++) {
								if(numList.get(e) == num[o][n]) {
									numList.set(e,0);
								}
							}
						}
						for(int a = 0; a < 9; a++) {
							for(int x = 0; x < numList.size(); x++) {
								if(numList.get(x) == num[h][a]) {
									numList.set(x,0);
								}
							}
						}
						int kari = 0;
						ArrayList<Integer> kariList = new ArrayList<Integer>();
						for(int v = 0; v < numList.size(); v++) {
							if(numList.get(v) != 0) {
								kariList.add(numList.get(v));
							}
						}

						int karinum = n * 9 +1 + h;
						String line = "";

						//候補が一つならnum[h][n]に代入
						for(int i = 0; i < kariList.size(); i++) {
							if(line.equals("")) {
								line += String.valueOf(kariList.get(i));
							}else {
								line += ",";
								line += String.valueOf(kariList.get(i));
							}
						}

						//mapにkeyは左上から右に、終わったら次の段にという形で81までの値を入れてvalueに候補を,区切りで格納
						map.put(karinum, line);
					}
				}
			}
			System.out.println("それぞれの場所の候補");
			for(int i = 0; i < 9; i++) {
				System.out.println((i + 1) + "段目");
				for(int j = 0; j < 9; j++) {
					int karinum = i * 9 + 1 + j;
					if(map.get(karinum) != null) {
						System.out.println(karinum + "の場所＞" + map.get(karinum));
					}
				}
			}
			//配列をListに戻す
			ArrayList<String> lists = new ArrayList<String>();
			lists = listka.listka(num);

			//ループチェック
			for(int q = 0; q < 9; q++) {
				if(!(list.get(q).equals(lists.get(q)))) {
					check = 1;
				}
			}
			list = (ArrayList<String>)lists.clone();
		}while(check == 1);

		return list;
	}
}
