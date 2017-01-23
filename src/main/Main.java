package main;

import java.util.Set;

import io.Reader;

public class Main {
	
	public static void main(String[] args) {
		Reader r = new Reader("data-mirna/ARNmessager-1.fasta");
		Set<Character> alphabet = r.getAlphabet();
		try {
			System.out.println(r.read());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
