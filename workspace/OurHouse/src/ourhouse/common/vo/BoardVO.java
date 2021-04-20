package ourhouse.common.vo;

/**
 * 게시판 카테고리 테이블
 * @author 이경륜
 */
public class BoardVO { // 게시판 카테고리
	
	private String boardId   ;	// POST, QNA
	private String boardName ;	// 사진,  질문답변
	
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
}
