package ourhouse.common.vo;

/**
 * 관리자페이지 회원목록 조회용 VO
 * @author 이경륜
 */
public class AdminMemberVO { 

	private String memId        ; // ID                 
    private String memEmail     ; // 메일
    private int    memPoint     ; // 포인트
    private int    pReportCount ; // 글 신고당한 횟수
    private int    rReportCount ; // 댓글 신고당한 횟수
    private String memNickname  ; // 별명
    private String memDelete    ; // 탈퇴여부
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public int getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}
	public int getpReportCount() {
		return pReportCount;
	}
	public void setpReportCount(int pReportCount) {
		this.pReportCount = pReportCount;
	}
	public int getrReportCount() {
		return rReportCount;
	}
	public void setrReportCount(int rReportCount) {
		this.rReportCount = rReportCount;
	}
	public String getMemNickname() {
		return memNickname;
	}
	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}
	public String getMemDelete() {
		return memDelete;
	}
	public void setMemDelete(String memDelete) {
		this.memDelete = memDelete;
	}
    
    
    
}
