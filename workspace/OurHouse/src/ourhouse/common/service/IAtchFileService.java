package ourhouse.common.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.ProfileImgVO;

public interface IAtchFileService {

	/**
	 * 사진게시물 첨부파일 로컬에 저장하는 메서드
	 * @param paramVO (postNo, fileSn, fileCn 담고있음)
	 * @param item 저장할 fileitem 객체
	 * @return 성공 1
	 * @author 이경륜
	 */
	public int saveAtchFile(AtchFileVO paramVO, FileItem item) throws Exception;
	
	
	/**
	 * 프로필사진 첨부파일 로컬에 저장하는 메서드
	 * @param paramVO (memId 담고있음)
	 * @param item 저장할  fileItem 객체
	 * @return 성공 1
	 * @author 김지현, 이경륜
	 */
	public int saveAtchFile(ProfileImgVO paramVO, FileItem item) throws Exception;
	
	
	
	
	/*
	 * 아래는 수업시간에 참고했던 소스임
	 * 우리와 테이블 구성이 달라 수정해야할 것임 
	 */
	
	/**
	 * 첨부파일을 로컬에 저장하기 위한 메서드
	 * @param fileItem 저장할 FileItem객체
	 * @return AtchFileVO 저장한 첨부파일 정보 (젤 중요한건 atch_file_id)
	 * @throws Exception
	 */
	public AtchFileVO saveAtchFile(FileItem fileItem) throws Exception;

	/**
	 * 첨부파일 아이디로 첨부파일 목록 정보 조회
	 * @param atchFileId 첨부파일ID
	 * @return 첨부파일 상세목록
	 * @throws Exception
	 */
	public List<AtchFileVO> getAtchFileList(long atchFileId) throws Exception;

	/**
	 * 파일 ID와 파일 순번에 해당하는 첨부파일 조회
	 * @param paramVO (atchFileId와 fileSn이 담겨있음)
	 * @return 첨부파일
	 * @throws SQLException
	 */
	public AtchFileVO getAtchFile(AtchFileVO paramVO) throws SQLException;


	












}
