package ourhouse.common.vo;

public class PhotoDetailVO extends BaseVO{
	private int    postNo        ; // 글번호
	private String memId         ; // 작성자명
	private String postTitle     ; // 글제목  - QNA
	private String postContent   ; // 글내용  - QNA
	private String houseName     ; // 주거형태
	private String spaceName     ; // 평수
	private String roomName      ; // 룸
	private String styleName     ; // 스타일
	private String colorName     ; // 컬러
	private String hashtag       ; // 해시태그
	private int    postView      ; // 조회수
	private String postDate      ; // 작성일
	private int    likeCount     ; // 좋아요갯수
	private String likeYN        ; // 좋아요여부
	private String memPrImgPath  ; // 작성자 프사  경로
	private String memPrStrImgNm ; // 작성자 프사 명
	private String followYN      ; // 팔로우여부
	private int	   repCount	     ; // 댓글갯수	
	
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
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public int getPostView() {
		return postView;
	}
	public void setPostView(int postView) {
		this.postView = postView;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public String getLikeYN() {
		return likeYN;
	}
	public void setLikeYN(String likeYN) {
		this.likeYN = likeYN;
	}
	public String getFollowYN() {
		return followYN;
	}
	public void setFollowYN(String followYN) {
		this.followYN = followYN;
	}
	public int getRepCount() {
		return repCount;
	}
	public void setRepCount(int repCount) {
		this.repCount = repCount;
	}
	public String getMemPrImgPath() {
		return memPrImgPath;
	}
	public void setMemPrImgPath(String memPrImgPath) {
		this.memPrImgPath = memPrImgPath;
	}
	public String getMemPrStrImgNm() {
		return memPrStrImgNm;
	}
	public void setMemPrStrImgNm(String memPrStrImgNm) {
		this.memPrStrImgNm = memPrStrImgNm;
	}
	
	
}
