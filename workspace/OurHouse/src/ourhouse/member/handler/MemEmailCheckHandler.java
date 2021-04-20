package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
/**
 * 이메일 중복체크
 * @author 김지현 
 */
public class MemEmailCheckHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1.req한테 받아와서 세팅
		String memEmail = req.getParameter("memEmail");
		
		//2.서비스객체 생성
		IMemberService memberService = MemberServiceImpl.getInstance();
		//서비스호출해서 '목록조회'수행
		MemberVO resultVO = memberService.selectEmail(memEmail);
		
		if(resultVO == null) {//일치한 이메일이 없다면..
			req.setAttribute("cnt", 0);
		}else {
			req.setAttribute("cnt", 1);
		}
		
		//json처리하러
		String url = "/views/signup/idDupCheck.jsp";
		return url;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
