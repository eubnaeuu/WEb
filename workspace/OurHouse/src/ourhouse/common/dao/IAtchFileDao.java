package ourhouse.common.dao;

import java.sql.SQLException;
import java.util.List;

import ourhouse.common.vo.AtchFileVO;

public interface IAtchFileDao {

	/**
	 * 로컬에 저장된 사진 게시글의 첨부파일 정보를 db에 저장하기 위한 메서드
	 * @param atchFileVO 로컬에 저장된 첨부파일의 정보를 담은 객체
	 * @return 성공 1
	 * @author 이경륜
	 * @throws SQLException
	 */
	public int insertAtchFile(AtchFileVO atchFileVO) throws SQLException;

	/**
	 * 첨부파일 아이디로 첨부파일 목록 정보 조회
	 * @param atchFileId 첨부파일ID
	 * @return 첨부파일 상세목록
	 * @throws SQLException
	 */
	public List<AtchFileVO> getAtchFileList(long atchFileId) throws SQLException;

	/**
	 * 파일 ID와 파일 순번에 해당하는 첨부파일 조회
	 * @param paramVO (atchFileId와 fileSn이 담겨있음)
	 * @return 첨부파일
	 * @throws SQLException
	 */
	public AtchFileVO getAtchFile(AtchFileVO paramVO) throws SQLException;

}
