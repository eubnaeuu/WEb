package ourhouse.common.vo;

/**
 * 신고사유 테이블
 * @author 이경륜
 */
public class ReasonVO {
	
	private String reasonId  ; // 신고사유id
	private String reasonCon ; // 신고사유
	
	public String getReasonId() {
		return reasonId;
	}
	public void setReasonId(String reasonId) {
		this.reasonId = reasonId;
	}
	public String getReasonCon() {
		return reasonCon;
	}
	public void setReasonCon(String reasonCon) {
		this.reasonCon = reasonCon;
	}
	
}
