package ourhouse.common.vo;

/**
 * 프로필 사진 저장 테이블
 * @author 이경륜
 */
public class ProfileImgVO extends MemberVO { // 프로필사진
	
	private long	 prImgId      ; // 프사id
	private String 	 memId        ; // 멤버id
	private String 	 prImgPath    ; // 프사 저장경로
	private String 	 originlImgNm ; // 프사 원본명
	private String   streImgNm    ; // 프사 저장명
	private String   imgExtn      ; // 확장자명
	private long 	 imgSize      ; // 이미지 크기
	private String   imgSaveDt    ; // 생성일
	
	
	public long getPrImgId() {
		return prImgId;
	}
	public void setPrImgId(long prImgId) {
		this.prImgId = prImgId;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getPrImgPath() {
		return prImgPath;
	}
	public void setPrImgPath(String prImgPath) {
		this.prImgPath = prImgPath;
	}
	public String getOriginlImgNm() {
		return originlImgNm;
	}
	public void setOriginlImgNm(String originlImgNm) {
		this.originlImgNm = originlImgNm;
	}
	public String getStreImgNm() {
		return streImgNm;
	}
	public void setStreImgNm(String streImgNm) {
		this.streImgNm = streImgNm;
	}
	public String getImgExtn() {
		return imgExtn;
	}
	public void setImgExtn(String imgExtn) {
		this.imgExtn = imgExtn;
	}
	public long getImgSize() {
		return imgSize;
	}
	public void setImgSize(long imgSize) {
		this.imgSize = imgSize;
	}
	public String getImgSaveDt() {
		return imgSaveDt;
	}
	public void setImgSaveDt(String imgSaveDt) {
		this.imgSaveDt = imgSaveDt;
	}

	
}
