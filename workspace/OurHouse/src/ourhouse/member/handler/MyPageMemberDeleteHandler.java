package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
/**
 * 마이페이지 => 회원탈퇴페이지 
 * @author 김지현
 */
public class MyPageMemberDeleteHandler implements CommandHandler{
	//회원탈퇴페이지로
	private static final String VIEW_PAGE = "/views/mypage/myPageMemDelete.jsp";
	//로그인페이지로
	private static final String LOGIN_PAGE = "/member/signin.do";
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession(false);
		
		if(session == null) {
			return LOGIN_PAGE;
		}else {
			MemberVO memVO = (MemberVO) session.getAttribute("session");
			if(memVO == null) {
				return LOGIN_PAGE;
			}else {
				if(req.getMethod().equals("GET")) {
					return VIEW_PAGE;
				}else {
					//1.req한테 정보 받아오기
					//원래는 세션에서 받아와야하는 아이디
					String memId = req.getParameter("memId");
					String memDelete = req.getParameter("memDelete");
					
					MemberVO mv = new MemberVO();
					mv.setMemId(memId);
					mv.setMemDelete(memDelete);
					
					//2.서비스 객체 생성
					IMemberService memberService = MemberServiceImpl.getInstance();
					
					//3.보내기
					int cnt = memberService.memDelete(mv);
					
					if(cnt > 0) {
						//업데이트 성공
						req.setAttribute("cnt", cnt);
					}
					
					String url = "/views/mypage/memDeleteChk.jsp";
					return url;
				}
			}
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
