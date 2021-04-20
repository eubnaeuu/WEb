package ourhouse.common.vo;

/**
 * 댓글 신고 내역 테이블
 * @author 이경륜
 *
 */
public class RReportVO {
	
	private int 	rReportNo   ; // 댓글글 신고 번호 (시퀀스)  
	private int 	replyNo     ; // 댓글번호 (시퀀스) 
	private String  reasonId    ; // 신고 이유 id       
	private String  reportDate  ; // 신고 날짜
	private String  reasonCon   ; // 신고 사유
	private String  memId	    ; // 신고 회원 ID
	private int     postNo	    ; // 게시글 번호
	private String  replyContent; // 댓글 내용
	
	public int getrReportNo() {
		return rReportNo;
	}
	public void setrReportNo(int rReportNo) {
		this.rReportNo = rReportNo;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
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
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReasonCon() {
		return reasonCon;
	}
	public void setReasonCon(String reasonCon) {
		this.reasonCon = reasonCon;
	}
	
	
	
}
