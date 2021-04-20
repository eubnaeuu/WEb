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
import ourhouse.common.vo.ProfileImgVO;
import ourhouse.common.vo.QnaListVO;
import ourhouse.common.vo.RoomVO;
import ourhouse.common.vo.SearchMemberVO;
import ourhouse.common.vo.SpaceVO;
import ourhouse.common.vo.StyleVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
import ourhouse.post.service.IPostService;
import ourhouse.post.service.PostServiceImpl;

/**
 * 통합검색 구현 
 * @author 조예슬
 */
public class SearchTotalHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/views/home/searchTotal.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			
		
			req.setCharacterEncoding("UTF-8");
	
			String search = req.getParameter("search");
			String method = req.getMethod();
			if(method.equals("GET")) {
				search = new String(search.getBytes("8859_1"),"UTF-8");
				MemberVO mv = (MemberVO) req.getSession().getAttribute("session");
			}
			
			//String memId = req.getParameter("memId");
			String memId = ""; // 임시
			
			// 사진글 가져오기
			HomePhotoVO paramVO = new HomePhotoVO();
			// 서비스 객체 생성
			IPostService postService = PostServiceImpl.getInstance();
			paramVO.setFileCn(search);
			if(memId!=null) {
				paramVO.setMemId(memId);
			}
			// 사진 게시글 전부 불러오기
			List<HomePhotoVO> searchPhotos = postService.searchTotalPhotos(paramVO);
			if(searchPhotos.size()>0) {
				System.out.println(searchPhotos.toString());
				System.out.println(searchPhotos.get(0).getFileCn());
			}
			
			// 질문글 가져오기
			QnaListVO qv = new QnaListVO();
			if(memId!=null) {
				qv.setMemId(memId);
			}
			qv.setPostTitle(search);
			qv.setPostContent(search);
			List<QnaListVO> qnaList = postService.SearchTotalQnaList(qv);
			
			// 유저 가져오기
			SearchMemberVO sv = new SearchMemberVO();
			
			sv.setMemId(search);
			sv.setMemIntro(search);
			sv.setMemNickname(search);
			
			IMemberService memService = MemberServiceImpl.getInstance();
			List<SearchMemberVO> searchTotalUser = memService.searchTotalUser(sv);
	
			req.setAttribute("searchPhotos", searchPhotos);
			req.setAttribute("searchQnaList", qnaList);
			req.setAttribute("searchTotalUser", searchTotalUser);
			req.setAttribute("search", search);
			
			
			return VIEW_PAGE;
	
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
