package ourhouse.notice.dao;

import java.util.List;

import ourhouse.common.vo.NoticeVO;

public interface INoticeDao {
	
	public List<NoticeVO> getNoticeAll() throws Exception;

	public int insertNotice(NoticeVO nvo) throws Exception;

	public NoticeVO getNotice(int notice_no) throws Exception;

	public int updateNotice(NoticeVO noticeVO) throws Exception;

	public int deleteNotice(int noticeNo) throws Exception;

	public NoticeVO getRecentNotice()throws Exception;

}
