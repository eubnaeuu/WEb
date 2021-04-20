package ourhouse.common.vo;

/**
 * 홈 화면 (사진화면) 카테고리 적용 리스트 조회용 VO
 * @author 조예슬
 */
public class HomeCategoryPhotoVO extends BaseVO{
	private String postDate      ;  // 사진글 게시일     
	private int    postNo        ;  // 사진글 번호      
	private int    postView      ;  // 조회수         
	private int    likeCount     ;  // 좋아요수        
	private int    repCount      ;  // 댓글수         
	private String memId         ;  // 작성자이름       
	private String memIntro      ;  // 회원소개글       
	private String memPrImgPath  ;  // 프사경로        
	private String memPrStrImgNm ;  // 프사저장명       
	private String fileCn        ;  // 사진글내용       
	private String fileStreCours ;  // 사진글경로       
	private String fileFileNm    ;  // 사진저장명       
	private String repMemId      ;  // 댓글 작성자      
	private String repPrImgPath  ;  // 댓글 프사 경로
	private String repPrImgNm    ;  // 댓글 프사 저장명
	private String repContent    ;  // 댓글내용
	private String followYN		 ;  // 팔로우여부
	private String likeYN		 ;  // 좋아요여부
	private String houseId       ;   //주거형태
	private String sapaceId      ;   //평수
	private String roomId        ;   //공간
	private String styleId       ;   //스타일
	private String colorId       ;   //색깔
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public int getPostView() {
		return postView;
	}
	public void setPostView(int postView) {
		this.postView = postView;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getRepCount() {
		return repCount;
	}
	public void setRepCount(int repCount) {
		this.repCount = repCount;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemIntro() {
		return memIntro;
	}
	public void setMemIntro(String memIntro) {
		this.memIntro = memIntro;
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
	public String getFileCn() {
		return fileCn;
	}
	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}
	public String getFileStreCours() {
		return fileStreCours;
	}
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}
	public String getFileFileNm() {
		return fileFileNm;
	}
	public void setFileFileNm(String fileFileNm) {
		this.fileFileNm = fileFileNm;
	}
	public String getRepMemId() {
		return repMemId;
	}
	public void setRepMemId(String repMemId) {
		this.repMemId = repMemId;
	}
	public String getRepPrImgPath() {
		return repPrImgPath;
	}
	public void setRepPrImgPath(String repPrImgPath) {
		this.repPrImgPath = repPrImgPath;
	}
	public String getRepPrImgNm() {
		return repPrImgNm;
	}
	public void setRepPrImgNm(String repPrImgNm) {
		this.repPrImgNm = repPrImgNm;
	}
	public String getRepContent() {
		return repContent;
	}
	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}
	public String getFollowYN() {
		return followYN;
	}
	public void setFollowYN(String followYN) {
		this.followYN = followYN;
	}
	public String getLikeYN() {
		return likeYN;
	}
	public void setLikeYN(String likeYN) {
		this.likeYN = likeYN;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getSapaceId() {
		return sapaceId;
	}
	public void setSapaceId(String sapaceId) {
		this.sapaceId = sapaceId;
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
	
	@Override
	public String toString() {
		return "HomeCategoryPhotoVO [postDate=" + postDate + ", postNo=" + postNo + ", postView=" + postView
				+ ", likeCount=" + likeCount + ", repCount=" + repCount + ", memId=" + memId + ", memIntro=" + memIntro
				+ ", memPrImgPath=" + memPrImgPath + ", memPrStrImgNm=" + memPrStrImgNm + ", fileCn=" + fileCn
				+ ", fileStreCours=" + fileStreCours + ", fileFileNm=" + fileFileNm + ", repMemId=" + repMemId
				+ ", repPrImgPath=" + repPrImgPath + ", repPrImgNm=" + repPrImgNm + ", repContent=" + repContent
				+ ", followYN=" + followYN + ", likeYN=" + likeYN + ", houseId=" + houseId + ", sapaceId=" + sapaceId
				+ ", roomId=" + roomId + ", styleId=" + styleId + ", colorId=" + colorId + "]";
	}
	
	
	
}
