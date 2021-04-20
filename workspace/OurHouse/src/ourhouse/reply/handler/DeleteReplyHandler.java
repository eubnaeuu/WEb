package ourhouse.reply.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.ReplyVO;
import ourhouse.photo.handler.WritePhotoHandler;
import ourhouse.reply.service.IReplyService;
import ourhouse.reply.service.ReplyServiceImpl;

public class DeleteReplyHandler implements CommandHandler{

	private static final String GSON_PAGE = "/views/MycommonJson.jsp";
	protected static Logger logger = Logger.getLogger(WritePhotoHandler.class);

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//gson용 map
		Map<Object, Object> jsonMap = new HashMap<>();
		
		// 서비스객체생성
		IReplyService replyService = ReplyServiceImpl.getInstance();
		
		// delete
		int replyNo = Integer.parseInt(req.getParameter("replyNo"));
		
		int cnt = replyService.deleteReply(replyNo);
		
		// gson에 세션도담아갈것
		Gson gson = new Gson();

		String jsonString = null;
		String sessionId = "chuchu"; // 세션으로 교체해야함
		
		if (cnt > 0) {
			jsonMap.put("msg", "성공");
		}else { //실패시
			jsonMap.put("msg", "실패");
		}
		jsonMap.put("sessionId", sessionId);
		
		jsonString = gson.toJson(jsonMap);
		req.setAttribute("jsonString", jsonString);
		
		return GSON_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
