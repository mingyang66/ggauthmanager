package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PageUtil {

	public static List<Integer> getShowPages(int currentPage, int maxPage) {
		List<Integer> pages = new ArrayList<Integer>();
		pages.add(currentPage - 3);
		pages.add(currentPage - 2);
		pages.add(currentPage - 1);
		pages.add(currentPage);
		pages.add(currentPage + 1);
		pages.add(currentPage + 2);
		pages.add(currentPage + 3);
		Iterator<Integer> it = pages.iterator();
		while (it.hasNext()) {
			int p =it.next();
			if (p <= 0 || p > maxPage) {
				it.remove();
			}
		}
		return pages;
	}
}
