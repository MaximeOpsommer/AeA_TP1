package main;

import java.io.File;
import java.util.Map;
import java.util.Set;

import io.Reader;
import io.Writer;
import structure.resolver.KMPResolver;
import structure.resolver.Resolver;

public class Main {
	
	public static void main(String[] args) {
		
		if(args.length != 5) {
			System.err.println("Le nombre d'arugments est incorrect");
			System.err.println("Veuillez indique le fichier, la taille N, ainsi que 3 booléens qui comptent comme occurrence respectivement l'inverse, le complémentaire et le complémentaire inverse");
		}
		
		try {
			
			File file = new File(args[0]);
			int tailleN = Integer.parseInt(args[1]);
			boolean inverse = Boolean.parseBoolean(args[2]);
			boolean complementaire = Boolean.parseBoolean(args[3]);
			boolean complementaireInverse = Boolean.parseBoolean(args[4]);
			
			Reader reader = new Reader(file.getPath());
			String texte = reader.readFasta();
			Resolver resolver = new KMPResolver(texte);
			
			Map<String, Set<Integer>> map = resolver.getOccurencesTousLesMotsDeTailleN(tailleN, inverse, complementaire, complementaireInverse);
			Writer writer = new Writer(map, file.getName().substring(0, file.getName().length() - 6));
			writer.writeFile();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
