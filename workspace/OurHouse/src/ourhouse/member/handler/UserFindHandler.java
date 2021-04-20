package ourhouse.member.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.common.vo.MypagePhotoVO;
import ourhouse.common.vo.PostVO;
import ourhouse.common.vo.QnaListVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
import ourhouse.photo.dao.PhotoDaoImpl;
import ourhouse.photo.service.IPhotoService;
/**
 * 회원 프로필 클릭 시 상세페이지 이동
 * @author 서대철
 *
 */
public class UserFindHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/views/mypage/userPage.jsp";
	private static final String GSON_PAGE = "/views/MycommonJson.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		String memId = null;
		
		if(session == null) {
			memId = " ";
			return getJspPage(req, resp, memId);
		} else {
			MemberVO loginVO = (MemberVO) session.getAttribute("session");
			
			if(loginVO == null) {
				memId = " ";
				return getJspPage(req, resp, memId);
			} else {
				memId = loginVO.getMemId();
				return getJspPage(req, resp, memId);
			}
		}
	}
	
	private String getJspPage(HttpServletRequest req, HttpServletResponse resp, String memId) {
		if(req.getMethod().equals("GET")) {
			// 요청한 파라미터 값 가져오기
			memId = req.getParameter("memId");
			System.out.println("멤버아이디");
			System.out.println(memId);
			// 서비스 객체 생성
			IMemberService memberService = MemberServiceImpl.getInstance();
			
			// 회원 정보
			MemberVO memVO = memberService.getMember(memId);
			
			// 팔로우/팔로워
			int followingCnt = memberService.getFollow(memId);
			int followerCnt = memberService.getFollower(memId);
			
			// 프로필이미지
			String img = memberService.getPrfileImg(memId);
			
			// 질문
			List<PostVO> QnAList = new ArrayList<>();
			QnAList = memberService.getQnAList(memId);

			// 게시글
			List<PostVO> postList = new ArrayList<>();
			postList = memberService.getPostList(memId);

			// 게시글 사진
			List<MypagePhotoVO> mpList = new ArrayList<>();
			mpList = memberService.getPhotoList(memId);
			System.out.println("다오다녀옴");
			System.out.println(mpList.toString());
			// 질문글 댓글
			int QnANo = 0;
			if(QnAList.size() > 0) {
				QnANo = QnAList.get(0).getPostNo();
			}
			
			// 질문글 사진
			List<AtchFileVO> atchList = new ArrayList<>();
			atchList = memberService.getQatchList(QnANo);
			
			int answer = memberService.getAnswer(QnANo);
			
			req.setAttribute("memberVO", memVO);
			req.setAttribute("followingCnt", followingCnt);
			req.setAttribute("followerCnt", followerCnt);
			req.setAttribute("img", img);
			
			if(QnAList.size() > 0) {
				req.setAttribute("QnAList", QnAList);
			}
			if(postList.size() > 0) {
				req.setAttribute("postList", postList);
			}
			req.setAttribute("QnAReplyCnt", answer);
			
			if(mpList.size() > 0) {
				req.setAttribute("MpPostPhoto", mpList);
			}
			if(atchList.size() > 0) {
				req.setAttribute("QatchFile", atchList);
			}
		
			
			return VIEW_PAGE;
		} else {
			memId = req.getParameter("memId");	
			
			//질문게시글 불러오는 메서드 진행 할 것임
			IMemberService memberService = MemberServiceImpl.getInstance();
			List<QnaListVO> myQList = new ArrayList<>();
			myQList = memberService.memberQnaList(memId);
			
			String str ="";
			for(int i=0 ; i< myQList.size(); i++) {
				if(myQList.get(i).getHouseId()!=null) {
					str += "#"+myQList.get(i).getHouseId();
				}
				if(myQList.get(i).getSpaceId()!=null) {
					str += "#"+myQList.get(i).getSpaceId();
				}
				if(myQList.get(i).getStyleId()!=null) {
					str += "#" +myQList.get(i).getStyleId();
				}
				if(myQList.get(i).getRoomId()!=null) {
					str += "#" + myQList.get(i).getRoomId();
				}
				if(myQList.get(i).getColorId()!=null) {
					str += "#" + myQList.get(i).getColorId();
				}
				myQList.get(i).setHouseId(str);
			}
			
			Gson gson = new Gson();
			String jsonString = gson.toJson(myQList);
			req.setAttribute("jsonString", jsonString);
				
			}
			return GSON_PAGE;
	}
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
