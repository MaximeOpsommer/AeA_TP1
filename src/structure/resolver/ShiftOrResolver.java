package structure.resolver;

import java.util.Set;

public class ShiftOrResolver {
	
	private final String RNA;
	private final Set<Character> alphabet;
	private int[][] vecteurs;
	private int[][] matrice;
	
	public ShiftOrResolver(String RNA, Set<Character> alphabet) {
		this.RNA = RNA;
		this.alphabet = alphabet;
	}
	
	public String getRNA() {
		return this.RNA;
	}
	
	public Set<Character> getAlphabet() {
		return this.alphabet;
	}
	
	public int[][] getVecteurs() {
		return this.vecteurs;
	}
	
	public int[][] getMatrice() {
		return this.matrice;
	}
	
	public void init(String mot) {
		this.matrice = new int[mot.length()][this.RNA.length()];
		this.vecteurs = new int[this.alphabet.size()][mot.length()];
		
		// mettre les indices des occurences des lettres du mot à 1
	}

}
