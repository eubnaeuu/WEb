package ourhouse.member.service;

import java.util.ArrayList;
import java.util.List;

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
import ourhouse.member.dao.IMemberDao;
import ourhouse.member.dao.MemberDaoImpl;

public class MemberServiceImpl implements IMemberService{
	private IMemberDao memDao;
	private static IMemberService service;
	
	
	public MemberServiceImpl() { 
		memDao = MemberDaoImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		if(service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}

	@Override
	public MemberVO getMember(String memId) {
		MemberVO mv = new MemberVO();
		
		try {
			mv =  memDao.getMember(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@Override
	public Object signupMember(MemberVO mv) {
		Object obj = 0;
		
		try {
			obj = memDao.signupMember(mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int getFollow(String memId) {
		int cnt = 0;
		try {
			cnt = memDao.getFollow(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int getFollower(String memId) {
		int cnt = 0;
		try {
			cnt = memDao.getFollower(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int getLikes(String memId) {
		int cnt = 0;
		try {
			cnt = memDao.getLikes(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
	@Override
	public int getCoupon(String memId) {
		int cnt = 0;
		try {
			cnt = memDao.getCoupon(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public String getPrfileImg(String memId) {
		String img = null;
		try {
			img = memDao.getPrfileImg(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}

	@Override
	public List<PostVO> getQnAList(String memId) {
		List<PostVO> postList = new ArrayList<PostVO>();
		try {
			postList = memDao.getQnAList(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postList;
	}

	@Override
	public List<PostVO> getPostList(String memId) {
		List<PostVO> postList = new ArrayList<PostVO>();
		try {
			postList = memDao.getPostList(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postList;
	}

	@Override
	public int getAnswer(int qnANo) {
		int ansCnt = 0;
		try {
			ansCnt = memDao.getAnswer(qnANo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ansCnt;
	}
	
	@Override
	public List<AdminMemberVO> getMemList() {
		List<AdminMemberVO> adminMemList = new ArrayList<AdminMemberVO>();
		try {
			adminMemList = memDao.getMemList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminMemList;
	}
	
	@Override
	public MemberVO selectMember(String memId) {
		MemberVO mv = new MemberVO();
		try {
			mv = memDao.selectMember(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@Override
	public List<AtchFileVO> getAtchList(int postNo) {
		List<AtchFileVO> atchList = new ArrayList<AtchFileVO>();
		try {
			atchList = memDao.getAtchList(postNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return atchList;
	}
	
	@Override
	public List<AtchFileVO> getQatchList(int qnANo) {
		List<AtchFileVO> QatchList = new ArrayList<AtchFileVO>();
		try {
			QatchList = memDao.getQatchList(qnANo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return QatchList;
	}

	@Override
	public List<MypagePhotoVO> getPhotoList(String memId) {
		List<MypagePhotoVO> photoList = new ArrayList<MypagePhotoVO>();
		try {
			photoList = memDao.getPhotoList(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photoList;
	}
	
	@Override
	public MemberVO selectNickName(String memNickname) {
		MemberVO mv = new MemberVO();
		try {
			mv = memDao.seletNickName(memNickname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@Override
	public MemberVO selectEmail(String memEmail) {
		MemberVO mv = new MemberVO();
		try {
			mv = memDao.selectEmail(memEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	@Override
	public int memDelete(MemberVO mv) {
		int result = 0;
		try {
			result = memDao.memDelete(mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<MyCouponListVO> selectMyCouponList(String memId) {
		List<MyCouponListVO> mycVO = new ArrayList<MyCouponListVO>();
		
		try {
			mycVO = memDao.selectCouponList(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mycVO;
	}

	@Override
	public int insertFollowMember(String targetId) {
		int cnt = 0;
		
		try {
			cnt = memDao.insertFollowMember(targetId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMemPass(MemberVO mv) {
		int cnt = 0;
		try {
			cnt = memDao.updateMemPass(mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public MemberVO selectMemPass(MemberVO mv) {
		MemberVO mv2 = new MemberVO();
		try {
			mv2 = memDao.selectMemPass(mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv2;
	}

	@Override
	public List<QnaListVO> memberQnaList(String memId) {
		List<QnaListVO> myQList = new ArrayList<QnaListVO>();
		
		try {
			myQList = memDao.memberQnaList(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return myQList;
	}

	@Override
	public List<MyLikesVO> myLikesList(String memId) {
		List<MyLikesVO> mylikeList = new ArrayList<MyLikesVO>();
		
		try {
			mylikeList = memDao.myLikesList(memId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mylikeList;
	}

	@Override
	public int memberInfoUpdate(MemberVO mv) {
		int cnt = 0;
		try {
			cnt = memDao.memberInfoUpdate(mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMemPoint(MemberVO mv) {
		int cnt = 0;
		try {
			cnt = memDao.updateMemPoint(mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<SearchMemberVO> searchTotalUser(SearchMemberVO sv) {
		List<SearchMemberVO> searchTotalUser = new ArrayList<SearchMemberVO>();
		
		try {
			searchTotalUser = memDao.searchTotalUser(sv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return searchTotalUser;
	}

	@Override
	public int updateReplyPoint(int replyNo) {
		int cnt = 0;
		
		try {
			cnt = memDao.updateReplyPoint(replyNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<FollowVO> getFollowingAll(FollowVO fv) {
		List<FollowVO> followingList = new ArrayList<>();
		
		try {
			followingList = memDao.getFollowingList(fv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return followingList;
	}

	@Override
	public List<FollowImgVO> getFollowingImg(FollowImgVO fivo) {
		List<FollowImgVO> followImgList = new ArrayList<>();
		
		try {
			followImgList = memDao.getFollowingImg(fivo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return followImgList;
	}

	@Override
	public List<FollowVO> getFollowerAll(FollowVO fv) {
		List<FollowVO> followerList = new ArrayList<>();
		
		try {
			followerList = memDao.getFollowerList(fv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return followerList;
	}

	@Override
	public List<FollowImgVO> getFollowerImg(FollowImgVO fivo) {
		List<FollowImgVO> followerImgList = new ArrayList<>();
		
		try {
			followerImgList = memDao.getFollowerImg(fivo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return followerImgList;
	}






}
