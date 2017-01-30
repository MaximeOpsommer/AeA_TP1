package structure.resolver;

import java.util.Set;

public class KMPResolver {

	private int[] next;
	private Set<Character> alphabet;
	private String dna;
	private String motif;

	public KMPResolver(String dna, Set<Character> alphabet) {
		this.dna = dna;
		this.alphabet = alphabet;
	}

	public void initNext(String motif) {
		this.setMotif(motif);
		this.next = new int[motif.length()];

		for (int i = 0; i < this.next.length; i++) {
			next[i] = best(i);
		}

	}

	public int best(int i) {
		char c = motif.charAt(i);
		int max = -1;
		String u;
		for (int j = 0; j < i; j++) {
			u = motif.substring(0, j);
			if (isBord(u, motif.substring(0, i))) {
				if (!isPrefix(u + c, this.motif)) {
					if (max < u.length()) {
						max = u.length();
					}
				}
			}

		}
		return max;
	}

	public boolean isBord(String u, String word) {
		String wordPref = word.substring(0, u.length());
		String wordSuf = word.substring(word.length() - u.length(), word.length());
		return u.equals(wordSuf) && u.equals(wordPref);
	}

	public boolean isPrefix(String u, String word) {
		return u.equals(word.substring(0, u.length()));
	}

	/*
	 * 
	 * GETTERS AND SETTERS
	 * 
	 */

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getMotif() {
		return motif;
	}

	public int[] getNext() {
		return next;
	}

	public void setNext(int[] next) {
		this.next = next;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public Set<Character> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(Set<Character> alphabet) {
		this.alphabet = alphabet;
	}

	public String nextToString() {
		String res = "{";
		for (int i = 0; i < this.next.length; i++) {
			res += next[i];
			if (i != this.next.length - 1) {
				res += ",";
			}
		}
		return res + "}";
	}

}
