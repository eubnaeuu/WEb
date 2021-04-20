package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourhouse.common.handler.CommandHandler;
/**
 * 입력한 인증키가 맞는지 체크하는 핸들러 
 */
public class MemberMailAuthChkHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String authKey = (String)req.getSession().getAttribute("authKey");
		
		//이메일
//		String userMail = req.getParameter("userMail");
		//사용자가 입력한 인증키 
		String userAuth = req.getParameter("userAuth");
	    
	    int cnt = 0;
	    
	    //보낸 인증키와 사용자가 입력한 인증키가 같다면 cnt를 1로 반환
	    if(authKey.equals(userAuth)){
	    	//성공할때 메일을 세션에 넣는다
	    	cnt = 1;
	    	//사용자가 입력한 메일주소를 세션에 담아서 회원가입할때 꺼낸다.
	    }
	    req.setAttribute("cnt", cnt); // 보낼 때는 Attribute로!
		
//	    HttpSession session = req.getSession();
//	    session.setAttribute("userMail", userMail);
	    
		String url = "/views/signup/authChk2.jsp";
		return url;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
