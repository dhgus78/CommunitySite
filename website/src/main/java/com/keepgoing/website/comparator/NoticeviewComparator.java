package com.keepgoing.website.comparator;

import java.util.Comparator;

import com.keepgoing.website.entity.NoticeView;

public class NoticeviewComparator implements Comparator<NoticeView>{
	
	public NoticeviewComparator() {
		
	}
	
	
	@Override
	public int compare(NoticeView n1, NoticeView n2) {
		if(n1.getRegdate().after(n2.getRegdate())) {
			return 1;
		}else if(n1.getRegdate().before(n2.getRegdate())) {
			return -1;
		}
		return 0;
	}
	
}
