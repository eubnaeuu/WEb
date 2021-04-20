package ourhouse.notice.service;

import java.util.ArrayList;
import java.util.List;

import ourhouse.common.vo.NoticeVO;
import ourhouse.notice.dao.INoticeDao;
import ourhouse.notice.dao.NoticeDaoImpl;

public class NoticeServiceImpl implements INoticeService{
	private INoticeDao nDao;
	private static INoticeService NoticeService;
	
	public NoticeServiceImpl() {
		nDao = NoticeDaoImpl.getInstance();
	}
	
	public static INoticeService getInstance() {
		if(NoticeService == null) {
			NoticeService = new NoticeServiceImpl();
		}
		return NoticeService;
	}

	@Override
	public List<NoticeVO> getNoticeAll() {
		List<NoticeVO> noticeList = new ArrayList<>();
		
		try {
			noticeList = nDao.getNoticeAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return noticeList;
	}

	@Override
	public int insertNotice(NoticeVO nvo) {
		int cnt = 0;
		
		try {
			cnt = nDao.insertNotice(nvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public NoticeVO getNotice(int notice_no) {
		NoticeVO noticeVO = null;
		
		try {
			noticeVO = nDao.getNotice(notice_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return noticeVO;
	}

	@Override
	public int updateNotice(NoticeVO noticeVO) {
		int cnt = 0;
		
		try {
			cnt = nDao.updateNotice(noticeVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		int cnt = 0;
		
		try {
			cnt = nDao.deleteNotice(noticeNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public NoticeVO getRecentNotice() {
		NoticeVO noticeVO = null;
		
		try {
			noticeVO = nDao.getRecentNotice();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return noticeVO;
	}
}
