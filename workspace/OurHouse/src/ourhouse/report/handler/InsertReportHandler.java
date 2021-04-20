package ourhouse.report.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.PReportVO;
import ourhouse.common.vo.RReportVO;
import ourhouse.report.service.IReportService;
import ourhouse.report.service.ReportServiceImpl;

public class InsertReportHandler implements CommandHandler{

	private static final String GSON_PAGE = "/views/MycommonJson.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		//gson용 map
		Map<Object, Object> jsonMap = new HashMap<>();
		
		// 서비스객체생성 
		IReportService reportService = ReportServiceImpl.getInstance();
		
		
		String reasonId = req.getParameter("reasonId");
		
		int cnt = 0; // 성공결과
		// 글인지 댓글인지 판단
		if(req.getParameter("preport") != null) { // 글일
			int postNo = Integer.parseInt(req.getParameter("preport"));
			PReportVO pReportVO = new PReportVO();
			pReportVO.setPostNo(postNo);
			pReportVO.setReasonId(reasonId);
			cnt = reportService.insertPReport(pReportVO);
		}else {
			int replyNo = Integer.parseInt(req.getParameter("rreport"));
			RReportVO rReportVO = new RReportVO();
			rReportVO.setReplyNo(replyNo);
			rReportVO.setReasonId(reasonId);
			cnt = reportService.insertRReport(rReportVO);
		}
		
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
