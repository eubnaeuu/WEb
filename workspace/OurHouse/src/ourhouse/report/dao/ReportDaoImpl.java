package ourhouse.report.dao;

import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourhouse.common.vo.PReportVO;
import ourhouse.common.vo.RReportVO;
import ourhouse.common.vo.ReasonVO;
import ourhouse.util.SqlMapClientFactory;

public class ReportDaoImpl implements IReportDao{
	
	private static IReportDao reportDao;
	
	private SqlMapClient smc;
	
	private ReportDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IReportDao getInstance() {
		if (reportDao == null) {
			reportDao = new ReportDaoImpl();
		}
		return reportDao;
		
	}

	@Override
	public List<PReportVO> PReportList() throws Exception {
		List<PReportVO> preportList = new ArrayList<>();
		
		preportList = smc.queryForList("report.PReportList", preportList);
		
		return preportList;
	}

	@Override
	public List<RReportVO> RReportList() throws Exception {
		List<RReportVO> rreportList = new ArrayList<>();
		
		rreportList = smc.queryForList("report.RReportList", rreportList);
		
		return rreportList;
	}

	@Override
	public int deleteReply(int replyNo) throws Exception {
		int cnt = 0;
		
		cnt = smc.delete("report.deleteReply", replyNo);
		
		return cnt;
	}

	@Override
	public int deletePost(int postNo) throws Exception {
		int cnt = 0;
		
		cnt = smc.delete("report.deletePost", postNo);
		
		return cnt;
	}

	@Override
	public List<ReasonVO> selectReasonList() throws Exception {
		
		List<ReasonVO> reasonList = smc.queryForList("report.selectReasonList");
		
		return reasonList;
	}

	@Override
	public int insertPReport(PReportVO pReportVO) throws Exception {
		int cnt = 0;
		
		Object obj = smc.insert("report.insertPReport", pReportVO);
		
		if(obj == null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int insertRReport(RReportVO rReportVO) throws Exception {
		int cnt = 0;
				
		Object obj = smc.insert("report.insertRReport", rReportVO);
		
		if(obj == null) {
			cnt = 1;
		}
		return cnt;
	}
	
}