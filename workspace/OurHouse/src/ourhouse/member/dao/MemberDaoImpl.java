package ourhouse.member.dao;


import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourhouse.common.vo.AdminMemberVO;
import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.FollowImgVO;
import ourhouse.common.vo.FollowVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.common.vo.MyCouponListVO;
import ourhouse.common.vo.MyLikesVO;
import ourhouse.common.vo.MypagePhotoVO;
import ourhouse.common.vo.PostVO;
import ourhouse.common.vo.ProfileImgVO;
import ourhouse.common.vo.QnaListVO;
import ourhouse.common.vo.SearchMemberVO;
import ourhouse.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao{
	private static IMemberDao memDao;
	
	private SqlMapClient smc;
	
	// 1. 생성자가 호출되면서 SqlMapClient 객체 만들기 
	private MemberDaoImpl() {
		// 우리가 싱글톤패턴을 사용하기 위해 만든 SqlMapClientFactory 클래스 의 getInstance() 호출 
		smc = SqlMapClientFactory.getInstance();
		
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	
	@Override
	public MemberVO getMember(String memId) throws Exception {
		FollowVO fv = new FollowVO();
		fv.setMemId(memId);
		fv.setTargetId(memId);
		MemberVO mv = (MemberVO) smc.queryForObject("member.getMember", memId);
		return mv;
	}

	@Override
	public int getFollow(String memId) throws Exception {
		int cnt = 0;
		
		cnt = (int) smc.queryForObject("follow.getFollow", memId);
		
		return cnt;
	}

	@Override
	public int getFollower(String memId) throws Exception {
		int cnt = 0;
		
		cnt = (int) smc.queryForObject("follow.getFollower", memId);
		
		return cnt;
	}
	
	@Override
	public int getLikes(String memId) throws Exception {
		int cnt = 0;
		
		cnt = (int) smc.queryForObject("likes.getLikes", memId);
		
		return cnt;
	}
	
	@Override
	public int getCoupon(String memId) throws Exception {
		int cnt = 0;
		
		cnt = (int) smc.queryForObject("coupon.getCoupon", memId);
		
		return cnt;
	}

	@Override
	public String getPrfileImg(String memId) throws Exception {
		String img = null;
		
		img = (String) smc.queryForObject("ProfileImg.getPrfileImg", memId);
		
		return img;
	}

	@Override
	public List<PostVO> getQnAList(String memId) throws Exception {
		List<PostVO> postList = new ArrayList<PostVO>();
		
		postList = (List<PostVO>) smc.queryForList("post.getQnAList", memId);
		
		return postList;
	}

	@Override
	public List<PostVO> getPostList(String memId) throws Exception {
		List<PostVO> postList = new ArrayList<PostVO>();
		
		postList = (List<PostVO>)smc.queryForList("post.getPostList", memId);
		
		return postList;
	}
	
	@Override
	public int getAnswer(int qnANo) throws Exception {
		int cnt = 0;
		
		cnt = (int) smc.queryForObject("reply.getAnswerCnt", qnANo);
		
		return cnt;
	}
	
	@Override
	public List<AdminMemberVO> getMemList() throws Exception {
		List<AdminMemberVO> adminMemList = new ArrayList<AdminMemberVO>();
		
		adminMemList = (List<AdminMemberVO>) smc.queryForList("admin.selectAdminMemList");
		
		return adminMemList;
	}
	
	@Override
	public List<AtchFileVO> getAtchList(int postNo) throws Exception {
		List<AtchFileVO> atchList = new ArrayList<AtchFileVO>();
		
		atchList = (List<AtchFileVO>) smc.queryForList("atchFile.getAtchList",postNo);
		
		return atchList;
	}

	@Override
	public List<AtchFileVO> getQatchList(int qnANo) throws Exception {
		List<AtchFileVO> QatchList = new ArrayList<AtchFileVO>();
		
		QatchList = (List<AtchFileVO>) smc.queryForList("atchFile.getQatchList",qnANo);
		
		return QatchList;
	}

	@Override
	public List<MypagePhotoVO> getPhotoList(String memId) throws Exception {
		List<MypagePhotoVO> photoList = new ArrayList<MypagePhotoVO>();
		
		photoList = (List<MypagePhotoVO>) smc.queryForList("mypage.getPhotoList",memId);
		System.out.println(photoList);
		return photoList;
	}
	

	
	@Override
	public Object signupMember(MemberVO mv) throws Exception {
		
		Object obj = smc.insert("member.signupMember", mv);
		
		return obj;
	}
	

	@Override
	public MemberVO selectMember(String memId) throws Exception{
		
		MemberVO mv = (MemberVO) smc.queryForObject("member.getMember",memId);
		
		return mv;
	}

	@Override
	public MemberVO seletNickName(String memNickname) throws Exception{
		
		MemberVO mv = (MemberVO) smc.queryForObject("member.seletNickName", memNickname);
		
		return mv;
	}

	@Override
	public MemberVO selectEmail(String memEmail) throws Exception{
		MemberVO mv = (MemberVO) smc.queryForObject("member.selectEmail", memEmail);
		return mv;
	}

	@Override
	public int memDelete(MemberVO mv) throws Exception {
		System.out.println("다오"+mv.getMemId());
		System.out.println("다오"+mv.getMemDelete());
		int result = (Integer) smc.update("member.memDelete", mv);
		return result;
	}

	@Override
	public List<MyCouponListVO> selectCouponList(String memId) throws Exception {
		List<MyCouponListVO> mycVO = (List<MyCouponListVO>) smc.queryForList("member.selectCouponList", memId);
		return mycVO;
	}

	@Override
	public int insertFollowMember(String targetId) throws Exception {
		int cnt = 0;
		
		Object obj = smc.insert("follow.insertFollowMember", targetId);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int updateMemPass(MemberVO mv) throws Exception{
		int cnt = (int) smc.update("member.updateMemPass", mv);
		return cnt;
	}

	@Override
	public MemberVO selectMemPass(MemberVO mv) throws Exception{
		MemberVO mv2 = (MemberVO) smc.queryForObject("member.selectMemPass", mv);
		return mv2;
	}

	@Override
	public List<QnaListVO> memberQnaList(String memId) throws Exception {
		List<QnaListVO> myQList = (List<QnaListVO>) smc.queryForList("qnalist.selectMyQna", memId);
		return myQList;
	}

	@Override
	public List<MyLikesVO> myLikesList(String memId) throws Exception {
		List<MyLikesVO> myQList = (List<MyLikesVO>) smc.queryForList("mypage.selectMyLikes", memId);
		return myQList;
	}

	@Override
	public int memberInfoUpdate(MemberVO mv) throws Exception{
		int cnt = (int) smc.update("member.memberInfoUpdate", mv);
		return cnt;
	}

	@Override
	public int updateMemPoint(MemberVO mv) throws Exception {
		int cnt = (int) smc.update("member.updateMemPoint", mv);
		return cnt;
	}

	@Override
	public List<SearchMemberVO> searchTotalUser(SearchMemberVO pv) throws Exception {
		List<SearchMemberVO> searchTotalUser = (List<SearchMemberVO>) smc.queryForList("member.searchTotalUser", pv);
		return searchTotalUser;
	}

	@Override
	public int updateReplyPoint(int replyNo) throws Exception {
		
		int cnt = smc.update("member.updateReplyPoint", replyNo);
		
		return cnt;
	}

	@Override
	public List<FollowVO> getFollowingList(FollowVO fv) throws Exception{
		List<FollowVO> followingList = smc.queryForList("follow.getFollowingAll", fv);
		return followingList;
	}

	@Override
	public List<FollowImgVO> getFollowingImg(FollowImgVO fivo) throws Exception {
		List<FollowImgVO> followImgList = smc.queryForList("followImg.getFollowingImg", fivo);
		return followImgList;
	}

	@Override
	public List<FollowVO> getFollowerList(FollowVO fv) throws Exception {
		List<FollowVO> followerList = smc.queryForList("follow.getFollowerAll", fv);
		return followerList;
	}

	@Override
	public List<FollowImgVO> getFollowerImg(FollowImgVO fivo) throws Exception {
		List<FollowImgVO> follwerImgList = smc.queryForList("followImg.getFollowerImg", fivo);
		return follwerImgList;
	}



	
	
}
