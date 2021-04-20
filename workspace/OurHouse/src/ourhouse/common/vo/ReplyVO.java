package ourhouse.common.vo;

/**
 * 댓글 테이블
 * @author 이경륜
 */
public class ReplyVO extends BaseVO {
	
	private int	    replyNo		 ; // 댓글번호
	private String  memId        ; // 댓글단사람 id
	private int 	postNo       ; // 글번호
	private String  replyContent ; // 댓글내용
	private String  replyDate    ; // 댓글날짜
	private String  repPrImgPath ; // 댓글쓴이 프사 경로
	private String  repPrStrImgNm; // 댓글쓴이 프사 저장명
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
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
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	public String getRepPrImgPath() {
		return repPrImgPath;
	}
	public void setRepPrImgPath(String repPrImgPath) {
		this.repPrImgPath = repPrImgPath;
	}
	public String getRepPrStrImgNm() {
		return repPrStrImgNm;
	}
	public void setRepPrStrImgNm(String repPrStrImgNm) {
		this.repPrStrImgNm = repPrStrImgNm;
	}
	
	
}
