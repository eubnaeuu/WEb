package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

/**
 * 마이페이지 => 인증번호확인하고 = > 비밀번호재설정 
 * @author 김지현
 */
public class MyPageMemPassChangeHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/mypage/mypageMemPassChange.jsp";
	private static final String LOGIN_PAGE = "/member/signin.do";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession(false);
		if(session == null) {
			return LOGIN_PAGE;
		}else {
			MemberVO memVO = (MemberVO) session.getAttribute("session");
			if(memVO==null) {
				return LOGIN_PAGE;
			}else {
				if(req.getMethod().equals("GET")) {
					//세션에서 받아온 로그인회원정보에서 받은 이메일
					String memEmail = memVO.getMemEmail();
					req.setAttribute("memEmail", memEmail);
					
					//비밀번호 변경하려면 이메일과 사용자가 입력한 비밀번호 있어야댐
					return VIEW_PAGE;
				}else {
					//1.요청 파라미터 정보 가져오기
					String memEmail = req.getParameter("memEmail");
					String memPass = req.getParameter("memPass");
					
					//2.서비스 객체 생성하기
					IMemberService memberService = MemberServiceImpl.getInstance();
					
					//3.사용자가 입력한 것으로 수정
					MemberVO mv = new MemberVO();
					mv.setMemEmail(memEmail);
					mv.setMemPass(memPass);
					
					int cnt = memberService.updateMemPass(mv);
					
					if(cnt>0) {
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
