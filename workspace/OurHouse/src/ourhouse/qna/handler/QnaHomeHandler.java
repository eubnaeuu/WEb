package ourhouse.qna.handler;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import ourhouse.code.service.CodeServiceImpl;
import ourhouse.code.service.ICodeService;
import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.CategoryVO;
import ourhouse.common.vo.ColorVO;
import ourhouse.common.vo.HouseVO;
import ourhouse.common.vo.NoticeVO;
import ourhouse.common.vo.QnaListVO;
import ourhouse.common.vo.RoomVO;
import ourhouse.common.vo.SpaceVO;
import ourhouse.common.vo.StyleVO;
import ourhouse.notice.service.INoticeService;
import ourhouse.notice.service.NoticeServiceImpl;
import ourhouse.post.service.IPostService;
import ourhouse.post.service.PostServiceImpl;

/**
 * 질문게시판 조회 
 * @author 조예슬
 */
public class QnaHomeHandler implements CommandHandler{
	protected static Logger logger = Logger.getLogger(QnaHomeHandler.class);
	private static final String VIEW_PAGE = "/views/qna/qnahome.jsp";
	IPostService postService = PostServiceImpl.getInstance();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// get인 경우
		if(req.getMethod().equals("GET")) {
			// 서비스 객체 생성
			ICodeService codeService = CodeServiceImpl.getInstance();
			INoticeService anqNotice = NoticeServiceImpl.getInstance();
			// 페이징처리 (추후 구현 예정)

			// 질문 게시글 전부 불러오기
			List<QnaListVO> qnaList = postService.selectQnaList();
			req.setAttribute("QnAList", qnaList);

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
			
			//공지사항 최신글  제목 불러오기
			NoticeVO nv = new NoticeVO();
			nv = anqNotice.getRecentNotice();
			logger.debug(nv.toString());
			req.setAttribute("RecentNotice", nv);
			
			return VIEW_PAGE;
			// post 인경우
		}else {

			// 카테고리를 선택할때마다 가져옴 
			String[] arr = req.getParameterValues("filterArr[]");

			CategoryVO categoryVO = new CategoryVO();
		
			if(!(arr==null)) {
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
			PrintWriter out = resp.getWriter();
			
			// 카테고리에따라 DB에서 게시글 가져오기 
			List<QnaListVO> qnaList = postService.categoryQnaList(categoryVO);
			

			
			logger.debug("다오에서옴");
			logger.debug(qnaList.toString());
			//logger.debug("ddd  "+jsonString );
			
			// Gson 으로 객체 저장	
			Gson gson = new Gson();
			String jsonString = gson.toJson(qnaList);
			req.setAttribute("jsonString", jsonString);
			// Gson 만들어 주러 감
			return "/views/qna/commonJson.jsp";
		}

	}
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}
	
}
