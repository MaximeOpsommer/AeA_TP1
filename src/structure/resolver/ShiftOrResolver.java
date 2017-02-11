package structure.resolver;

import io.Reader;
import io.Writer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShiftOrResolver extends Resolver {
	
	private int[][] vecteurs;
	private int[][] matrice;
	
	public ShiftOrResolver(String text) throws Exception {
		super(text);
	}
	
	public int[][] getVecteurs() {
		return this.vecteurs;
	}
	
	public int[][] getMatrice() {
		return this.matrice;
	}

	private void buildMatrice(String motif) {
		
		this.init(motif);
		
		int[] colonne = new int[motif.length()];
		
		/* Premiere colonne */
		if(super.text.charAt(0) == motif.charAt(0))
			colonne[0] = 1;
		else
			colonne[0] = 0;
		for(int i = 1; i < colonne.length; i++)
			colonne[i] = 0;
		this.matrice[0] = colonne;
		
		/* calcul des colonnes suivantes */
		for(int i = 1; i < this.matrice.length; i++) {
			colonne = this.colonneSuivante(colonne, i);
			this.matrice[i] = colonne;
		}
		
	}
	
	protected void init(String motif) {
		this.matrice = new int[super.text.length()][motif.length()];
		this.vecteurs = new int[this.alphabet.length][motif.length()];
		
		// mettre les indices des occurences des lettres du mot a 1
		for(int j = 0; j < motif.length(); j++) {
			for(int i = 0; i < this.alphabet.length; i++) {
				if(motif.charAt(j) == this.alphabet[i])
					vecteurs[i][j] = 1;
				else
					vecteurs[i][j] = 0;
			}
		}
	}
	
	private int[] colonneSuivante(int[] colonneActuelle, int index) {
		int[] col1 = this.shift(colonneActuelle);
		char c = super.text.charAt(index);
		int i = 0;
		while(i < this.alphabet.length && this.alphabet[i] != c)
			i++;
		int[] col2 = this.vecteurs[i];
		
		return this.AND(col1, col2);
	}
	
	private int[] shift(int[] colonne) {
		int[] res = new int[colonne.length];
		res[0] = 1;
		for(int i = 1; i < res.length; i++)
			res[i] = colonne[i - 1];
		
		return res;
	}
	
	public int[] AND(int[] col1, int[] col2) {
		/* On suppose que les 2 colonnes ont la meme longueur */
		int[] res = new int[col1.length];
		for(int i = 0; i < res.length; i++) {
			res[i] = (col1[i] == 1 && col2[i] == 1) ? 1 : 0;
		}
		
		return res;
	}
	
	public List<Integer> getIndexOccurences(String motif) {
		List<Integer> res = new ArrayList<Integer>();
		
		this.buildMatrice(motif);
		
		int tmp = motif.length() - 1;
		
		for(int i = tmp; i < super.text.length(); i++) {
			if(this.matrice[i][tmp] == 1)
				res.add(i - tmp);
		}
		
		return res;
	}

}
