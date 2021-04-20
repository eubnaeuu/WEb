package ourhouse.common.vo;

/**
 * 사용처 테이블
 * @author 이경륜
 */
public class ShopVO {
	
	private String shopId	; // 사용처 코드
	private String shopName ; // 사용처(쇼핑몰)이름
	private String shopUrl ; // 사용처 url
	
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopUrl() {
		return shopUrl;
	}
	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	
	
	
}
