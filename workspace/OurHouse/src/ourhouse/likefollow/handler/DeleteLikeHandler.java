package ourhouse.likefollow.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.LikesVO;
import ourhouse.likefollow.service.ILikeFollowService;
import ourhouse.likefollow.service.LikeFollowServiceImpl;

public class DeleteLikeHandler implements CommandHandler{

	private static final String GSON_PAGE = "/views/MycommonJson.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//gson용 map
		Map<Object, Object> jsonMap = new HashMap<>();
		
		// 서비스객체생성
		ILikeFollowService likefollowService = LikeFollowServiceImpl.getInstance();
		
		// delete
		int postNo = Integer.parseInt(req.getParameter("postNo"));
		String sessionId = req.getParameter("sessionId");
		
		LikesVO paramVO = new LikesVO();
		paramVO.setPostNo(postNo);
		paramVO.setMemId(sessionId);
		
		int cnt = likefollowService.deleteLike(paramVO);
		
		Gson gson = new Gson();
		String jsonString = null;
		
		if (cnt > 0) {
			jsonMap.put("msg", "성공");
		}else { //실패시
			jsonMap.put("msg", "실패");
		}
		
		jsonString = gson.toJson(jsonMap);
		req.setAttribute("jsonString", jsonString);
		
		return GSON_PAGE;	
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
