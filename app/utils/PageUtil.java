package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class PageUtil {

	public static List<String> getShowPages(int currentPage, int maxPage) {
		List<String> pages = new ArrayList<String>();
		if(maxPage < 5){
			for(int i=0;i<maxPage;i++){
				pages.add(String.valueOf(1+i));
			}
		} else {
			int n = 5;
			int j = 0;
			if(currentPage >= n){
				j = currentPage - n + 1;
				if(currentPage == maxPage && j > 1){
					j--;
				}
				if(currentPage < maxPage) {
					n = currentPage + 1;
				} else {
					n = currentPage;
				}
			}
			if(currentPage <= n) {
				currentPage = 1;
			}
			for(int i=j;i<n;i++){
				pages.add(String.valueOf(currentPage+i));
			}
			int lastPage = Integer.parseInt(pages.get(pages.size()-1));
			if(lastPage+1 == maxPage) {
				pages.add(String.valueOf(maxPage));
			} else {
				pages.add("...");
				pages.add(String.valueOf(maxPage));
			}
			if(lastPage == maxPage){
				pages.remove("...");
				pages.remove(String.valueOf(maxPage));
			}
			
		}
		return pages;
	}
}
