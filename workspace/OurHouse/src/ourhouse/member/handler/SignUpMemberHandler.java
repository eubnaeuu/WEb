package ourhouse.member.handler;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
/**
 * 회원가입 화면
 * @author 김지현
 */
public class SignUpMemberHandler implements CommandHandler{
	
	
	private static final String VIEW_PAGE = "/views/signup/signupMailAuth.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//겟방식으로 오면 회원가입 페이지로
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE; //보여지는 화면..
		}else {
			//입력하고 나서 post방식
			//1.요청 파라미터 정보 가져오기
			String memEmail = req.getParameter("memEmail");
			String memId = req.getParameter("memId");
			String memPass = req.getParameter("memPass");
			String memNickname = req.getParameter("memNickname");
			
			//2.서비스 객체 생성하기
			IMemberService memberService = MemberServiceImpl.getInstance();
			
			//3.회원정보 등록
			MemberVO paramVO = new MemberVO();
			BeanUtils.populate(paramVO, req.getParameterMap());
			
			Object result = memberService.signupMember(paramVO);
			
			req.setAttribute("result", result);
			
			//리다이렉으로 하면 req객체가 날아간다
			//json으로 만들어주는 공장으로..
			String url = "/views/signup/signupMember.jsp";
			return url;
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return true; //redirect
		}else {
			return true; //forward
		}
	}

}
