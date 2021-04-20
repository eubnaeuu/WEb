package ourhouse.reply.service;

import java.util.ArrayList;
import java.util.List;

import ourhouse.common.vo.ReplyVO;
import ourhouse.reply.dao.IReplyDao;
import ourhouse.reply.dao.ReplyDaoImpl;

public class ReplyServiceImpl implements IReplyService{
	private static IReplyService replyService;
	
	private IReplyDao replyDao;
	
	private ReplyServiceImpl() {
		replyDao = ReplyDaoImpl.getInstance();
	}
	
	public static IReplyService getInstance() {
		if (replyService == null) {
			replyService = new ReplyServiceImpl();
		}
		return replyService;
	}

	@Override
	public List<ReplyVO> selectReplyList(int postNo) {
		List<ReplyVO> replyList = new ArrayList<ReplyVO>();
		
		try {
			replyList = replyDao.selectReplyList(postNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return replyList;
	}

	@Override
	public int insertReply(ReplyVO paramVO) {
		int cnt = 0;
		
		try {
			cnt = replyDao.insertReply(paramVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public ReplyVO selectReply(int replyNo) {
		ReplyVO replyVO = new ReplyVO();
		try {
			replyVO = replyDao.selectReply(replyNo);
		} catch (Exception e) {
		}
		return replyVO;
	}

	@Override
	public int deleteReply(int replyNo) {
		int cnt = 0;
		try {
			cnt = replyDao.deleteReply(replyNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
