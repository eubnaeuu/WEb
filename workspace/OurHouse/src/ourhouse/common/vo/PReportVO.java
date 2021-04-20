package ourhouse.common.vo;

/**
 * 글 신고 내역 테이블
 * @author 이경륜
 */
public class PReportVO {	// 글 신고 테이블
	
	private int 	pReportNo	 ; // 글 신고 번호 (시퀀스)
	private int 	postNo       ; // post 글번호 (시퀀스)
	private String  reasonId     ; // 신고 이유 id
	private String  reportDate   ; // 신고 날짜
	private String  fileCn		 ; // 게시글 내용		 
	private String  reasonCon	 ; // 신고 사유
	private String  memId        ; // 신고당한 회원의 id
	
	public int getpReportNo() {
		return pReportNo;
	}
	public void setpReportNo(int pReportNo) {
		this.pReportNo = pReportNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getReasonId() {
		return reasonId;
	}
	public void setReasonId(String reasonId) {
		this.reasonId = reasonId;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getFileCn() {
		return fileCn;
	}
	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}
	public String getReasonCon() {
		return reasonCon;
	}
	public void setReasonCon(String reasonCon) {
		this.reasonCon = reasonCon;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}


	
}
