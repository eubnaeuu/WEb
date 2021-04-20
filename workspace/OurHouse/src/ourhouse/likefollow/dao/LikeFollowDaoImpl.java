package ourhouse.likefollow.dao;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourhouse.common.vo.FollowVO;
import ourhouse.common.vo.LikesVO;
import ourhouse.util.SqlMapClientFactory;

public class LikeFollowDaoImpl implements ILikeFollowDao{

	private static ILikeFollowDao likefollowDao;
	
	private SqlMapClient smc;
	
	private LikeFollowDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ILikeFollowDao getInstance() {
		if (likefollowDao == null) {
			likefollowDao = new LikeFollowDaoImpl();
		}
		return likefollowDao;
		
	}

	@Override
	public int insertLike(LikesVO paramVO) throws Exception {
		
		int cnt = 0;
		Object obj = smc.insert("likes.insertLike",paramVO);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int deleteLike(LikesVO paramVO) throws Exception {
		
		int cnt = smc.delete("likes.deleteLike",paramVO);
		
		return cnt;
	}

	@Override
	public int insertFollow(FollowVO paramVO) throws Exception {
		int cnt = 0;
		Object obj = smc.insert("follow.insertFollow",paramVO);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int deleteFollow(FollowVO paramVO) throws Exception {

		int cnt = smc.delete("follow.deleteFollow",paramVO);
		
		return cnt;
	}

}
