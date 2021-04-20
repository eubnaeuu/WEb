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
import ourhouse.common.vo.MypagePhotoVO;
import ourhouse.common.vo.PostVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

/**
 * 마이페이지 구현 
 * @author 조예슬
 *
 */
public class ShowMemPhotoHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/mypage/mypagePhoto.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	req.setCharacterEncoding("UTF-8");
		
		// 세션 사용할 시 
		//MemberVO memVO = (MemberVO)req.getSession().getAttribute("session");
		
		
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		//String memId= memVO.getMemId();
		//System.out.println("아이디"+memId);
		String memId = req.getParameter("memId");
		//String memId="chichi";
		System.out.println("멤버아이디");
		System.out.println(memId);
		// 게시글 사진
		List<MypagePhotoVO> mpList = new ArrayList<>();
		
		mpList = memberService.getPhotoList(memId);
	
		System.out.println(mpList);
//		if(mpList.size()>0) {
//			req.setAttribute("MpPostPhoto", mpList);
//		}
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(mpList);

		req.setAttribute("jsonString", jsonString);
		
		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {

			return false;
	
	}

}
