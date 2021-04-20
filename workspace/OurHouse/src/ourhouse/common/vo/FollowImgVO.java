package ourhouse.common.vo;

/**
 * 팔로우 팔로잉 이미지 가져오는 VO
 * @author 조예슬
 *
 */
public class FollowImgVO extends BaseVO{
	
	private String 	memId   ; // 팔로우 번호
	private String  targetId      ; // 팔로우를 하는 멤버id
	private String  profileImg   ; // 팔로우를 당하는 멤버id
	
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
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	@Override
	public String toString() {
		return "FollowImgVO [memId=" + memId + ", targetId=" + targetId + ", profileImg=" + profileImg + "]";
	}
	
	
	
	
}
