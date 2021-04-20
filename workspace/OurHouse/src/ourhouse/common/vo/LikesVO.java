package ourhouse.common.vo;

/**
 * 좋아요 테이블
 * @author 이경륜
 */
public class LikesVO { // 좋아요 
	
	private int 	likeNo   ; // 좋아요 no
	private int 	postNo   ; // 글 no
	private String  memId	 ; // 좋아요 누른 회원 id
	
	public int getLikeNo() {
		return likeNo;
	}
	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	
}
