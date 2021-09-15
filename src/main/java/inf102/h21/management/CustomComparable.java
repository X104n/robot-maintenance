package inf102.h21.management;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class combines a Comparator and an element to implement a Comparable
 * Java PriorityQueue does not allow us to initialize a Priority Queue in O(n) time unless
 * 
 * 
 * @author Martin Vatshelle
 *
 * @param <T>
 */
public class CustomComparable<T> implements Comparable<CustomComparable<T>> {

	public final Comparator<T> cmp;
	public final T elem;
	
	private CustomComparable(Comparator<T> cmp, T elem) {
		this.cmp = cmp;
		this.elem = elem;
	}
	
	@Override
	public int compareTo(CustomComparable<T> o) {
		if(this.cmp != o.cmp)
			throw new IllegalArgumentException("Need to use same comparator");
		return cmp.compare(elem, o.elem);
	}
	
	public static <T> List<CustomComparable<T>> toComparable(List<T> elems, Comparator<T> cmp){
		List<CustomComparable<T>> list = new ArrayList<CustomComparable<T>>(elems.size());
		for(T elem:elems) {
			list.add(new CustomComparable<T>(cmp, elem));
		}
		return list;
	}
}
