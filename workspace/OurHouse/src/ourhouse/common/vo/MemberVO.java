package ourhouse.common.vo;

/**
 * 회원 테이블
 * @author 이경륜
 */
public class MemberVO {
	
	private String memAdmin		; // 관리자여부
	private String memId        ; // 회원ID
	private String memPass      ; // 비번
	private String memEmail     ; // 메일
	private String memNickname  ; // 별명
	private String memIntro     ; // 소개
	private int    memPoint     ; // 포인트
	private String memDelete    ; // 탈퇴여부
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemNickname() {
		return memNickname;
	}
	public void setMemNickname(String memNickname) {
		this.memNickname = memNickname;
	}
	public String getMemIntro() {
		return memIntro;
	}
	public void setMemIntro(String memIntro) {
		this.memIntro = memIntro;
	}
	public int getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}
	public String getMemDelete() {
		return memDelete;
	}
	public void setMemDelete(String memDelete) {
		this.memDelete = memDelete;
	}
	public String getMemAdmin() {
		return memAdmin;
	}
	public void setMemAdmin(String memAdmin) {
		this.memAdmin = memAdmin;
	}
	@Override
	public String toString() {
		return "MemberVO [memAdmin=" + memAdmin + ", memId=" + memId + ", memPass=" + memPass + ", memEmail=" + memEmail
				+ ", memNickname=" + memNickname + ", memIntro=" + memIntro + ", memPoint=" + memPoint + ", memDelete="
				+ memDelete + "]";
	}
	
	
	
}
