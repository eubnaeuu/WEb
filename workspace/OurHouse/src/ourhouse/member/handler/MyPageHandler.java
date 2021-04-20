package ourhouse.member.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.FollowVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.common.vo.MypagePhotoVO;
import ourhouse.common.vo.PostVO;
import ourhouse.common.vo.QnaListVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;

/**
 * 마이페이지 구현 
 * @author 조예슬
 *
 */
public class MyPageHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/mypage/mypage.jsp";
	private static final String LOGIN_PAGE = "/member/signin.do";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	req.setCharacterEncoding("UTF-8");
	
		//int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
		
		//String msg = req.getParameter("msg") == null? "" : req.getParameter("msg");
		
		//System.out.println("msg : "+msg);
		// 세션 받아오기
		HttpSession s = req.getSession(false);
		
		if(s==null) {
			return LOGIN_PAGE;
			//return HOME_PAGE;
		}else {
			MemberVO memVO = (MemberVO)s.getAttribute("session");
				if(memVO==null){
					return LOGIN_PAGE;
					//	return HOME_PAGE;	
				}else {
				
					// get인 경우
					if(req.getMethod().equals("GET")) {
					String memId= memVO.getMemId();
					
					//서비스 객체 생성
					IMemberService memberService = MemberServiceImpl.getInstance();
					
					//System.out.println("아이디"+memId);
					//String memId="sdcsdc";
					
					MemberVO mv = new MemberVO();
					mv.setMemId(memId);
					
					// 회원정보
					mv = memberService.getMember(memId);
					// 팔로우/팔로워
					int followingCnt =  memberService.getFollow(memId);
					int followerCnt = memberService.getFollower(memId);
					// 좋아요
					int likes = memberService.getLikes(memId);
					// 쿠폰
					int coupon = memberService.getCoupon(memId);
					// 프로필이미지
					String img = memberService.getPrfileImg(memId);
					// 질문
					List<PostVO> QnAList = new ArrayList<>();
					QnAList = memberService.getQnAList(memId);
					
					// 게시글
					List<PostVO> PostList = new ArrayList<>();
					PostList = memberService.getPostList(memId);
					
					
					// 게시글 사진
					List<MypagePhotoVO> mpList = new ArrayList<>();
					
					mpList = memberService.getPhotoList(memId);
					
					// 질문글 댓글
					int QnANo=0;
					if(QnAList.size()>0) {
						QnANo = QnAList.get(0).getPostNo();
					}
					
					//질문글 사진
					List<AtchFileVO> atchList2 = new ArrayList<>();
					atchList2 = memberService.getQatchList(QnANo);
					
					
					int answer = memberService.getAnswer(QnANo);
					
					
					req.setAttribute("member", mv);
					req.setAttribute("following", followingCnt);
					req.setAttribute("follower", followerCnt);
					req.setAttribute("likes", likes);
					req.setAttribute("coupon", coupon);
					req.setAttribute("img", img);
					
					if(QnAList.size()>0) {
						req.setAttribute("QnAList", QnAList);
					}
					if(PostList.size()>0) {
						req.setAttribute("PostList", PostList);
					}
					req.setAttribute("QnaReplyCnt", answer);
					
					if(mpList.size()>0) {
						req.setAttribute("MpPostPhoto", mpList);
					}
					if(atchList2.size()>0) {
						req.setAttribute("QatchFile", atchList2);
					}
					
					return VIEW_PAGE;
					// post 인경우
					}else {
					// 질문게시글 상세보기 페이지 	
					String memId = req.getParameter("memId");	
					
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
					return  "/views/mypage/MycommonJson.jsp";	
					
					
				} // 내부 else 절 
		}   // 전체 if 절의 끝 

		
		
		
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {

			return false;
	
	}

}
