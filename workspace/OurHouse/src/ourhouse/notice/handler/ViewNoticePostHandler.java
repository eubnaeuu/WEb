package ourhouse.notice.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.NoticeVO;
import ourhouse.notice.service.INoticeService;
import ourhouse.notice.service.NoticeServiceImpl;

public class ViewNoticePostHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/notice/noticePost.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int noticeNo = Integer.parseInt(req.getParameter("noticeNo"));
		
		// 서비스 객체 생성
		INoticeService noticeService = NoticeServiceImpl.getInstance();
		
		// 게시물 정보 조회
		NoticeVO nvo = new NoticeVO();
		nvo.setNoticeNo(noticeNo);

		NoticeVO noticeVO = noticeService.getNotice(noticeNo);
		
		req.setAttribute("noticeVO", noticeVO);
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
