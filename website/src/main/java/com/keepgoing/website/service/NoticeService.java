package com.keepgoing.website.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keepgoing.website.entity.Notice;
import com.keepgoing.website.entity.NoticeView;

public interface NoticeService {
	
	// -페이지를 요청할 때
	List<NoticeView> getViewList(boolean pub);

	// -검색을 요청할 때
	List<NoticeView> getViewList(String field, String query, boolean pub);

	// -페이지를 요청할 때
	List<NoticeView> getViewList(int page, String field, String query, boolean pub);
	
	// -자신의 페이지를 요청할 때
		List<NoticeView> getMyViewList(int page, String field, String query, boolean pub);

	int getCount();

	int getCount(String field, String query, boolean pub);

	// -자세한 페이지 요청할 때
	NoticeView getView(int id);

	Notice getNext(int id);
	
	NoticeView getMyNext(String field, String query, int id);

	Notice getPrev(int id);
	
	NoticeView getMyPrev(String field, String query, int id);

	// -일괄공개를 요청할 때
	int updatePubAll(int[] pubIds, int[] closeIds);

	// -일괄삭제를 요청할 때
	int deleteAll(int[] ids);

	// -수정페이지를 요청할 때
	int update(NoticeView noticeview);

	int insert(Notice notice);

	int delete(int id);
	
	void viewCountValidation(NoticeView notice, HttpServletRequest request, HttpServletResponse response);
}
