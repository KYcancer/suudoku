package sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sudoku {
	public static void main(String[] args) {

		TateYokoBlock tyb = new TateYokoBlock();
		Beam beam = new Beam();
		KouhoDelete koDe = new KouhoDelete();
		Kouho kouho = new Kouho();
		ArrayList<String> list = new ArrayList<String>();
		int check = 0;
		int checks = 0;
		do {
			checks = 0;
			//問題の入力
			list = que();
			//問題を解く
			do {
				check = 0;
				ArrayList<String> lists = new ArrayList<String>();
				lists = tyb.tateyoko(list);
				lists = beam.beam(lists);
				lists = koDe.kouhoDelete(lists);
				//ループチェック
				for(int q = 0; q < 9; q++) {
					if(!(list.get(q).equals(lists.get(q)))) {
						check = 1;
					}
				}
				list = (ArrayList<String>)lists.clone();
			}while(check == 1);
			//表示
			//list = kouho.kouho(list);
			view(list);
			//結果表示
			String result = "";
			for(int g = 0; g < 9; g++) {
				if(list.get(g).contains("0")) {
					result = "今の段階では解けません。問題を解くアルゴリズムを追加してください。";
				}else {
					result = "解けました";
				}
			}
			System.out.println(result);
			while(true) {
				System.out.println("次の問題を解きますか？");
				System.out.print("y/n:＞");
				Scanner scnn = new Scanner(System.in);
				String str = scnn.nextLine();

				if(!(str.equals("y") || str.equals("n"))) {
					System.out.println("yかnを入力してください。");
				}else {
					if(str.equals("y")) {
						checks = 0;
						break;
					}else {
						checks = 1;
						break;
					}
				}
			}
		}while(checks == 0);
	}


	//結果表示メソッド
	public static void view(List<String> list) {
		for(int i = 0; i < 9; i++) {
			if(i == 3 || i == 6) {
				System.out.println("----------------------");
			}
			String[] split = list.get(i).split("");
			for(int j = 0; j < 9; j++) {
				if(j == 3 || j == 6) {
					System.out.print(" |");
				}
				System.out.print(" ");
				if(split[j].equals("0")) {
					System.out.print("･");
				}else {
					System.out.print(split[j]);
				}
			}
			System.out.println();
		}
	}


	//問題入力メソッド
	public static ArrayList<String> que(){
		ArrayList<String> list = new ArrayList<String>();
		System.out.println("左から順に上の段から入力してください。(空白は0を入力してください)");
		for(int i = 0; i < 9; i++) {
			String num;
			while(true) {
				System.out.print(i + 1 + "段目　＞");
				Scanner scn = new Scanner(System.in);
				String nums = scn.nextLine();
				if(nums.matches("\\d{9}")) {
					num = nums;
					break;
				}else {
					System.out.println("半角数字9字で入力してください。");
				}
			}
			list.add(num);
		}


		/*list.add("080020094");
		list.add("900400618");
		list.add("100809052");
		list.add("800000269");
		list.add("500900873");
		list.add("093078541");
		list.add("450090180");
		list.add("009580420");
		list.add("008004935");*/

		while(true) {
			view(list);
			System.out.println("これで合っていますか？");
			System.out.print("y/n:＞");
			Scanner scnn = new Scanner(System.in);
			String str = scnn.nextLine();
			if(!(str.equals("y") || str.equals("n"))) {
				System.out.println("yかnを入力してください。");
			}else {
				if(str.equals("y")) {
					break;
				}else {
					view(list);
					while(true) {
						System.out.print("何段目が違いますか？　:＞");
						Scanner scnne = new Scanner(System.in);
						String stri = scnne.nextLine();
						if(stri.matches("\\d{1}")) {
							System.out.print("ではもう一度");
							while(true) {
								System.out.print(stri + "段目を入力してください。:＞");
								Scanner sca = new Scanner(System.in);
								String nu = sca.nextLine();
								int cn = Integer.parseInt(stri);
								if(nu.matches("\\d{9}")) {
									list.set(cn - 1,nu);
									break;
								}else {
									System.out.println("半角数字9字で入力してください。");
								}
							}
							break;
						}else {
							System.out.println("半角数字1文字で入力してください。");
						}
					}
				}
			}
		}
		return list;
	}
}
