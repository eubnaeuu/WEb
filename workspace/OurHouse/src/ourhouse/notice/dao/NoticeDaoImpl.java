package ourhouse.notice.dao;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourhouse.common.vo.NoticeVO;
import ourhouse.util.SqlMapClientFactory;

public class NoticeDaoImpl implements INoticeDao{
	private static INoticeDao nDao;
	
	private SqlMapClient smc;
	
	// 생성자가 호출될 때 싱글턴 객체 생성
	public NoticeDaoImpl() {
		// SqlMapClientFactory에서 genInstance() 호출
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static INoticeDao getInstance() {
		if(nDao == null) {
			nDao = new NoticeDaoImpl();
		}
		return nDao;
	}
	
	@Override
	public List<NoticeVO> getNoticeAll() throws Exception{
		List<NoticeVO> noticeList = new ArrayList<>();
		
		noticeList = smc.queryForList("notice.getNoticeAll", noticeList);
		
		return noticeList;
	}

	@Override
	public int insertNotice(NoticeVO nvo) throws Exception {
		int cnt = 0;
		Object obj = smc.insert("notice.insertNotice", nvo);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public NoticeVO getNotice(int notice_no) throws Exception {
		NoticeVO noticeVO = (NoticeVO) smc.queryForObject("notice.getNotice", notice_no);
		return noticeVO;
	}

	@Override
	public int updateNotice(NoticeVO noticeVO) throws Exception {
		int cnt = 0;
		
		cnt = smc.update("notice.updateNotice", noticeVO);
		
		return cnt;
	}

	@Override
	public int deleteNotice(int noticeNo) throws Exception {
		int cnt = 0;
		
		cnt = smc.delete("notice.deleteNotice", noticeNo);
		
		return cnt;
	}

	@Override
	public NoticeVO getRecentNotice() throws Exception {
		NoticeVO noticeVO = (NoticeVO) smc.queryForObject("notice.getRecentNotice");
		return noticeVO;
	}
}
