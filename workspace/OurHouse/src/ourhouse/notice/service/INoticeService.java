package ourhouse.notice.service;

import java.util.List;

import ourhouse.common.vo.NoticeVO;

public interface INoticeService {

	/**
	 * DB에 저장되어있는 정보를 조회하는 메서드
	 * @return VO에 저장되어 있는 모든 데이터를 반환
	 * @author 서대철
	 */
	public List<NoticeVO> getNoticeAll();

	/**
	 * 공지사항을 추가하는 메서드
	 * @param nvo (공지사항 정보 입력값)
	 * @return 성공하면 cnt가 1로 반환
	 * @author 서대철
	 */
	public int insertNotice(NoticeVO nvo);

	/**
	 * 공지사항 번호가 일치하는 정보를 조회하는 메서드
	 * @param notice_no
	 * @return 번호와 일치하는 데이터를 반환
	 * @author 서대철
	 */
	public NoticeVO getNotice(int notice_no);

	/**
	 * 공지사항 번호에 일치하는 정보를 변경하는 메서드
	 * @param noticeVO
	 * @return 데이터 수정이 성공하면 cnt를 1로 반환
	 * @author 서대철
	 */
	public int updateNotice(NoticeVO noticeVO);

	/**
	 * 공지사항 번호가 일치하는 공지사항을 삭제하는 메서드
	 * @param noticeNo
	 * @return 공지사항 삭제가 성공하면 cnt를 1로 반환
	 * @author 서대철
	 */
	public int deleteNotice(int noticeNo);
	/**
	 * 질문게시글 공지사항 최신글 가져오기
	 * @author 조예슬
	 * @return NoticeVO 
	 */
	public NoticeVO getRecentNotice();
	
}
