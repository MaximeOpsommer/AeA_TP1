package structure.resolver;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class KMPResolverTest {
	
	private KMPResolver kmp;
	
	@Before
	public void init(){
		Set<Character> alphabet = new HashSet<Character>();
		alphabet.add('A');
		alphabet.add('T');
		alphabet.add('C');
		alphabet.add('G');
		
		this.kmp = new KMPResolver("TACTAGATACTAC", alphabet);
	}
	
	@Test
	public void nextTabInit1(){
		kmp.setMotif("TAGTAG");
		int[] expectedNext = {-1,0,0,-1,0,0,3};
		
		Assert.assertEquals(7, kmp.getNext().length);
		Assert.assertArrayEquals(expectedNext, kmp.getNext());
		
	}
	
	@Test
	public void nextTabInit2(){
		kmp.setMotif("TACTAGA");
		int[] expectedNext = {-1,0,0,-1,0,2,0,0};
		
		Assert.assertEquals(8, kmp.getNext().length);
		Assert.assertArrayEquals(expectedNext, kmp.getNext());
		
	}
	
}
