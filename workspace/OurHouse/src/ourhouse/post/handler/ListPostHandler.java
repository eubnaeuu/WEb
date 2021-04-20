package ourhouse.post.handler;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ourhouse.code.service.CodeServiceImpl;
import ourhouse.code.service.ICodeService;
import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.CategoryVO;
import ourhouse.common.vo.ColorVO;
import ourhouse.common.vo.HomePhotoVO;
import ourhouse.common.vo.HouseVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.common.vo.PostVO;
import ourhouse.common.vo.RoomVO;
import ourhouse.common.vo.SpaceVO;
import ourhouse.common.vo.StyleVO;
import ourhouse.post.service.IPostService;
import ourhouse.post.service.PostServiceImpl;

/**
 * 홈메인화면 겸 사진 메인화면 리스트
 * @author 이경륜
 */
public class ListPostHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/views/home/home.jsp";
	private static final String GSON_PAGE = "/views/MycommonJson.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		// ★ 로그인 세션받아오기
		HttpSession session = req.getSession(false);
		String memId = null;
		
		if(session == null) { // 세션 생성 이전 (비로그인)
			memId = " "; // 비로그인시 db에 로그인아이디를 공백으로 조회하기위함
			return getJspPage(req, resp, memId);
			
		}else { // 세션받아왔으나 비로그인
			MemberVO loginVO = (MemberVO) session.getAttribute("session");
			
			if(loginVO == null) {
				memId = " ";
				return getJspPage(req, resp, memId);
			
			}else { // 로그인상태
				memId = loginVO.getMemId();
				return getJspPage(req, resp, memId);
			}
		}

	}

	private String getJspPage(HttpServletRequest req, HttpServletResponse resp, String memId) {
		if(req.getMethod().equals("GET")) {	
			req.setAttribute("sessionId", memId); // 메인페이지 팔로우, 좋아요 버튼 작동 위해서 보냄
			
			HomePhotoVO paramVO = new HomePhotoVO();
			paramVO.setMemId(memId);
			
			// 서비스 객체 생성
			IPostService postService = PostServiceImpl.getInstance();
			ICodeService codeService = CodeServiceImpl.getInstance();
			
			// 상단 최신 탑 조회수 글 2개 불러오기
			List<HomePhotoVO> topPhotoList = postService.selectTopPhotos();
			req.setAttribute("topPhotoList", topPhotoList);
			
			// 사진 게시글 전부 불러오기
			List<HomePhotoVO> homePhotoList = postService.selectHomePhotos(paramVO);
			req.setAttribute("homePhotoList", homePhotoList);
			
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
			return VIEW_PAGE;
			
		}else { // post
			// 카테고리를 선택할때마다 가져옴 
			String[] arr = req.getParameterValues("filterArr[]");
			
			CategoryVO categoryVO = new CategoryVO();
			
			if(!(arr == null)) {
				for(int i =0 ; i< arr.length ;i++) {
					if(arr[i].startsWith("H")) {
						categoryVO.setHouseId(arr[i]);
						//break;
					}else if(arr[i].startsWith("R")) {
						categoryVO.setRoomId(arr[i]);
						//break;
					}else if(arr[i].startsWith("S")) {
						categoryVO.setSpaceId(arr[i]);
						//break;
					}else if(arr[i].startsWith("T")) {
						categoryVO.setStyleId(arr[i]);
						//break;
					}else if(arr[i].startsWith("C")) {
						categoryVO.setColorId(arr[i]);
						//break;
					}
					
				}
				
			}else {
				categoryVO.setHouseId("");
				categoryVO.setSpaceId("");
				categoryVO.setRoomId("");
				categoryVO.setStyleId("");
				categoryVO.setColorId("");
			}
//					String memId = "chuchu"; // 테스트용 아이디
			
			categoryVO.setMemId(memId);
			
			IPostService postService = PostServiceImpl.getInstance();
			// 카테고리 적용 사진 게시글 전부 불러오기
			List<HomePhotoVO> homeCatPhotoList = postService.selectCategoryHomePhotos(categoryVO);
			System.out.println(homeCatPhotoList);
			req.setAttribute("homeCatPhotoList", homeCatPhotoList);
			
			// Gson 으로 객체 저장	
			Gson gson = new Gson();
			String jsonString = gson.toJson(homeCatPhotoList);
			req.setAttribute("jsonString", jsonString);
			// Gson 만들어 주러 감
			
			
			return GSON_PAGE;
		}
		
	}
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
