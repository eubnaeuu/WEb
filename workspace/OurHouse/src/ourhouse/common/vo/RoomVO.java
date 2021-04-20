package ourhouse.common.vo;

/**
 * 방 종류 테이블
 * @author 이경륜
 */
public class RoomVO {
	
	private String roomId	; // 방 종류 id
	private String roomName ; // 방 종류 이름
	
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
}
