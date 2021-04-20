package ourhouse.common.vo;

/**
 * 상품태그 테이블
 * @author 이경륜
 */
public class ProdVO { // 상품태그 테이블
	
	private int 	prodNo		; // 상품태그no
	private long 	imgNo       ; // 이미지파일no
	private String  prodName    ; // 상품태그 이름
	private String  prodLink    ; // 상품태그 링크
	private int 	xPath       ; // x좌표
	private int 	yPath       ; // y좌표
	
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public long getImgNo() {
		return imgNo;
	}
	public void setImgNo(long imgNo) {
		this.imgNo = imgNo;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdLink() {
		return prodLink;
	}
	public void setProdLink(String prodLink) {
		this.prodLink = prodLink;
	}
	public int getxPath() {
		return xPath;
	}
	public void setxPath(int xPath) {
		this.xPath = xPath;
	}
	public int getyPath() {
		return yPath;
	}
	public void setyPath(int yPath) {
		this.yPath = yPath;
	}
	
	

}
