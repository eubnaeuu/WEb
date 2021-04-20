package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

public class MemberFindIdHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/signin/findId.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}else {
			//1.요청 파라미터 정보 가져오기
			String memEmail = req.getParameter("memEmail");
			
			MemberVO mv = new MemberVO();
			mv.setMemEmail(memEmail);
			
			//2.서비스 객체 생성
			IMemberService memberService = MemberServiceImpl.getInstance();
			MemberVO resultVO = memberService.selectEmail(memEmail);
			
			req.setAttribute("resultVO", resultVO);

			//3.json공장으로
			String url = "/views/signin/findId_json.jsp";
			return url;
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return true; //리다이렉
		}else {
			return false;//포워드
		}
	}
}
