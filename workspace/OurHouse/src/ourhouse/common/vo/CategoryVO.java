package ourhouse.common.vo;

/**
 * 주거형태 테이블
 * @author 이경륜
 *
 */
public class CategoryVO extends BaseVO{	// 주거형태 카테고리
	
	private String memId     ;
	private String houseId   ;
	private String colorId   ; 
	private String roomId    ; 
	private String spaceId   ; 
	private String styleId   ;
	
	
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(String colorId) {
		this.colorId = colorId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getSpaceId() {
		return spaceId;
	}
	public void setSpaceId(String spaceId) {
		this.spaceId = spaceId;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	@Override
	public String toString() {
		return "CategoryVO [memId=" + memId + ", houseId=" + houseId + ", colorId=" + colorId + ", roomId=" + roomId
				+ ", spaceId=" + spaceId + ", styleId=" + styleId + "]";
	}
	
	

	

	
}
