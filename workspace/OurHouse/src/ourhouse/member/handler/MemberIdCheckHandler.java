package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

public class MemberIdCheckHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//req에서 받아와서 세팅

		String memId = req.getParameter("memId");
		
		//서비스객체생성
		IMemberService memberService = MemberServiceImpl.getInstance();
		//서비스호출해서 '목록 조회' 수행
		MemberVO resultVO = memberService.selectMember(memId);
		
		//닉네임 중복체크
//		String memNickname = req.getParameter("memNickname");//사용자 입력
//		String voNickname = resultVO.getMemNickname(); //vo에 있는 닉네임
		
		//조회결과를 req한테 담아서 전송
		if(resultVO == null) { //일치한 아이디가 없다면..
			req.setAttribute("cnt", 0);
		}else{
			req.setAttribute("cnt", 1);
		}
		
		//json처리하러
		String url = "/views/signup/idDupCheck.jsp";
		return url;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false; //포워드
	}

}
