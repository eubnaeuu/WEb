package ourhouse.reply.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.ReplyVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
import ourhouse.reply.service.IReplyService;
import ourhouse.reply.service.ReplyServiceImpl;

public class WriteReplyHandler implements CommandHandler {

	private static final String GSON_PAGE = "/views/MycommonJson.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		IReplyService replyService = ReplyServiceImpl.getInstance();
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		//gson용 map
		Map<Object, Object> jsonMap = new HashMap<>();
		
		ReplyVO paramVO = new ReplyVO();
		paramVO.setPostNo(Integer.parseInt(req.getParameter("postNo")));
		paramVO.setMemId(req.getParameter("memId"));
		paramVO.setReplyContent(req.getParameter("replyContent"));
		
		// select키로 replyNo 받아오자
		
		// insert
		int replyNo = replyService.insertReply(paramVO);
		
		// 포인트 50점추가
		int cnt = memberService.updateReplyPoint(paramVO.getReplyNo());
		
		// 댓글 관련정보불러오기 
		ReplyVO replyVO = replyService.selectReply(paramVO.getReplyNo());
		
		
		// gson에 세션도담아갈것
		Gson gson = new Gson();

		String jsonString = null;
		String sessionId = "chuchu"; // 세션으로 교체해야함
		
		if (replyNo > 0 && replyVO != null) {
			jsonMap.put("msg", "성공");
			jsonMap.put("replyVO", replyVO);
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
