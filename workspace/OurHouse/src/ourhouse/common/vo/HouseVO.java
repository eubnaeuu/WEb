package ourhouse.common.vo;

/**
 * 주거형태 테이블
 * @author 이경륜
 *
 */
public class HouseVO {	// 주거형태 카테고리

	private String houseId   ; // H1	H2		H3	H4
	private String houseName ; // 원룸	아파트	빌라	단독주택

	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	
}
