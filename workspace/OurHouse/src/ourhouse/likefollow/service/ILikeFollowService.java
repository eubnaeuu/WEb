package ourhouse.likefollow.service;

import ourhouse.common.vo.FollowVO;
import ourhouse.common.vo.LikesVO;

public interface ILikeFollowService {

	/**
	 * 게시글을 좋아요 하는 메서드
	 * @param paramVO 세션id, 게시글 번호
	 * @return 성공시 1 
	 */
	public int insertLike(LikesVO paramVO);
	
	/**
	 * 게시글 좋아요를 취소하는 메서드
	 * @param paramVO 세션id, 게시글 번호
	 * @return 성공시 1
	 */
	public int deleteLike(LikesVO paramVO);

	/**
	 * 타겟을 팔로우 하는 메서드
	 * @param paramVO 세션id, 타겟id
	 * @return 성공시 1 
	 */
	public int insertFollow(FollowVO paramVO);

	/**
	 * 타겟 팔로우를 취소 (삭제) 하는 메서드
	 * @param paramVO 세션id, 타겟id 
	 * @return 성공시 1
	 */
	public int deleteFollow(FollowVO paramVO);
}
