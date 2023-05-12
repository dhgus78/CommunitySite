package com.keepgoing.website.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.keepgoing.website.dao.NoticeDao;
import com.keepgoing.website.entity.Notice;
import com.keepgoing.website.entity.NoticeView;

@Repository
public class MybatisNoticeDao implements NoticeDao{
	
	private NoticeDao mapper;
	
	@Autowired
	public MybatisNoticeDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(NoticeDao.class);
	}

	@Override
	public List<NoticeView> getViewList(int offset, int size, String field, String query, boolean pub) {
		
		return mapper.getViewList(offset, size, field, query, pub);
	}
	
	@Override
	public List<NoticeView> getMyViewList(int offset, int size, String field, String query, boolean pub) {
		
		return mapper.getMyViewList(offset, size, field, query, pub);
	}

	@Override
	public int getCount(String field, String query, boolean pub) {
		
		return mapper.getCount(field, query, pub);
	}

	@Override
	public NoticeView getView(int id) {
		
		return mapper.getView(id);
	}

	@Override
	public Notice getNext(int id) {
		
		return mapper.getNext(id);
	}
	
	@Override
	public NoticeView getMyNext(String field, String query, int id) {
		
		return mapper.getMyNext(field,query,id);
	}

	@Override
	public Notice getPrev(int id) {
		
		return mapper.getPrev(id);
	}
	
	@Override
	public NoticeView getMyPrev(String field, String query, int id) {
		
		return mapper.getMyPrev(field,query,id);
	}

	@Override
	public int update(NoticeView noticeview) {
		
		return mapper.update(noticeview);
	}

	@Override
	public int insert(Notice notice) {
		mapper.insert(notice);
		return notice.getId();
		 
	}

	@Override
	public int delete(int id) {
		
		return mapper.delete(id);
	}

	@Override
	public int deleteAll(int[] ids) {
		
		return mapper.deleteAll(ids);
	}

	@Override
	public int updatePubAll(int[] ids, boolean pub) {
		
		return mapper.updatePubAll(ids, pub);
	}

	@Override
	public void addHit(NoticeView notice) {
		
		int hit = notice.getHit();
		int plusHit = ( hit + 1 );
		notice.setHit(plusHit);
		
		 mapper.addHit(notice);
		
	}

	
}
