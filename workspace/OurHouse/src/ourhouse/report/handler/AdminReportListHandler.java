package ourhouse.report.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.common.vo.PReportVO;
import ourhouse.common.vo.RReportVO;
import ourhouse.report.service.IReportService;
import ourhouse.report.service.ReportServiceImpl;

public class AdminReportListHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/admin/adminReportList.jsp";
	private static final String MAIN_PAGE = "/main.do";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession(false);
		
		if(session == null) {
			// session이 null일 경우(로그인이 안되었을 경우) 메인화면으로 이동
			return MAIN_PAGE;
		}else {
			// 
			MemberVO mv = (MemberVO) session.getAttribute("session");
			if(mv ==null) {
				return MAIN_PAGE;
			}else {
				String memId = mv.getMemId();
				if("admin".equals(memId)) {
					IReportService reportService = ReportServiceImpl.getInstance();
					
					List<PReportVO> preportList = reportService.preportList();
					List<RReportVO> rreportList = reportService.rreportList();
					
					req.setAttribute("preportList", preportList);
					req.setAttribute("rreportList", rreportList);
					
					return VIEW_PAGE;
				}else {
					return MAIN_PAGE;
				}
			}
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
