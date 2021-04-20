package ourhouse.report.dao;

import java.util.List;

import ourhouse.common.vo.PReportVO;
import ourhouse.common.vo.RReportVO;
import ourhouse.common.vo.ReasonVO;

public interface IReportDao {

	List<PReportVO> PReportList() throws Exception;

	List<RReportVO> RReportList() throws Exception;

	int deleteReply(int replyNo) throws Exception;

	int deletePost(int postNo) throws Exception;

	/**
	 * 신고 사유 조회 메서드
	 * @return reasonVO 담긴 리스트
	 * @author 이경륜
	 */
	List<ReasonVO> selectReasonList() throws Exception;

	
	/**
	 * 게시글 신고 
	 * @param pReportVO (게시글번호, 신고사유)
	 * @return 성공시 1
	 */
	int insertPReport(PReportVO pReportVO) throws Exception;
	
	/**
	 * 댓글 신고 
	 * @param rReportVO (댓글번호, 신고사유)
	 * @return 성공시 1
	 */
	int insertRReport(RReportVO rReportVO) throws Exception;
	
	

}
