package ourhouse.reply.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourhouse.common.vo.ReplyVO;
import ourhouse.util.SqlMapClientFactory;

public class ReplyDaoImpl implements IReplyDao{
	
	private static IReplyDao replyDao;
	
	private SqlMapClient smc;
	
	private ReplyDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IReplyDao getInstance() {
		if (replyDao == null) {
			replyDao = new ReplyDaoImpl();
		}
		return replyDao;
		
	}

	@Override
	public List<ReplyVO> selectReplyList(int postNo) throws Exception {
		
		List<ReplyVO> replyList = smc.queryForList("reply.selectReplyList", postNo);
		
		return replyList;
	}

	@Override
	public int insertReply(ReplyVO paramVO) throws Exception {
		
		int postNo = (int) smc.insert("reply.insertReply", paramVO);
		
		if( postNo > 0) { // 성공시
			return postNo;
		}
		
		return 0;
	}

	@Override
	public ReplyVO selectReply(int replyNo) throws Exception {
		
		ReplyVO replyVO = (ReplyVO) smc.queryForObject("reply.selectReply", replyNo);
		
		return replyVO;
	}

	@Override
	public int deleteReply(int replyNo) throws Exception {
		
		int cnt = smc.delete("reply.deleteReply", replyNo);
		
		return cnt;
	}
	
}