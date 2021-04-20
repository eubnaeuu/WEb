package ourhouse.post.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import ourhouse.common.handler.CommandHandler;
import ourhouse.post.service.IPostService;
import ourhouse.post.service.PostServiceImpl;
/**
 * 사진 및 qna 게시글 삭제 핸들러
 * 매핑 /post/delete.do
 * @author 이경륜
 *
 */
public class DeletePostHandler implements CommandHandler{

	private static final String GSON_PAGE = "/views/MycommonJson.jsp";
	protected static Logger logger = Logger.getLogger(DeletePostHandler.class);
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//gson용 map
		Map<Object, Object> jsonMap = new HashMap<>();
		
		IPostService postService = PostServiceImpl.getInstance();
		
		int postNo = Integer.parseInt(req.getParameter("postNo"));
		
		int cnt = postService.deletePost(postNo);
		
		// gson에 세션도담아갈것
		Gson gson = new Gson();
		String jsonString = null;
		String sessionId = "chichi"; // 세션으로 교체해야함
		
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
