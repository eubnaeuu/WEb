package ourhouse.common.service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.ProfileImgVO;
import ourhouse.common.dao.AtchFileDaoImpl;
import ourhouse.common.dao.IAtchFileDao;
import ourhouse.util.FileUploadRequestWrapper;

public class AtchFileServiceImpl implements IAtchFileService{

	private static IAtchFileService fileService;
	private IAtchFileDao fileDao;
	
	private AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if (fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		return fileService;
	}
	
	/**
	 * 사진게시물 첨부파일 로컬에 저장하는 메서드
	 * @author 이경륜
	 */
	@Override
	public int saveAtchFile(AtchFileVO paramVO, FileItem item) throws Exception{
		
		// 1. 디렉토리 생성
		File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY); // 워크스페이스 내 uploads 폴더
		
		if(!uploadDir.exists()) { // 경로 없으면 만들어주기
			uploadDir.mkdir();
		}
		
		// 2. 저장에 필요한 데이터 정리
		String orignlFileNm = new File(item.getName()).getName(); // 파일명.확장자명
		
		long fileSize = item.getSize(); // 파일사이즈
		String fileExtsn = orignlFileNm.lastIndexOf(".") < 0 ? "" : orignlFileNm.substring(orignlFileNm.lastIndexOf(".")+1);
		
		String streFileNm = ""; // 파일저장명 (밑에서 UUID로 생성할것)
		String fileStreCours = ""; // 파일저장경로
		File storeFile = null;
		
		// 2-1. 유일한 파일 저장명 만들기
		do {
			streFileNm = UUID.randomUUID().toString().replace("-", "") + "." + fileExtsn; // 랜덤id값 추출
			fileStreCours = FileUploadRequestWrapper.UPLOAD_DIRECTORY + File.separator + streFileNm;
			storeFile = new File(fileStreCours);
		} while(storeFile.exists()); // 파일명이 중복되지않을 때까지 반복
		
		// 3. 업로드한 파일 로컬에 저장하기
		item.write(storeFile);
		
		// 4. 업로드한 파일을 로컬에 저장한 후 파일 정보 저장
		AtchFileVO atchFileVO = new AtchFileVO();
		/* atchFileId는 시퀀스로 db에서 처리 */
		atchFileVO.setPostNo(paramVO.getPostNo());	// 사진글NO
		atchFileVO.setFileSn(paramVO.getFileSn());	// 파일순번 (일단은 1개만 업로드중)
		atchFileVO.setFileCn(paramVO.getFileCn());	// 파일에 딸린 글내용
		atchFileVO.setFileStreCours(fileStreCours);	// 저장경로
		atchFileVO.setOrignlFileNm(orignlFileNm);	// 원본파일명
		atchFileVO.setStreFileNm(streFileNm);		// 저장할 파일명
		atchFileVO.setFileExtsn(fileExtsn);			// 확장자명
		atchFileVO.setFileSize(fileSize);			// 파일사이즈
		/* creatDt는 db에서 sysdate로 처리*/
		
		int cnt = fileDao.insertAtchFile(atchFileVO);
		
		item.delete(); // 레파지토리에 임시 업로드된 것을 지워주는 작업 (업로드한 것은 잘 남아 있음)
		
		return cnt;
	}
	
	/**
	 * 프로필사진 첨부파일 로컬에 저장하는 메서드
	 * @author 김지현, 이경륜
	 */
	@Override
	public int saveAtchFile(ProfileImgVO paramVO, FileItem item) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	
	/* 아래는 기존 수업시간 메서드들 */
	
	/**
	 * 첨부파일을 저장하기 위한 메서드
	 */
	@Override
	public AtchFileVO saveAtchFile(FileItem item) throws Exception {
		
		// 1. 파일 아이템을 업로드 폴더(로컬)에 저장하기
		File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
		
		if(!uploadDir.exists()) { // 경로 없으면 만들어주기
			uploadDir.mkdir();
		}
		
		// 1-1. 전체 경로를 제외한 파일명만 추출하기.
		String orignlFileNm = new File(item.getName()).getName(); // substring도 가능
		
		// 1-2. 파일 사이즈 가져오기
		long fileSize = item.getSize();
		
		// 1-3. 저장할 파일명
		String streFileNm = "";
		
		// 1-4. 저장경로
		String fileStreCours = "";
		
		File storeFile = null;
		
		// 1-5. 유일한 파일 저장명 만들기
		do {
			streFileNm = UUID.randomUUID().toString().replace("-", ""); // 랜덤id값 추출
			fileStreCours = FileUploadRequestWrapper.UPLOAD_DIRECTORY + File.separator + streFileNm;
			storeFile = new File(fileStreCours);
		} while(storeFile.exists()); // 파일명이 중복되지않을 때까지 반복
		
		// 1-6. 확장자 이름 추출
		String fileExtsn = orignlFileNm.lastIndexOf(".") < 0 ? "" : orignlFileNm.substring(orignlFileNm.lastIndexOf(".")+1);
		
		// 1-7. 업로드한 파일 로컬에 저장하기
		item.write(storeFile);
		
		// 2. 업로드한 파일을 로컬에 저장한 후 파일 정보 저장
		AtchFileVO atchFileVO = new AtchFileVO();
		/* atchFileId는 selectKey로 자동 셋팅 */
		atchFileVO.setStreFileNm(streFileNm);		// 저장할 파일명
		atchFileVO.setFileSize(fileSize);			// 파일사이즈
		atchFileVO.setOrignlFileNm(orignlFileNm);	// 원본파일명
		atchFileVO.setFileExtsn(fileExtsn);			// 확장자명
		atchFileVO.setFileStreCours(fileStreCours);	// 저장경로
		
		fileDao.insertAtchFile(atchFileVO);
		
		item.delete(); // 레파지토리에 임시 업로드된 것을 지워주는 작업 (업로드한 것은 잘 남아 있음)
		return atchFileVO;
	}

	/**
	 * 첨부파일 아이디로 첨부파일 목록 정보 조회
	 */
	@Override
	public List<AtchFileVO> getAtchFileList(long atchFileId) throws Exception {
		List<AtchFileVO> fileVOList = fileDao.getAtchFileList(atchFileId);
		return fileVOList;
	}

	/**
	 * 파일 ID와 파일 순번에 해당하는 첨부파일 조회
	 */
	@Override
	public AtchFileVO getAtchFile(AtchFileVO paramVO) throws SQLException {
		AtchFileVO fileVO = fileDao.getAtchFile(paramVO);
		return fileVO;
	}




}
