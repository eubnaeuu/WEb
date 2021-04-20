package ourhouse.notice.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.NoticeVO;
import ourhouse.notice.service.INoticeService;
import ourhouse.notice.service.NoticeServiceImpl;

public class InsertNoticeHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/admin/writeNotice.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		} else {
			// 1.Parameter Value가져오기
			String noticeTitle = req.getParameter("notice_title");
			String noticeContent = req.getParameter("notice_content");
			
			// 2.서비스 객체 생성
			INoticeService noticeService = NoticeServiceImpl.getInstance();
			
			// 3.데이터 입력
			NoticeVO nvo = new NoticeVO();
			nvo.setNoticeTitle(noticeTitle);
			nvo.setNoticeContent(noticeContent);
			
			int cnt = noticeService.insertNotice(nvo);
			
			String url = "/admin/noticeList.do";
			
			return url;
			
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return true; //리다이렉
 		} else {
			return false;
		}
	}
}