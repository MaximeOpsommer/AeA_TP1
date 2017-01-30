package structure.resolver;

import java.util.HashSet;
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

	public void buildMatrice(String motif) {
		
		Character[] lettres = new Character[this.alphabet.size()];
		this.alphabet.toArray(lettres);
		
		this.init(motif, lettres);
		
		int[] colonne = new int[motif.length()];
		
		/* Première colonne */
		if(this.RNA.charAt(0) == motif.charAt(0))
			colonne[0] = 1;
		else
			colonne[0] = 0;
		for(int i = 1; i < colonne.length; i++)
			colonne[i] = 0;
		this.matrice[0] = colonne;
		
		/* calcul des colonnes suivantes */
		for(int i = 1; i < this.matrice.length; i++) {
			colonne = this.colonneSuivante(colonne, i, lettres);
			this.matrice[i] = colonne;
		}
		
	}
	
	public void init(String motif, Character[] lettres) {
		this.matrice = new int[this.RNA.length()][motif.length()];
		this.vecteurs = new int[this.alphabet.size()][motif.length()];
		
		// mettre les indices des occurences des lettres du mot à 1
		for(int j = 0; j < vecteurs[0].length; j++) {
			for(int i = 0; i < vecteurs.length; i++) {
				if(motif.charAt(j) == lettres[i])
					vecteurs[j][i] = 1;
				else
					vecteurs[j][i] = 0;
			}
		}
	}
	
	public int[] colonneSuivante(int[] colonneActuelle, int index, Character[] lettres) {
		int[] res = new int[colonneActuelle.length];
		
		int[] col1 = this.shift(colonneActuelle);
		char c = this.RNA.charAt(index);
		int indexVecteur;
		int i = 0;
		while(i < lettres.length && !lettres[i].equals(c))
			i++;
		int[] col2 = this.vecteurs[i];
		
		return this.AND(col1, col2);
	}
	
	public int[] shift(int[] colonne) {
		int[] res = new int[colonne.length];
		res[0] = 1;
		for(int i = 1; i < res.length; i++)
			res[i] = colonne[i - 1];
		
		return res;
	}
	
	public int[] AND(int[] col1, int[] col2) {
		/* On suppose que les 2 colonnes ont la même longueur */
		int[] res = new int[col1.length];
		for(int i = 0; i < res.length; i++) {
			res[i] = (col1[i] == 1 && col2[i] == 1) ? 1 : 0;
		}
		return res;
	}
	
	public static void main(String[] args) {
		Set<Character> alphabet = new HashSet<Character>();
		alphabet.add('a');
		alphabet.add('c');
		alphabet.add('g');
		alphabet.add('t');
		ShiftOrResolver resolver = new ShiftOrResolver("ctactatatatc", alphabet);
		resolver.buildMatrice("tata");
		
		int[][] matrice = resolver.getMatrice();
		for(int i = 0; i < matrice[0].length; i++) {
			for(int j = 0; j < matrice.length; j++) {
				System.out.print(matrice[j][i]);
			}
			System.out.println();
		}
	}

}
