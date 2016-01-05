package pl.edu.agh;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.Test;

public class HeapTests {

	@Test
	public void add_IntegerTest() {
		Heap<Integer> h = new Heap<>();
		addInt(h);
		checkIsTrue(h);
	}
	
	private void addInt(Heap<Integer> h)
	{
		Random r = new Random();
		int ile = r.nextInt(100)+10;
		for(int i=0;i<ile;i++)
		{
			h.add(r.nextInt(100));
		}
	}
	
	private <T extends Comparable<T>> void checkIsTrue(Heap<T> h)
	{
		for(int i=0;i<h.getHeap().size();i++)
		{
			if(((i*2)+1)<h.getHeap().size())
				assertTrue(h.get(i).compareTo(h.get((i*2)+1))>=0);
			if(((i*2)+2)<h.getHeap().size())
				assertTrue(h.get(i).compareTo(h.get((i*2)+2))>=0);
		}
	}
	
	@Test
	public void add_StringTest() {
		Heap<String> h = new Heap<>();
		h.add("ala"); h.add("ma");h.add("kota");h.add("domek");h.add("miejscowosc");
		h.add("slownik");h.add("jeszcze");h.add("kilka");h.add("wyrazow");
		checkIsTrue(h);
	}
	
	@Test 
	public void extractMax_IntegerTest()
	{
		Heap<Integer> h = new Heap<>();
		addInt(h);
		while(h.getHeap().size()>0)
		{
			int val = h.extractMax();
			assertTrue(checkMaxInt(val, h));
			checkIsTrue(h);
		}
	}
	
	@Test
	public void deletemax_IntegerTest()
	{
		Heap<Integer> h = new Heap<>();
		addInt(h);
		while(h.getHeap().size()>0)
		{
			h.deleteMax();
			checkIsTrue(h);
		}
	}
	
	@Test
	public void heapify_IntegerTest()
	{
		Heap<Integer> h = new Heap<>();
		addInt(h);
		Integer[] tab = new Integer[100];
		Random r = new Random();
		for(int i=0;i<100;i++)
		{
			tab[i] = r.nextInt(100);
		}
		h.heapify(tab);
		checkIsTrue(h);
	}
	
	@Test
	public void meld_IntegerTest()
	{
		Heap<Integer> h = new Heap<>();
		Heap<Integer> h2 = new Heap<>();
		addInt(h);
		addInt(h2);
		h.meld(h2);
		checkIsTrue(h);
	}
	
	private boolean checkMaxInt(int val, Heap<Integer> h)
	{
		for(int i=0;i<h.getHeap().size();i++)
		{
			if(h.get(i)>val)
				return false;
		}
		return true;
	}

}
