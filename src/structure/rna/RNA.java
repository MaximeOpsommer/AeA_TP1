package structure.rna;

public enum RNA {
	
	A('U'),
	C('G'),
	G('C'),
	U('A');
	
	private char opposite;
	
	RNA(char opposite) {
		this.opposite = opposite;
	}
	
	public char getOpposite() {
		return this.opposite;
	}

}
