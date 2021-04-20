package ourhouse.reply.service;

import java.util.List;

import ourhouse.common.vo.ReplyVO;

public interface IReplyService {
	
	/**
	 * 사진 게시물 조회페이지에서 댓글 관련 정보 불러오는 메서드
	 * @param postNo 선택된 사진 게시글
	 * @return replyList 댓글vo가 담긴 리스트
	 * @author 이경륜
	 */
	public List<ReplyVO> selectReplyList(int postNo);

	/**
	 * 게시글에 댓글 등록하는 메서드
	 * @param paramVO (postNo, memId, replyContent 포함)
	 * @return 성공시 댓글no 실패시 0
	 * @author 이경륜
	 */
	public int insertReply(ReplyVO paramVO);

	/**
	 * 댓글 하나의 정보를 불러오는 메서드 (프사포함)
	 * @param replyNo
	 * @return replyVO 
	 * @author 이경륜
	 */
	public ReplyVO selectReply(int replyNo);


	/**
	 * 댓글 하나 삭제하는 메서드
	 * @param replyNo 삭제할 글번호
	 * @return 성공 1 
	 * @author 이경륜
	 */
	public int deleteReply(int replyNo);

}
