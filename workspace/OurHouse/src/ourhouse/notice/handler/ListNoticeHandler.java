package ourhouse.notice.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.NoticeVO;
import ourhouse.notice.service.INoticeService;
import ourhouse.notice.service.NoticeServiceImpl;

public class ListNoticeHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/admin/noticeList.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 1. 서비스 객체 생성
		INoticeService noticeService = NoticeServiceImpl.getInstance();
		
		// 2. 공지사항 게시물 조회
		List<NoticeVO> noticeList = new ArrayList<>();
		
		noticeList = noticeService.getNoticeAll();
		
		req.setAttribute("noticeList", noticeList);
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
