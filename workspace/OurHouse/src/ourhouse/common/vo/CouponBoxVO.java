package ourhouse.common.vo;

/**
 * 유저가 발급받은 쿠폰함
 * @author 이경륜
 */
public class CouponBoxVO { // 나의 쿠폰함

	private int     couponNo    ;	// 쿠폰발급번호 (시퀀스)
	private int     cpMgNo      ;	// 쿠폰종류번호 (시퀀스)
	private String  memId      ;	// 쿠폰받은 회원ID
	private String  couponCode ;	// 쿠폰바코드번호
	private String  couponDate ;	// 쿠폰생성일자
	
	public int getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}
	public int getCpMgNo() {
		return cpMgNo;
	}
	public void setCpMgNo(int cpMgNo) {
		this.cpMgNo = cpMgNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponDate() {
		return couponDate;
	}
	public void setCouponDate(String couponDate) {
		this.couponDate = couponDate;
	}
	
}
