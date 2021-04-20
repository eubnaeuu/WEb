package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.FollowVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

public class MemberFollowHander implements CommandHandler{
	private static final String VIEW_PAGE = "";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		String memId = req.getParameter("memId");
		String memId = "sdcsdc";
		
//		String targetId = req.getParameter("targetId");
		String targetId = req.getParameter("chichi");
		
		
		IMemberService memberSerivce = MemberServiceImpl.getInstance();
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMemId(memId);
		
		FollowVO followVO = new FollowVO();
		followVO.setTargetId(targetId);
		
		int cnt = memberSerivce.insertFollowMember(targetId);
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		
		return false;
	}

}
