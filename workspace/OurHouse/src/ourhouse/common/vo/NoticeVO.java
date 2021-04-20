package ourhouse.common.vo;

/**
 * 공지사항 테이블
 * @author 이경륜
 */
public class NoticeVO { // 공지사항

	private int    noticeNo		 ; // 공지사항글no
	private String noticeTitle   ; // 공지사항제목
	private String noticeContent ; // 공지사항내용
	private String noticeDate    ; // 공지사항 등록날짜
	private String updateDate    ; // 공지사항 수정날짜
	
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	
}
