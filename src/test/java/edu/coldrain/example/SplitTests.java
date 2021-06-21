package edu.coldrain.example;

import lombok.extern.log4j.Log4j;

@Log4j
public class SplitTests {

	public static void main(String[] args) {
		String type = "TWC";
		
		String[] typeArr = type.split("");
		
		for (String token : typeArr) {
			System.out.println(token);
		}
		
	}
	
}
