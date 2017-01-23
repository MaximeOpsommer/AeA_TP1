package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Reader {
	
	private BufferedReader buffer;
	private String file;
	private Set<Character> alphabet;
	
	public Reader(String file){
		this.file = file;
		this.alphabet = new HashSet<Character>();
	}
	
	/**
	 * Read a ".fasta" file. Returns the DNA sequence
	 * @return String
	 * @throws IOException
	 */
	public String readFasta() throws IOException{
		FileReader fr = new FileReader(this.file);
		StringBuilder builder = new StringBuilder();
		buffer = new BufferedReader(fr);
		String line;
		buffer.readLine(); //first line is not an DNA sequence
	    while ((line = buffer.readLine()) != null) {
	    	builder.append(line);
	    }
	    return builder.toString();
	}
	
	public void checkIfNewLetters(String line) {
		for(char c : line.toCharArray())
			alphabet.add(c);
	}

	public BufferedReader getBuffer() {
		return buffer;
	}

	public void setBuffer(BufferedReader buffer) {
		this.buffer = buffer;
	}

	public String getFile() {
		return file;
	}
	
	public Set<Character> getAlphabet() {
		return this.alphabet;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	
}
