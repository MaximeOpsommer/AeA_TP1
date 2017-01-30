package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	
	private BufferedReader buffer;
	private String file;
	
	public Reader(String file){
		this.file = file;
	}
	
	/**
	 * Read a ".fasta" file. Returns the RNA sequence
	 * @return String
	 * @throws IOException
	 */
	public String readFasta() throws IOException{
		FileReader fr = new FileReader(this.file);
		StringBuilder builder = new StringBuilder();
		buffer = new BufferedReader(fr);
		String line, res;
		buffer.readLine(); //first line is not an DNA sequence
	    while ((line = buffer.readLine()) != null) {
	    	builder.append(line);
	    }
	    res = builder.toString();
	    return res;
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

	public void setFile(String file) {
		this.file = file;
	}
	
	
}
