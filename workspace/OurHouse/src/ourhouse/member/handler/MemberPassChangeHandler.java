package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

public class MemberPassChangeHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/signin/changeMemberPass.jsp"; 
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}
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

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
