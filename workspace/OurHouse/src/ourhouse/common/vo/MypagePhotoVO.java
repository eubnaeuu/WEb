package ourhouse.common.vo;

/**
 * 마이페이지 사진 조회용
 * @author 조예슬
 *
 */
public class MypagePhotoVO extends BaseVO{
	
	private String postDate      ;  // 사진글 게시일  
	private String memId         ; // 회원명
	private String hashtag        ;  // 해시태그
	private String fileStreCours ;  // 사진글경로     
	private String fileFileNm    ;  // 사진저장명     
	private int    postNo 		 ;  // 게시글번호
	
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
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
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	
	
	
}
