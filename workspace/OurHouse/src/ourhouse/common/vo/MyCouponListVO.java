package ourhouse.common.vo;

public class MyCouponListVO {
	private String memId; 			// 쿠폰을 소유한 회원
	private String prodName; 		// 사용처
	private String couponSite; 		// 사용 홈페이지
	private String myCouponCode; 	// 쿠폰번호
	private int couponNo;			// 쿠폰 시퀀스

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getCouponSite() {
		return couponSite;
	}

	public void setCouponSite(String couponSite) {
		this.couponSite = couponSite;
	}

	public String getMyCouponCode() {
		return myCouponCode;
	}

	public void setMyCouponCode(String myCouponCode) {
		this.myCouponCode = myCouponCode;
	}

	public int getCouponNo() {
		return couponNo;
	}

	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}
	
}
