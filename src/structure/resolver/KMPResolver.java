package structure.resolver;

import java.util.Set;

public class KMPResolver {

	private int[] next;
	private Set<Character> alphabet;
	private String dna;
	private String motif;
	
	public KMPResolver(String dna, Set<Character> alphabet){
		this.dna = dna;
		this.alphabet = alphabet;
		initNext("");
	}
	
	private void initNext(String motif){
		this.motif = motif;
		this.next = new int[motif.length()];
		this.next[0] = -1;
		
		for(int i = 1;i < this.next.length;i++){
			//TODO init Next
		}
		
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

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
		initNext(motif);
	}

	
}
