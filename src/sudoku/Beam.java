package sudoku;

import java.util.ArrayList;

public class Beam {
	public ArrayList<String> beam(ArrayList<String> list){
		Hairetuka hairetu = new Hairetuka();
		Listka listka = new Listka();
		int check = 0;
		do {
			check = 0;
			int[][] num = hairetu.hairetuka(list);
			//問題を解くアルゴリズム
			//現在のマスの座標ループ（num[h][n]）
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
						//kariListにそのマスの候補を入れる
						ArrayList<Integer> kariList = new ArrayList<Integer>();
						for(int v = 0; v < numList.size(); v++) {
							if(numList.get(v) != 0) {
								kariList.add(numList.get(v));
							}
						}
						//ブロック内の埋まっている箇所は0にしてinBlo[]に格納
						int[] inBlo = {1,2,3,4,5,6,7,8,9};
						for(int k = n / 3 * 3; k < 3 * sn; k++) {
							for(int d = h / 3 * 3; d < 3 * sh; d++) {
								int yoko = 0;
								int tate = 0;
								int kariRes = 0;
								if(num[d][k] != 0) {
									yoko = d % 3;
									if(k == 0 || k == 3 || k == 6) {
										tate = 1;
									}else if(k == 1 || k == 4 || k == 7) {
										tate = 4;
									}else {
										tate = 7;
									}
									kariRes = tate + yoko;
								}
								for(int l = 0; l < 9; l++) {
									if(inBlo[l] == kariRes) {
										inBlo[l] = 0;
									}
								}
							}
						}
						//現在のマスに入る候補のループ
						for(int i = 0; i < kariList.size(); i++) {
							//現在のブロック内での場所を出してpointに格納
							int num1 = 0;
							int num2 = 0;
							int point = 0;
							if(h == 0 || h == 3 || h == 6) {
								num1 = 0;
							}else if(h == 1 || h == 4 || h == 7) {
								num1 = 1;
							}else {
								num1 = 2;
							}
							if(n == 0 || n == 3 || n == 6) {
								num2 = 0;
							}else if(n == 1 || n == 4 || n == 7) {
								num2 = 1;
							}else {
								num2 = 2;
							}
							if(num1 == 0 && num2 == 0) {
								point = 1;
							}else if(num1 == 1 && num2 == 0) {
								point = 2;
							}else if(num1 == 2 && num2 == 0) {
								point = 3;
							}else if(num1 == 0 && num2 == 1) {
								point = 4;
							}else if(num1 == 1 && num2 == 1) {
								point = 5;
							}else if(num1 == 2 && num2 == 1) {
								point = 6;
							}else if(num1 == 0 && num2 == 2) {
								point = 7;
							}else if(num1 == 1 && num2 == 2) {
								point = 8;
							}else{
								point = 9;
							}

							//point <= 現在のブロック内での場所
							int[] karikari = new int[9];
							for(int q = 0; q < 9; q++) {
								karikari[q] = inBlo[q];
							}
							for(int j = 0; j < 9; j++) {
								if(j + 1 == point) {
									continue;
								}else {
									if(karikari[j] != 0 && j + 1 != point) {

										int numb1 = 0;
										int numb2 = 0;
										int numb3 = 0;
										int numb4 = 0;
										int yoko = 0;
										int tate = 0;

										if(karikari[j] == 1) {
										}else if(karikari[j] == 2) {
											numb1 = 1;
										}else if(karikari[j] == 3) {
											numb1 = 2;
										}else if(karikari[j] == 4) {
											numb2 = 1;
										}else if(karikari[j] == 5) {
											numb1 = 1;
											numb2 = 1;
										}else if(karikari[j] == 6) {
											numb1 = 2;
											numb2 = 1;
										}else if(karikari[j] == 7) {
											numb2 = 2;
										}else if(karikari[j] == 8) {
											numb1 = 1;
											numb2 = 2;
										}else {
											numb1 = 2;
											numb2 = 2;
										}
										if(h > 2 && h < 6) {
											numb3 = 3;
										}else if(h > 5) {
											numb3 = 6;
										}
										if(n > 2 && n < 6) {
											numb4 = 3;
										}else if(n > 5) {
											numb4 = 6;
										}
										yoko = numb1 + numb3;
										tate = numb2 + numb4;

										for(int k = 0; k < 9; k++) {

											if(kariList.get(i).equals(num[k][tate])) {
												karikari[j] = 0;
											}
										}
										for(int f = 0; f < 9; f++) {
											if(kariList.get(i).equals(num[yoko][f])) {
												karikari[j] = 0;
											}
										}
									}

									int count = 0;
									for(int z = 0; z < 9; z++) {
										if(karikari[z] != 0) {
											count++;
										}
									}
									if(count == 1) {
										num[h][n] = kariList.get(i);
									}
								}
							}
						}
					}
				}
			}
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
