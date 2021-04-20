package ourhouse.qna.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;

public class WriteQnaHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/qna/writeQna.jsp";
	private static final String MAIN_PAGE = "/main.do";
	private static final String GSON_PAGE = "/views/MycommonJson.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

}
