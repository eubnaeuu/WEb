package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
/**
 * 닉네임 중복체크
 * @author 김지현 
 */
public class MemberNickNameCheckHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//req한테 받아와서 세팅
		String memNickname = req.getParameter("memNickname");
		
		//서비스 객체 생성
		IMemberService memberService = MemberServiceImpl.getInstance();
		//서비스호출해서 '목록조회' 수행
		MemberVO resultVO = memberService.selectNickName(memNickname);
		
		if(resultVO == null) { //일치하는 별명이 없다면
			req.setAttribute("cnt", 0);
		}else {
			req.setAttribute("cnt", 1);
		}
		
		//json처리 (아이디중복체크와 동일)
		String url = "/views/signup/idDupCheck.jsp";
		return url;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
