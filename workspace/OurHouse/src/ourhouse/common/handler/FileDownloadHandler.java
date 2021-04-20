package ourhouse.common.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.service.AtchFileServiceImpl;
import ourhouse.common.service.IAtchFileService;

public class FileDownloadHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 쿼리스트링에 있던 파라미터 꺼내주기
		long atchFileId = req.getParameter("fileId") != null ? Long.parseLong(req.getParameter("fileId")) : 0;
		int fileSn = req.getParameter("fileSn") != null ? Integer.parseInt(req.getParameter("fileSn")) : 0;
		
		// 서비스 객체 생성
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		// paramVO에 셋팅
		AtchFileVO paramVO = new AtchFileVO();
		paramVO.setAtchFileId(atchFileId);
		paramVO.setFileSn(fileSn);
		
		// 파일정보 조회
		AtchFileVO atchFileVO = fileService.getAtchFile(paramVO);
		
		if(atchFileVO != null) {
			
			// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
			resp.setContentType("application/octet-stream"); // 이미지가 binary data이기때문에 html 아니고 octet-stream
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(atchFileVO.getOrignlFileNm(), "utf-8") + "\""); //다운로드니까 attachment 꼭 넣어줘야함

			// 저장경로에서 파일 읽어와서 출력하기
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(atchFileVO.getFileStreCours()));
			BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
			
			int readBytes = 0;
			while ( (readBytes = bis.read()) != -1 ) {
				bos.write(readBytes);
			}
			
			bis.close();
			bos.close();
		}
		
		return null;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
