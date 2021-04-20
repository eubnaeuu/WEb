package ourhouse.member.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.FollowImgVO;
import ourhouse.common.vo.FollowVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

public class MemberFollowerListHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/mypage/followerList.jsp";
	private static final String MAIN_PAGE = "/main.do";
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession(false);
		String memId = null;
		
		if(session == null) {
			memId = " ";
			return MAIN_PAGE;
		} else {
			MemberVO loginVO = (MemberVO) session.getAttribute("session");
			
			if(loginVO == null) {
				memId = " ";
				return MAIN_PAGE;
			} else {
				memId = loginVO.getMemId();
				
				System.out.println("session : "+memId);
				
				IMemberService memberService = MemberServiceImpl.getInstance();
				
				// sesison
				MemberVO memVO = memberService.getMember(memId);
				
				// 팔로워, 팔로잉
				int followingCnt = memberService.getFollow(memId);
				int followerCnt = memberService.getFollower(memId);
				
				// 프로필 이미지
				String img = memberService.getPrfileImg(memId);

				// 팔로워 회원 목록 조회
				FollowVO fv = new FollowVO();
				fv.setMemId(memId);
				System.out.println(fv);
				
				List<FollowVO> followerList = memberService.getFollowerAll(fv);
				System.out.println(followerList);
				
				// 팔로워 회원 이미지 조회
				FollowImgVO fivo = new FollowImgVO();
				fivo.setMemId(memId);
				
				List<FollowImgVO> followerImgList = memberService.getFollowerImg(fivo); 
				System.out.println(followerImgList);
				
				req.setAttribute("memberVO", memVO);
				req.setAttribute("followingCnt", followingCnt);
				req.setAttribute("followerCnt", followerCnt);
				req.setAttribute("img", img);
				req.setAttribute("followerList", followerList);
				req.setAttribute("followerImg", followerImgList);
				
				return VIEW_PAGE;
			}
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
