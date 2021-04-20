package ourhouse.common.vo;

/**
 * 회원 테이블
 * @author 이경륜
 */
public class SearchMemberVO extends BaseVO {
	
	private String memId        ; // 회원ID
	private String memNickname  ; // 별명
	private String memIntro     ; // 소개
	private String profileImg    ; // 탈퇴여부
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
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
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	
	
	
	
}
