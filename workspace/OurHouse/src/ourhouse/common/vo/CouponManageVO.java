package ourhouse.common.vo;

/**
 * 관리자가 관리하는 쿠폰
 * @author 이경륜
 */
public class CouponManageVO {
	
	private int 	cpMgNo    ;	// 쿠폰종류번호 (시퀀스)
	private String  shopId    ;	// 쿠폰 사용처 ID
	private int     price  	  ;	// 쿠폰 가격
	private int 	stock 	  ; // 쿠폰 재고
	private String 	cpDelete  ; // 삭제여부
	private String  shopName  ; // 쿠폰 사용처  
	private String  shopUrl   ; // 사용처 url
	
	public int getCpMgNo() {
		return cpMgNo;
	}
	public void setCpMgNo(int cpMgNo) {
		this.cpMgNo = cpMgNo;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCpDelete() {
		return cpDelete;
	}
	public void setCpDelete(String cpDelete) {
		this.cpDelete = cpDelete;
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
	@Override
	public String toString() {
		return "CouponManageVO [cpMgNo=" + cpMgNo + ", shopId=" + shopId + ", price=" + price + ", stock=" + stock
				+ ", cpDelete=" + cpDelete + ", shopName=" + shopName + ", shopUrl=" + shopUrl + "]";
	}
	
	
	
	
	
	
	
}
