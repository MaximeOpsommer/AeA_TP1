package structure.resolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import structure.Mot;

public abstract class Resolver {
	
	protected final char[] alphabet;
	protected final char[] dna = new char[]{'A', 'C', 'G', 'T'};
	protected final char[] rna = new char[]{'A', 'C', 'G', 'U'};
	protected final String text;
	private int[] combinaisons;
	
	public Resolver(String text) throws Exception {
		this.text = text;
		if(text.contains("T"))
			this.alphabet = this.dna;
		else if(text.contains("U"))
			this.alphabet = this.rna;
		else
			throw new Exception("The fasta file is incorrect, there is no 'U' nor 'T'");
	}
	
	public char[] getAlphabet() {
		return this.alphabet;
	}
	
	public String getText() {
		return this.text;
	}
	
	private void combinaisonSuivante() {
		int max = this.alphabet.length  - 1;
		for(int i = this.combinaisons.length - 1; i >= 0; i--) {
			if(this.combinaisons[i] == max)
				this.combinaisons[i] = 0;
			else {
				this.combinaisons[i]++;
				break;
			}
		}
	}
	
	private String getCombinaison() {
		StringBuilder builder = new StringBuilder();
		for(int i : this.combinaisons)
			builder.append(this.alphabet[i]);
		return builder.toString();
	}
	
	private boolean estDerniereCombinaison() {
		for(int i : this.combinaisons) {
			if(i < (this.alphabet.length -1))
				return false;
		}
		return true;
	}
	
	/**
	 * Etablit la liste de tous les mots de taille N avec les modes de recherches indiques en parametres
	 * @param n : taille du mot
	 * @param inverse : booleen indiquant si l'inverse est aussi une occurence du mot ou non
	 * @param complementaire : booleen indiquant si le complementaire est aussi une occurence du mot ou non
	 * @param complementaireInverse : booleen indiquant si le complementaire inverse est aussi une occurence du mot ou non
	 * @return La liste des mots avec les modes de recherches indiques en parametres
	 */
	public List<String> getMotsDeTailleN(int n, boolean inverse, boolean complementaire, boolean complementaireInverse) {
		List<String> res = new ArrayList<String>();
		this.combinaisons = new int[n];
		Mot mot;
		while(!this.estDerniereCombinaison()) {
			mot = new Mot(this.getCombinaison());
			if(!res.contains(mot.getSequence())
					&& (!inverse || (inverse && !res.contains(mot.getSequenceInverse())))
					&& (!complementaire || (complementaire && !res.contains(mot.getSequenceComplementaire())))
					&& (!complementaireInverse || (complementaireInverse && !res.contains(mot.getSequenceComplementaireInverse()))))
				res.add(mot.getSequence());
			this.combinaisonSuivante();
		}
		mot = new Mot(this.getCombinaison());
		if(!res.contains(mot.getSequence())
				&& (!inverse || (inverse && !res.contains(mot.getSequenceInverse())))
				&& (!complementaire || (complementaire && !res.contains(mot.getSequenceComplementaire())))
				&& (!complementaireInverse || (complementaireInverse && !res.contains(mot.getSequenceComplementaireInverse()))))
			res.add(mot.getSequence());
		return res;
	}
	
	public Map<String, Set<Integer>> getOccurencesTousLesMotsDeTailleN(int n, boolean inverse, boolean complementaire, boolean complementaireInverse) {
		Map<String, Set<Integer>> res = new HashMap<String, Set<Integer>>();
		
		List<String> motsDeTailleN = this.getMotsDeTailleN(n, inverse, complementaire, complementaireInverse);
		
		Mot mot;
		for(String str : motsDeTailleN) {
			mot = new Mot(str);
			res.put(str, new HashSet<Integer>());
			res.get(str).addAll(this.getIndexOccurences(str));
			if(inverse)
				res.get(str).addAll(this.getIndexOccurences(mot.getSequenceInverse()));
			if(complementaire)
				res.get(str).addAll(this.getIndexOccurences(mot.getSequenceComplementaire()));
			if(complementaireInverse)
				res.get(str).addAll(this.getIndexOccurences(mot.getSequenceComplementaireInverse()));
		}
		
		while(res.values().remove(new HashSet<Integer>()));
		
		return res;
	}
	
	public abstract List<Integer> getIndexOccurences(String motif);

}
