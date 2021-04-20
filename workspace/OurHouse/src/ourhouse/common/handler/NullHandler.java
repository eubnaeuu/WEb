package ourhouse.common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;

/**
 * 웹컨트롤러에서 해당 요청을 처리할 핸들러를 발견하지 못한 경우 사용됨
 */
public class NullHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404
		return null;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}
	
}
