package ourhouse.notice.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.NoticeVO;
import ourhouse.notice.service.INoticeService;
import ourhouse.notice.service.NoticeServiceImpl;

public class UpdateNoticeHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/admin/updateNotice.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			int notice_no = Integer.parseInt(req.getParameter("noticeNo"));
			
			// 1. 서비스 객체 생성
			INoticeService noticeService = NoticeServiceImpl.getInstance();
			
			// 2. 공지사항 정보 조회
			NoticeVO noticeVO = noticeService.getNotice(notice_no);
			
			req.setAttribute("noticeVO", noticeVO);
			
			return VIEW_PAGE;
		} else {
			// 1. Parameter Value 가져오기
			int noticeNo = Integer.parseInt(req.getParameter("noticeNo"));
			System.out.println(noticeNo);
			String noticeTitle = req.getParameter("noticeTitle");
			String noticeContent = req.getParameter("noticeContent");
			String updateDate = req.getParameter("updateDate");
			
			// 2. 서비스 객체 생성
			INoticeService noticeService = NoticeServiceImpl.getInstance();
			
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setNoticeNo(noticeNo);
			noticeVO.setNoticeTitle(noticeTitle);
			noticeVO.setNoticeContent(noticeContent);
			noticeVO.setUpdateDate(updateDate);
			
			int cnt = noticeService.updateNotice(noticeVO);
			
			String url = req.getContextPath() + "/admin/noticeList.do";
			
			return url;
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		} else {
			return true;
		}
	}

}
