package structure.resolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KMPResolver extends Resolver {
	
	private int[] next;

	public KMPResolver(String text) throws Exception {
		super(text);
	}

	@Override
	public List<Integer> getIndexOccurences(String motif) {
		this.init(motif);
		
		List<Integer> res = new ArrayList<Integer>();
		
		int limit = super.text.length() - motif.length();
		int tmp;
		for(int i = 0; i <= limit; i++) {
			tmp = checkMotif(motif, i);
			if(tmp == motif.length())
				res.add(i);
			else
				i += (tmp - this.next[tmp]) - 1;
		}
		
		return res;
	}
	
	public int checkMotif(String motif, int position) {
		for(int i = 0; i < motif.length(); i++) {
			if(super.text.charAt(position + i) != motif.charAt(i))
				return i;
		}
		return motif.length();
	}

	@Override
	protected void init(String motif) {
		this.next = new int[motif.length() + 1];
		this.next[0] = -1;
		for (int i = 1; i < this.next.length; i++)
			next[i] = best(motif + ' ', i);
	}

	private int best(String motif, int i) {
		String string = motif.substring(0, i);
		Set<String> bords = listeBords(string);
		int max = -1;
		for(String str : bords) {
			int tmp = str.length();
			if(!motif.startsWith(str + motif.charAt(i)) && tmp > max)
				max = tmp;
		}
		return max;
	}
	
	private Set<String> listeBords(String string) {
		Set<String> res = new HashSet<String>();
		String substring;
		for(int i = 0; i < string.length(); i++) {
			substring = string.substring(0, i);
			if(estUnBord(string, substring))
					res.add(substring);
		}
		return res;
	}
	
	private boolean estUnBord(String string, String substring) {
		return string.startsWith(substring) && string.endsWith(substring);
	}

}
