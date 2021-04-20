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
 * 해시태그로 게시글 검색
 * @author 조예슬
 */
public class SearchPostHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/views/home/searchPhoto.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			req.setCharacterEncoding("UTF-8");
	
			String hashtag = req.getParameter("hashtag");
			String method = req.getMethod();
			if(method.equals("GET")) {
				hashtag = new String(hashtag.getBytes("8859_1"),"UTF-8");
			}
			//String memId = req.getParameter("memId");
//			String memId = "chuchu"; // 임시
			//req.setAttribute("memId", memId); // 메인페이지 팔로우, 좋아요 버튼 작동 위해서 보냄

			HomePhotoVO paramVO = new HomePhotoVO();
			//paramVO.setMemId(memId);

			// 서비스 객체 생성
			IPostService postService = PostServiceImpl.getInstance();

			// 페이징처리 (추후 구현 예정)
			
			PostVO pv = new PostVO();
			pv.setHashtag(hashtag);
//			if(memId!=null) {
//				pv.setMemId(memId);
//			}
			// 사진 게시글 전부 불러오기
			List<HomePhotoVO> searchPhotos = postService.searchPhotos(pv);
			if(searchPhotos.size()>0) {
				System.out.println(searchPhotos.toString());
				System.out.println(searchPhotos.get(0).getFileCn());
			}
			req.setAttribute("hashtag", hashtag);
			req.setAttribute("PhotoList", searchPhotos);

			
			
			return VIEW_PAGE;
	
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
