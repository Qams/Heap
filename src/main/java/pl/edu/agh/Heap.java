package pl.edu.agh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Heap<T extends Comparable<T>> {

	ArrayList<T> heap;
	
	public Heap() {
		heap = new ArrayList<>();
	}
	
	public List<T> getHeap()
	{
		return heap;
	}
	
	public void add(T element) {
		heap.add(element);
		if (heap.size() > 1) {
			int x = heap.size() - 1;
			while (x > 0) {
				T tmp2 =  heap.get(x);
				if (x % 2 != 0) {
					T tmp = heap.get(x / 2);

					if (tmp.compareTo(tmp2)<0) {
						Collections.swap(heap, x, x/2);
						x = x / 2;
					} else
						break;
				} else {
					T tmp = heap.get((x / 2) - 1);
					if (tmp.compareTo(tmp2)<0) {
						Collections.swap(heap, x, (x/2)-1);
						x = (x / 2) - 1;
					} else
						break;
				}
			}
		}
	}
	
	public T extractMax() {
		T deleteValue;
		if (heap.size() > 0) {
			Collections.swap(heap, 0, heap.size() - 1);
			deleteValue = heap.remove(heap.size() - 1);
		}
		else
			deleteValue = null;
		setHeapHierarchy();
		return deleteValue;
	}
	
	private void setHeapHierarchy()
	{
		int index = 0;
		int indexTmp = 0;
		while (heap.size() > (indexTmp * 2) + 1) {
			T greater;
			T tmp = heap.get((index * 2) + 1);
			T tmp2;
			try {
				tmp2 = heap.get((index * 2) + 2);

				if (tmp.compareTo(tmp2)>=0) {
					greater = tmp;
					indexTmp = (index * 2) + 1;
				} else {
					greater = tmp2;
					indexTmp = (index * 2) + 2;
					
				}
			} catch (IndexOutOfBoundsException e) {
				greater = tmp;
				indexTmp = (indexTmp * 2) + 1;
			}
			if (greater.compareTo(heap.get(index))>0) {
				Collections.swap(heap, index, indexTmp);
				index = indexTmp;
			} else
				break;
		}
	}
	
	public void replace(T replace)
	{
		heap.set(0, replace);
		setHeapHierarchy();
	}
	
	public void deleteMax()
	{
		extractMax();
	}
	
	public void heapify(T[] tab)
	{
		for(T t : tab)
		{
			add(t);
		}
	}
	
	public void meld(Heap<T> h)
	{
		for(int i=0;i<h.getHeap().size();i++)
		{
			add((T) h.getHeap().get(i));
		}
	}
	
	public T get(int index)
	{
		try
		{
			return heap.get(index);
		}
		catch(RuntimeException ex)
		{
			return null;
		}
	}
	
	public static void main(String[] args) {
		Heap<Integer> h = new Heap<>();
		h.add(7);
		h.add(5);
		h.add(8);
		h.add(1);
		h.add(10);
		h.add(13); 
		h.add(12);
		h.add(7);
		System.out.println(h.heap);
	}
}
