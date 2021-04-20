package ourhouse.member.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.AdminMemberVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
/**
 * 관리자 회원 조회 
 * @author 조예슬
 *
 */
public class AdminMemberListHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/views/admin/memberManage.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		List<AdminMemberVO> adminMemList = new ArrayList<>();
		
		adminMemList = memberService.getMemList();
		req.setAttribute("adminMemList", adminMemList);	
		
		// 회원 비활성화 하기
		String active = req.getParameter("active");
		String memId = req.getParameter("memId");
		System.out.println("핸들러"+active);
		System.out.println("핸들러"+memId);
		MemberVO mv1 = new MemberVO();
		mv1.setMemId(memId);
		mv1.setMemDelete(active);
		int result = memberService.memDelete(mv1);
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		
		return false;
	}

}
