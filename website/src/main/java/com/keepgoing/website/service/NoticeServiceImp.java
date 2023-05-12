package com.keepgoing.website.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keepgoing.website.dao.NoticeDao;
import com.keepgoing.website.entity.Notice;
import com.keepgoing.website.entity.NoticeView;

@Service
public class NoticeServiceImp implements NoticeService{
	
	@Autowired
	private NoticeDao noticeDao;



	@Override
	public List<NoticeView> getViewList(boolean pub) {
		// TODO Auto-generated method stub
		return getViewList(1, "title", "", pub);
	}

	@Override
	public List<NoticeView> getViewList(String field, String query, boolean pub) {
		// TODO Auto-generated method stub
		return getViewList(1, field, query, pub);
	}
	
	@Override
	public List<NoticeView> getViewList(int page, String field, String query, boolean pub) {
		
		int size = 10;
		int offset = 0+(page-1)*size; // page 1 -> 0, 2->10, 3->20  an=a1+(n-1)d  <공차수열 방정식>  -> 0+(page-1)*10
		
		List<NoticeView> list = noticeDao.getViewList(offset, size, field, query, pub);
		
		return list;
	}
	
	@Override
	public List<NoticeView> getMyViewList(int page, String field, String query, boolean pub) {
		
		int size = 10;
		int offset = 0+(page-1)*size; // page 1 -> 0, 2->10, 3->20  an=a1+(n-1)d  <공차수열 방정식>  -> 0+(page-1)*10
		
		List<NoticeView> list = noticeDao.getMyViewList(offset, size, field, query, pub);
		
		return list;
	}

	@Override
	public int getCount() {
		
		return getCount("title","", true);
	}

	@Override
	public int getCount(String field, String query, boolean pub) {
		
		return noticeDao.getCount(field, query, pub);
	}

	@Override
	public NoticeView getView(int id) {
		NoticeView notice = noticeDao.getView(id);

		return notice;
	}

	@Override
	public Notice getNext(int id) {
		
		return noticeDao.getNext(id);
	}
	
	@Override
	public NoticeView getMyNext(String field, String query, int id) {
		
		return noticeDao.getMyNext(field,query,id);
	}

	@Override
	public Notice getPrev(int id) {
		
		return noticeDao.getPrev(id);
	}
	
	@Override
	public NoticeView getMyPrev(String field, String query, int id) {
		
		return noticeDao.getMyPrev(field,query,id);
	}

	@Override
	public int updatePubAll(int[] pubIds, int[] closeIds) {
		int result = 0;
		result += noticeDao.updatePubAll(pubIds, true);
		result += noticeDao.updatePubAll(closeIds, false);
		
		return result;
	}

	@Override
	public int deleteAll(int[] ids) {
		
		return noticeDao.deleteAll(ids);
	}

	@Override
	public int update(NoticeView noticeview) {
		
		return noticeDao.update(noticeview);
	}

	@Override
	public int insert(Notice notice) {
		
		return noticeDao.insert(notice);
	}

	@Override
	public int delete(int id) {
		
		return noticeDao.delete(id);
	}

	public void viewCountValidation(NoticeView notice, HttpServletRequest request, HttpServletResponse response) {
		
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        boolean isCookie = false;
        
        // request에 쿠키들이 있을 때
        for (int i = 0; cookies != null && i < cookies.length; i++) {
        	// noticeView 쿠키가 있을 때
            if (cookies[i].getName().equals("noticeView")) {
            	// cookie 변수에 저장
                cookie = cookies[i];
                // 만약 cookie 값에 현재 게시글 번호가 없을 때
                if (!cookie.getValue().contains("[" + notice.getId() + "]")) {
                	// 해당 게시글 조회수를 증가시키고, 쿠키 값에 해당 게시글 번호를 추가
                	noticeDao.addHit(notice);
                    cookie.setValue(cookie.getValue() + "[" + notice.getId() + "]");
                }
                isCookie = true;
                break;
            }
        }
        // 만약 noticeView라는 쿠키가 없으면 처음 접속한 것이므로 새로 생성
        if (!isCookie) { 
        	noticeDao.addHit(notice);
            cookie = new Cookie("noticeView", "[" + notice.getId() + "]"); // oldCookie에 새 쿠키 생성
        }
        
        // 쿠키 유지시간을 오늘 하루 자정까지로 설정
        long todayEndSecond = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
        long currentSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        cookie.setPath("/"); // 모든 경로에서 접근 가능
        cookie.setMaxAge((int) (todayEndSecond - currentSecond));
        response.addCookie(cookie);
    }
	
}
