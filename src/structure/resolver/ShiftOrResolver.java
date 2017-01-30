package structure.resolver;

public class ShiftOrResolver {
	
	private char[] alphabet;
	private final String RNA;
	private int[][] vecteurs;
	private int[][] matrice;
	
	public ShiftOrResolver(String RNA) {
		this.RNA = RNA;
		this.alphabet = new char[]{'A', 'C', 'G', 'U'};
	}
	
	public char[] getAlphabet() {
		return this.alphabet;
	}
	
	public String getRNA() {
		return this.RNA;
	}
	
	public int[][] getVecteurs() {
		return this.vecteurs;
	}
	
	public int[][] getMatrice() {
		return this.matrice;
	}

	public void buildMatrice(String motif) {
		
		this.init(motif);
		
		int[] colonne = new int[motif.length()];
		
		/* Premi√®re colonne */
		if(this.RNA.charAt(0) == motif.charAt(0))
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
	
	public void init(String motif) {
		this.matrice = new int[this.RNA.length()][motif.length()];
		this.vecteurs = new int[this.alphabet.length][motif.length()];
		
		// mettre les indices des occurences des lettres du mot ‡ 1
		for(int j = 0; j < motif.length(); j++) {
			for(int i = 0; i < this.alphabet.length; i++) {
				if(motif.charAt(j) == this.alphabet[i])
					vecteurs[i][j] = 1;
				else
					vecteurs[i][j] = 0;
			}
		}
	}
	
	public int[] colonneSuivante(int[] colonneActuelle, int index) {
		int[] col1 = this.shift(colonneActuelle);
		char c = this.RNA.charAt(index);
		int i = 0;
		while(i < this.alphabet.length && this.alphabet[i] != c)
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
		/* On suppose que les 2 colonnes ont la mÍme longueur */
		int[] res = new int[col1.length];
		for(int i = 0; i < res.length; i++) {
			res[i] = (col1[i] == 1 && col2[i] == 1) ? 1 : 0;
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		ShiftOrResolver resolver = new ShiftOrResolver("CUACUAUAUAUC");
		resolver.buildMatrice("UAUA");
		
		int[][] matrice = resolver.getMatrice();
		for(int i = 0; i < matrice[0].length; i++) {
			for(int j = 0; j < matrice.length; j++) {
				System.out.print(matrice[j][i]);
			}
			System.out.println();
		}
	}

}
