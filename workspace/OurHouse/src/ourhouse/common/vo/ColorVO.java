package ourhouse.common.vo;

/**
 * 컬러 테이블
 * @author 이경륜
 *
 */
public class ColorVO {	// 컬러 카테고리
	
	private String colorId   ;	//	C1	C2		C3	C4	C5
	private String colorName ;	//	블랙	화이트	노랑	분홍	그린
	
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
}
