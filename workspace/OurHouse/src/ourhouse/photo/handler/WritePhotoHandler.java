package ourhouse.photo.handler;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import ourhouse.code.service.CodeServiceImpl;
import ourhouse.code.service.ICodeService;
import ourhouse.common.handler.CommandHandler;
import ourhouse.common.service.AtchFileServiceImpl;
import ourhouse.common.service.IAtchFileService;
import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.ColorVO;
import ourhouse.common.vo.HouseVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.common.vo.PostVO;
import ourhouse.common.vo.RoomVO;
import ourhouse.common.vo.SpaceVO;
import ourhouse.common.vo.StyleVO;
import ourhouse.photo.service.IPhotoService;
import ourhouse.photo.service.PhotoServiceImpl;
import ourhouse.util.FileUploadRequestWrapper;

public class WritePhotoHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/views/photo/writePhoto.jsp";
	private static final String MAIN_PAGE = "/main.do";
	private static final String GSON_PAGE = "/views/MycommonJson.jsp";
	
	protected static Logger logger = Logger.getLogger(WritePhotoHandler.class);
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// ★ 로그인 세션받아오기
		HttpSession session = req.getSession(false);
		String memId = null;
		
		if(session == null) { // 세션 생성 이전 (비로그인)
			memId = " "; // 비로그인시 db에 로그인아이디를 공백으로 조회하기위함
			return MAIN_PAGE;
			
		}else { // 세션받아왔으나 비로그인
			MemberVO loginVO = (MemberVO) session.getAttribute("session");
			
			if(loginVO == null) {
				memId = " ";
				return MAIN_PAGE;
			
			}else { // 로그인상태
				memId = loginVO.getMemId();
				return getJspPage(req, resp, memId);
				
			}
		}
		
		
	}

	private String getJspPage(HttpServletRequest req, HttpServletResponse resp, String memId) throws Exception {
		// 서비스 객체 생성
		IPhotoService photoService = PhotoServiceImpl.getInstance();
		ICodeService codeService = CodeServiceImpl.getInstance(); // 셀렉트 박스 채우기 위함
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();

		if("GET".equals(req.getMethod())) { //작성페이지 도착시
			
			// 카테고리 목록 불러오기
			List<HouseVO> houseList = codeService.selectHouseList();
			List<RoomVO> roomList = codeService.selectRoomList();
			List<SpaceVO> spaceList = codeService.selectSpaceList();
			List<StyleVO> styleList = codeService.selectStyleList();
			List<ColorVO> colorList = codeService.selectColorList();
			req.setAttribute("houseList", houseList);
			req.setAttribute("roomList", roomList);
			req.setAttribute("spaceList", spaceList);
			req.setAttribute("styleList", styleList);
			req.setAttribute("colorList", colorList);
			
			return VIEW_PAGE; // 글작성폼으로 이동
		
		} else { // POST 방식으로 글 작성 완료시
			
			// 1. post 테이블에 insert 후 postNo (키값)을 받아오기 atch_file 테이블에 파일 정보를 함께 입력할 것
			PostVO postVO = new PostVO();
			BeanUtils.populate(postVO, req.getParameterMap());

			postVO.setMemId(memId); // sessionId
			
			int cntPost = photoService.writePhoto(postVO);
			
			// 여러개 보내는소스
			int fileCount = Integer.parseInt(req.getParameter("fileCount"));
			
			int cntFile = 0; // file 저장 확인용
			
			for(int i = 0 ; i < fileCount ; i++ ) {
				FileItem item = ((FileUploadRequestWrapper)req).getFileItem("atchFile"+(i+1));
				AtchFileVO atchFileVO = new AtchFileVO();
				
				if(item != null && !item.getName().equals("")) { // 파일이 있으면
					
					// 1. 파일첨부시 필요한 컬럼 데이터 준비하기
					AtchFileVO paramVO = new AtchFileVO();
					paramVO.setPostNo(postVO.getPostNo());
					paramVO.setFileSn(i+1); // 파일순번
					paramVO.setFileCn(req.getParameter("fileCn"+(i+1))); // 해당 파일의글 내용
					
					// 2. 파일 업로드(후 db저장) 기능 호출
					// 게시글 먼저 post 테이블에 저장후 post_no를 postVO에 저장해옴. 그 받아온 post_no를 사진 첨부시 같이 넣어줄 것
					// 파일저장순서: 로컬에 저장된 후 db에 insert됨
					
					cntFile += fileService.saveAtchFile(paramVO, item); // 파일정보, 파일
				}
			}
			
			Map<Object, Object> jsonMap = new HashMap<>();
			
			Gson gson = new Gson();
			
			String jsonString = null;
			
			if(cntPost > 0 && cntFile == fileCount) {
				jsonMap.put("msg", "성공");
				jsonMap.put("postNo", postVO.getPostNo());
			}else { //실패시
				jsonMap.put("msg", "실패");
			}
			
			jsonString = gson.toJson(jsonMap);
			req.setAttribute("jsonString", jsonString);
			
			return GSON_PAGE;
			
		}
	}
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
