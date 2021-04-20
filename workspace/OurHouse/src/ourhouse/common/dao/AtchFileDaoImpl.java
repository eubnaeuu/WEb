package ourhouse.common.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourhouse.common.vo.AtchFileVO;
import ourhouse.util.SqlMapClientFactory;

public class AtchFileDaoImpl implements IAtchFileDao{
	
	private static IAtchFileDao fileDao;
	private SqlMapClient smc;
	
	private AtchFileDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAtchFileDao getInstance() {
		if (fileDao == null) {
			fileDao = new AtchFileDaoImpl();
		}
		return fileDao;
	}

	/**
	 * 로컬에 저장된 첨부파일 정보를 db에 저장하기 위한 메서드
	 * - 수업시간 메서드 그대로 사용함
	 * @author 이경륜
	 */
	@Override
	public int insertAtchFile(AtchFileVO atchFileVO) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("atchFile.insertAtchFile", atchFileVO);
		
		if (obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	/**
	 * 첨부파일 아이디로 첨부파일 목록 정보 조회
	 */
	@Override
	public List<AtchFileVO> getAtchFileList(long atchFileId) throws SQLException {
		
		List<AtchFileVO> fileVOList = smc.queryForList("atchFile.getAtchFileList", atchFileId);
		return fileVOList;
		
	}

	/**
	 * 파일 ID와 파일 순번에 해당하는 첨부파일 조회
	 */
	@Override
	public AtchFileVO getAtchFile(AtchFileVO paramVO) throws SQLException {

		AtchFileVO fileVO = (AtchFileVO) smc.queryForObject("atchFile.getAtchFile", paramVO);
		return fileVO;
	}
	
	
	
}
