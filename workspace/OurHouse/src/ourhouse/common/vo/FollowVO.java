package ourhouse.common.vo;

/**
 * 팔로우 테이블
 * @author 이경륜
 *
 */
public class FollowVO {
	
	private int 	followNo   ; // 팔로우 번호
	private String  memId      ; // 팔로우를 하는 멤버id
	private String  targetId   ; // 팔로우를 당하는 멤버id
	private String  followDate ; // 팔로우 시작한 날짜
	
	public int getFollowNo() {
		return followNo;
	}
	public void setFollowNo(int followNo) {
		this.followNo = followNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getFollowDate() {
		return followDate;
	}
	public void setFollowDate(String followDate) {
		this.followDate = followDate;
	}
	
	
}
