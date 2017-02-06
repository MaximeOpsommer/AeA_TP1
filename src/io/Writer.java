package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Writer {
	
	private Map<String, Set<Integer>> map;
	private String originalFileName;
	
	public Writer(Map<String, Set<Integer>> map, String originalFileName) {
		this.map = map;
		this.originalFileName = originalFileName;
	}
	
	public String generateDataFileFromMap() {
		StringBuilder builder = new StringBuilder();
		List<Integer> list;
		int size;
		
		for(Map.Entry<String, Set<Integer>> entry : this.map.entrySet()) {
			list = new ArrayList<Integer>();
			list.addAll(entry.getValue());
			size = list.size();
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++)
					builder.append(list.get(i) + " " + list.get(j) + "\n");
			}
		}
		
		return builder.toString();
	}

	public void writeFile() {
		try {
			File f = new File("data-plot/" + this.originalFileName + ".data");
			FileWriter fw = new FileWriter(f);
			fw.write(this.generateDataFileFromMap());
			f.createNewFile();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
