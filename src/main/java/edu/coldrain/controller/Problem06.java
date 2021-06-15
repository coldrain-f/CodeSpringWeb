package edu.coldrain.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem06 {
	
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]) {

		//정답을 담을 List 선언
		final List<Integer> answer = new ArrayList<Integer>();
		
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			//숫자를 하나 입력받는다.
			String item = sc.next();
			
			//뒤집어서 변수에 담는다.
			int number = 0;
			for (int j = item.length() - 1; j >= 0; j--) {
				number = (number * 10)  + (item.charAt(j) - 48);
			}
			
			//뒤집은 숫자가 소수인지 판별한다.
			int primeCount = 0;
			for (int k = 2; k < number; k++) {
				if (number % k == 0) {
					++primeCount;
					break;
				}
			} //뒤집은 숫자가 소수라면 정답에 집어넣는다. ( 1은 소수가 아니다. )
			if (primeCount == 0 && number != 1) {
				answer.add(number);
			}
		}
		
		answer.forEach(item -> System.out.print(item + " "));
		
		
	}

	private static void test1() {
		String number = "32";
		int result = 0;
		
		//뒤집어서 출력한다.
		for (int i = number.length() - 1; i >= 0; i--) {
			System.out.printf("%c ", number.charAt(i));
		}
		System.out.println();
	
		//정수로 변경해서 집어넣는다. ( "019"를 19로 )
		for (int i = number.length() - 1; i >= 0; i--) {
			result = (result * 10) + (number.charAt(i) - 48);
		}
		
		//소수인지 판별한다.
		int count = 0;
		for (int i = 2; i < result; i++) {
			if (result % i == 0) {
				++count;
				if (count > 0) {
					break;
				}
			}
		}
		if (count == 0) {
			System.out.println(result +"는 소수입니다.");
		}
		
		System.out.println("result = " + result);
	}

}
