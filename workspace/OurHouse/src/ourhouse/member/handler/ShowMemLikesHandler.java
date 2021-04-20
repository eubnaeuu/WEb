package ourhouse.member.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.FollowVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.common.vo.MyLikesVO;
import ourhouse.common.vo.MypagePhotoVO;
import ourhouse.common.vo.PostVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

/**
 * 마이페이지 구현 
 * @author 조예슬
 *
 */
public class ShowMemLikesHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/mypage/myLikesJson.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	req.setCharacterEncoding("UTF-8");
		
		// 세션 사용할 시 
		//MemberVO memVO = (MemberVO)req.getSession().getAttribute("session");
		
		System.out.println("서블릿은 왔나");
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		//String memId= memVO.getMemId();
		//System.out.println("아이디"+memId);
		String memId = req.getParameter("memId");
		//String memId="chichi";
		
		// 게시글 사진
		List<MyLikesVO> mylikeList = new ArrayList<>();
		
		mylikeList = memberService.myLikesList(memId);
	
		Gson gson = new Gson();
		String jsonString = gson.toJson(mylikeList);

		req.setAttribute("jsonString", jsonString);
		
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {

			return false;
	
	}

}
