package structure;

public class Mot implements Comparable<Mot> {
	
	private String sequence;
	private String sequenceInverse;
	private String sequenceComplementaire;
	private String sequenceComplementaireInverse;
	
	public Mot(String sequence) {
		this.sequence = sequence;
		this.sequenceInverse = new StringBuilder().append(sequence).reverse().toString();
		this.sequenceComplementaire = this.convertirEnSequenceComplementaire(sequence);
		this.sequenceComplementaireInverse = new StringBuilder().append(this.sequenceComplementaire).reverse().toString();
	}
	
	public String getSequence() {
		return this.sequence;
	}
	
	public String getSequenceInverse() {
		return this.sequenceInverse;
	}
	
	public String getSequenceComplementaire() {
		return this.sequenceComplementaire;
	}
	
	public String getSequenceComplementaireInverse() {
		return this.sequenceComplementaireInverse;
	}
	
	private String convertirEnSequenceComplementaire(String sequence) {
		StringBuilder builder = new StringBuilder();
		for(char c : sequence.toCharArray())
			builder.append(RNA.valueOf(c + "").getOpposite());
		return builder.toString();
	}

	public int compareTo(Mot mot) {
		return this.sequence.compareTo(mot.getSequence());
	}

}
