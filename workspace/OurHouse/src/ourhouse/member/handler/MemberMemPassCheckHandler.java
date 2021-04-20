package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

/**
 * 사용자가 입력한 새비밀번호와
 * 디비에 있는 비밀번호와 중복검사 
 * @author 김지현
 */
public class MemberMemPassCheckHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//1.req한테 받아와서 세팅
		String memPass = req.getParameter("memPass");
		String memEmail = req.getParameter("memEmail");
		System.out.println(">>>사용자에게 받아온 패스워드 :" + memPass);
		System.out.println(">>>사용자에게 받아온 이메일 :" + memEmail);
		
		MemberVO mv = new MemberVO();
		mv.setMemPass(memPass);
		mv.setMemEmail(memEmail);
		
		//2.서비스 객체 생성
		IMemberService memberService = MemberServiceImpl.getInstance();
		//서비스 호출해서 '새비밀번호'와 같은 비밀번호를 가진 사람이 있는지 조회
		MemberVO resultVO = memberService.selectMemPass(mv);
		
		if(resultVO == null) {//중복된 비밀번호가 없다면..
			req.setAttribute("cnt", 0);
		}else {
			//중복된 비밀번호가 있다면..(현재비밀번호와 같습니다)
			req.setAttribute("cnt", 1);
		}
		
		String url = "/views/signup/idDupCheck.jsp";
		return url;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
