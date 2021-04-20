package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

public class MemberSignInHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/signin/signin.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}else {
			//1.요청 파라미터 정보 가져오기
			String memId = req.getParameter("memId");
			String memPass = req.getParameter("memPass");
			
			
			MemberVO mv = new MemberVO();
			mv.setMemId(memId);
			
			//2.서비스 객체 생성하기
			IMemberService memberService = MemberServiceImpl.getInstance();
			
			MemberVO resultVO = memberService.selectMember(memId);
			
			
			int cnt = 0;
			
			if(resultVO == null) { //로그인 실패
				cnt = 1;
				req.setAttribute("cnt", cnt);
				String url = "/views/signin/signinFalse.jsp";
				return url;
			}else { //로그인 성공
				req.setAttribute("resultVO", resultVO);
				
				//세션에 로그인된 아이디에 대한 회원 정보 넣기
				HttpSession session = req.getSession();
				session.setAttribute("session", resultVO);
				
				String url = "/views/signin/signinTrue.jsp";
				return url;
			}
			
			
			
//			String msg = "";
//			
//			
//			if(resultVO==null) {
//				msg="fail";
//			}
//			
//			if(resultVO != null && resultVO.getMemPass().equals(memPass) && resultVO.getMemId().equals(memId) && "N".equals(resultVO.getMemDelete())) {
//				//사용자가 입력한 아이디와 비번이 같다면
//				//로그인 판단이 여기서 나니까
//				HttpSession session = req.getSession();
//				session.setAttribute("session", resultVO);
//				msg = "AllOk";
//			}else if(resultVO != null && "Y".equals(resultVO.getMemDelete())) {
//				msg = "noMember";
//			}else {
//				//비밀번호가 틀리면
//				msg="passFalse";
//			}
//			
//			req.setAttribute("msg", msg);
//			
//			String url = "/views/signin/signinMember.jsp";
//			return url;
			
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
