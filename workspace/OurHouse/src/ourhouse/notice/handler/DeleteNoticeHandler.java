package ourhouse.notice.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.notice.service.INoticeService;
import ourhouse.notice.service.NoticeServiceImpl;

public class DeleteNoticeHandler implements CommandHandler{
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 1. 요청할 공지사항번호의 파라미터 정보를 가져온다.
		int noticeNo = Integer.parseInt(req.getParameter("noticeNo"));
		
		// 2. 서비스 객체 생성
		INoticeService noticeService = NoticeServiceImpl.getInstance();
		
		// 3. 서비스에 요청
		int cnt = noticeService.deleteNotice(noticeNo);
		
		String url = "/admin/noticeList.do";
		
		return url;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}