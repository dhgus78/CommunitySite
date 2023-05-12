package com.keepgoing.website.dao;

import java.util.List;

import com.keepgoing.website.entity.Notice;
import com.keepgoing.website.entity.NoticeView;


//@Mapper
public interface NoticeDao {

	List<NoticeView> getViewList(int offset, int size, String field, String query, boolean pub);
	List<NoticeView> getMyViewList(int offset, int size, String field, String query, boolean pub);
	int getCount(String field, String query, boolean pub);
	NoticeView getView(int id);
	
	Notice getNext(int id);
	NoticeView getMyNext(String field, String query, int id);
	Notice getPrev(int id);
	NoticeView getMyPrev(String field, String query, int id);
	
	int update(NoticeView noticeview);
	int insert(Notice notice);
	int delete(int id);
	
	int deleteAll(int[] ids);
	//int updatePubAll(int[] pubIds, int[] closeIds);
	int updatePubAll(int[] ids, boolean pub);
	
	void addHit(NoticeView notice);
}

/* DAO 쿼리문 기능.
 * ${} <- 필드를 삽입할때 많이 쓰임.    필드 삽입시 '' 값을 넣을때 사용되는 구분자가 들어가면 안돼니깐 필드 전용 기능을 제공. 
 * 		  값에도 사용 가능.-> String 타입 값인데 ''없이 값을 꽂고 싶을 경우에 사용. 
 * 
 * #{} <- 값을 삽입할때 많이 쓰임.      String은 값을 여기 안에다가 넣어서 삽입 '',   int타입은 ''없이 삽입하는것으로 보임.     
 * */


