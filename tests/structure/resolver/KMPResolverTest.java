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
		kmp.initNext("TAGTAG");
		int[] expectedNext = {-1,0,0,-1,0,0};
		
		Assert.assertEquals(6, kmp.getNext().length);
		Assert.assertArrayEquals(expectedNext, kmp.getNext());
		
	}
	
	@Test
	public void nextTabInit2(){
		kmp.initNext("TACTAGA");
		int[] expectedNext = {-1,0,0,-1,0,2,0};
		
		Assert.assertEquals(7, kmp.getNext().length);
		Assert.assertArrayEquals(expectedNext, kmp.getNext());
		
	}
	
	@Test
	public void isBordTest(){
		Assert.assertTrue(kmp.isBord("TA", "TACCCCCCTA"));
		Assert.assertTrue(kmp.isBord("", "TACCCCCCTA"));
		Assert.assertFalse(kmp.isBord("G", "TACCCCCCTA"));
		
	}
	
	@Test
	public void isPrefixTest(){
		Assert.assertTrue(kmp.isPrefix("TA", "TAGGGG"));
		Assert.assertTrue(kmp.isPrefix("", "TAGGGG"));
		Assert.assertFalse(kmp.isPrefix("GG", "TAGGGG"));
	}
	
}
