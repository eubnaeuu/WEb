package ourhouse.report.service;

import java.util.ArrayList;
import java.util.List;

import ourhouse.common.vo.PReportVO;
import ourhouse.common.vo.RReportVO;
import ourhouse.common.vo.ReasonVO;
import ourhouse.report.dao.IReportDao;
import ourhouse.report.dao.ReportDaoImpl;

public class ReportServiceImpl implements IReportService{
	private static IReportService reportService;
	
	private IReportDao reportDao;
	
	private ReportServiceImpl() {
		reportDao = ReportDaoImpl.getInstance();
	}
	
	public static IReportService getInstance() {
		if (reportService == null) {
			reportService = new ReportServiceImpl();
		}
		return reportService;
	}

	@Override
	public List<PReportVO> preportList() {
		List<PReportVO> preportList = new ArrayList<>();
		
		try {
			preportList = reportDao.PReportList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return preportList;
	}

	@Override
	public List<RReportVO> rreportList() {
		List<RReportVO> rreportList = new ArrayList<>();
		
		try {
			rreportList = reportDao.RReportList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rreportList;
	}

	@Override
	public int deleteReply(int replyNo) {
		int cnt = 0;
		
		try {
			cnt = reportDao.deleteReply(replyNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deletePost(int postNo) {
		int cnt = 0;
		
		try {
			cnt = reportDao.deletePost(postNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<ReasonVO> selectReasonList() {
		List<ReasonVO> reasonList = new ArrayList<>();
		
		try {
			reasonList = reportDao.selectReasonList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reasonList;
	}

	@Override
	public int insertPReport(PReportVO pReportVO) {
		int cnt = 0;
		
		try {
			cnt = reportDao.insertPReport(pReportVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int insertRReport(RReportVO rReportVO) {
		int cnt = 0;
		
		try {
			cnt = reportDao.insertRReport(rReportVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
}
