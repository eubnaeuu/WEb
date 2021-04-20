package ourhouse.member.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.common.vo.MyCouponListVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

public class MyCouponHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/mypage/mycoupon.jsp";
	private static final String LOGIN_PAGE = "/member/signin.do";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		//1. 
		
		// 2. 서비스 객체 생성
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		String memId = req.getParameter("memId");
		// 3. 조회
		//String memId = mv.getMemId();
		
		MemberVO memVO = new MemberVO();
		memVO.setMemId(memId);
		
		MemberVO mvo = memberService.getMember(memId);
		
		// 3. 회원의 쿠폰 정보 조회
		MyCouponListVO mcVO = new MyCouponListVO();
		mcVO.setMemId(memId);
		
		List<MyCouponListVO> mycList = memberService.selectMyCouponList(memId);
		//req.setAttribute("myCouponList", mycList);
		
		Gson gson = new Gson();
		
		String jsonString = gson.toJson(mycList);
		req.setAttribute("jsonString", jsonString);
		
		String member = gson.toJson(mvo);
		req.setAttribute("member", member);
		
		return VIEW_PAGE;
		
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
