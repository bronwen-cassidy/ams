package com.xioq.dasacumen.lib.utilities;

import java.util.Iterator;
import java.util.SortedSet;

/**
 * <p>
 * A simple utility class which allows for quick {@code SortedSet} manipulation which is not achieved by the {@code SortedSet}'s implementation.
 * <p>
 * @author Michael Waude
 */
public class SortedSetUtil {
	/**
	 * <p>
	 * Retrieves an {@code Object} from a {@code SortedSet} at it's index.
	 * <p>
	 * Example usage: {@code SortedSetUtil.getAtIndex(setToRetrieveFrom, 1)}
	 * <p>
	 * The example shows how to retrieve the element at index 1 from the example {@code SortedSet setToRetrieveFrom}.
	 * 
	 * @param set
	 * @param index
	 * @return An element at it's index of the set specified
	 */
	public static Object getAtIndex(SortedSet<?> set, int index) throws IndexOutOfBoundsException {
		if (index > set.size() - 1)
			throw new IndexOutOfBoundsException("Element does not exist in set at index: " + index);
		Iterator<?> it = set.iterator();
		int currentIndex = 0;
		while (it.hasNext() && currentIndex <= index) {
			if (currentIndex == index)
				return it.next();
			it.next();
			currentIndex++;
		}
		return null;
	}

	/**
	 * <p>
	 * Removes an element from a {@code SortedSet} at it's index.
	 * <p>
	 * Example usage: {@code SortedSetUtil.removeAtIndex(setToRemoveFrom, 1)}
	 * <p>
	 * The example shows how to remove the element at index 1 from the example {@code SortedSet setToRemoveFrom}.
	 * 
	 * @param set
	 * @param index
	 */
	public static void removeAtIndex(SortedSet<?> set, int index) throws IndexOutOfBoundsException {
		if (index > set.size() - 1)
			throw new IndexOutOfBoundsException("Element does not exist in set at index: " + index);
		Iterator<?> it = set.iterator();
		int currentIndex = 0;
		while (it.hasNext() && currentIndex <= index) {
			it.next();
			currentIndex++;
		}
		it.remove();
	}
}
