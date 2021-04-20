package ourhouse.likefollow.service;

import ourhouse.common.vo.FollowVO;
import ourhouse.common.vo.LikesVO;
import ourhouse.likefollow.dao.ILikeFollowDao;
import ourhouse.likefollow.dao.LikeFollowDaoImpl;

public class LikeFollowServiceImpl implements ILikeFollowService{
private static ILikeFollowService likefollowService;
	
	private ILikeFollowDao likefollowDao;
	
	private LikeFollowServiceImpl() {
		likefollowDao = LikeFollowDaoImpl.getInstance();
	}
	
	public static ILikeFollowService getInstance() {
		if (likefollowService == null) {
			likefollowService = new LikeFollowServiceImpl();
		}
		return likefollowService;
	}

	@Override
	public int insertLike(LikesVO paramVO) {
		
		int cnt = 0;
		
		try {
			cnt = likefollowDao.insertLike(paramVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteLike(LikesVO paramVO) {
		int cnt = 0;
		
		try {
			cnt = likefollowDao.deleteLike(paramVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertFollow(FollowVO paramVO) {
		int cnt = 0;
		
		try {
			cnt = likefollowDao.insertFollow(paramVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteFollow(FollowVO paramVO) {
		int cnt = 0;
		
		try {
			cnt = likefollowDao.deleteFollow(paramVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
