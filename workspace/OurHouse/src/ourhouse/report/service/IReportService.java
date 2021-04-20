package ourhouse.report.service;

import java.util.List;

import ourhouse.common.vo.PReportVO;
import ourhouse.common.vo.RReportVO;
import ourhouse.common.vo.ReasonVO;

public interface IReportService {

	/**
	 * 게시물 신고 목록 조회 메서드
	 * @return 게시물 신고 목록
	 * @author 서대철
	 */
	List<PReportVO> preportList();

	/**
	 * 댓글 신고 목록 조회 메서드
	 * @return 댓글 신고 목록
	 * @author 서대철
	 */
	List<RReportVO> rreportList();

	/**
	 * 댓글 삭제 메서드
	 * @param replyNo
	 * @return 삭제가 완료되면 cnt가 1로 반환
	 * @author 서대철
	 */
	int deleteReply(int replyNo);
	
	/**
	 * 게시물 삭제 메서드
	 * @param postNo
	 * @return 삭제가 완료되면 cnt를 1로 반환
	 * @author 서대철
	 */
	int deletePost(int postNo);

	/**
	 * 신고 사유 조회 메서
	 * @return reasonVO 담긴 리스트
	 * @author 이경륜
	 */
	List<ReasonVO> selectReasonList();

	/**
	 * 게시글 신고 
	 * @param pReportVO (게시글번호, 신고사유)
	 * @return 성공시 1
	 */
	int insertPReport(PReportVO pReportVO);
	
	/**
	 * 댓글 신고 
	 * @param rReportVO (댓글번호, 신고사유)
	 * @return 성공시 1
	 */
	int insertRReport(RReportVO rReportVO);

}
