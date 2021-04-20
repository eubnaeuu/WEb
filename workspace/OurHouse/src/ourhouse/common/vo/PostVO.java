package ourhouse.common.vo;

/**
 * 게시글 테이블 (사진글+질문과답변글)
 * @author 이경륜
 *
 */
public class PostVO extends BaseVO{ // 게시글

	private int     postNo      ; // 글번호 (사진, 질문답변)
	private String  boardId     ; // 게시판 종류
	private String  memId       ; // 작성자 id
	private String  houseId     ; // 주거형태
	private String  spaceId     ; // 평수
	private String  roomId      ; // 방 종류
	private String  styleId     ; // 스타일
	private String  colorId     ; // 컬러
	private String  hashtag     ; // 해시태그
	private int     postView       ; // 조회수
	private String  postDate    ; // 작성날짜
	private String  postTitle   ; // 제목 (질문과답변)
	private String  postContent ; // 내용 (질문과답변)

	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
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

	
}
