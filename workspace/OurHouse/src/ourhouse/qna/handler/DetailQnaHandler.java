package ourhouse.qna.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.BaseVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.common.vo.PhotoDetailVO;
import ourhouse.common.vo.PostVO;
import ourhouse.common.vo.ReasonVO;
import ourhouse.common.vo.ReplyVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
import ourhouse.photo.service.IPhotoService;
import ourhouse.photo.service.PhotoServiceImpl;
import ourhouse.post.service.IPostService;
import ourhouse.post.service.PostServiceImpl;
import ourhouse.reply.service.IReplyService;
import ourhouse.reply.service.ReplyServiceImpl;
import ourhouse.report.service.IReportService;
import ourhouse.report.service.ReportServiceImpl;

public class DetailQnaHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/views/qna/detailQna.jsp";
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getParameter("postNo") == null) { // 널처리
			return "/main.do";
		}
		
		// postNo존재하는지 확인하는 널처리 필요
		
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
		req.setAttribute("sessionId", memId);
		
		int postNo = Integer.parseInt(req.getParameter("postNo"));
		PostVO paramVO = new PostVO();
		paramVO.setMemId(memId); // 세션아이디
		paramVO.setPostNo(postNo);
		
		IPhotoService photoService = PhotoServiceImpl.getInstance();
		IPostService postService = PostServiceImpl.getInstance();
		IReplyService replyService = ReplyServiceImpl.getInstance();
		IMemberService memberService = MemberServiceImpl.getInstance();
		IReportService reportService = ReportServiceImpl.getInstance();
		
		// 0. 사진 클릭시 post 조회수 update
		int viewUpdate = postService.updatePostView(postNo);
		
		// 1. 게시글 및 작성자 관련  (HomePhotoVO사용)
		// 글NO, 작성자명, 글제목, 글내용, 카테고리5개, 조회수, 작성일, 좋아요수, 좋아요여부, 작성자프사경로, 작성자프사저장명, 팔로우여부, 댓글수
		PhotoDetailVO qnaDetailVO = postService.selectQnaDetail(paramVO);
		req.setAttribute("qnaDetailVO", qnaDetailVO);
		
		// 2. 파일 관련: 파일ID, 글NO, 사진, 내용, 저장경로, 저장이름 (List<AtchFileVO>) -- 파일패키지
		// 사진 첨부파일 및 글 내용(atch_file) 로드 후 req객체에 셋팅
		List<AtchFileVO> qnaFileList = photoService.selectPhotoFiles(postNo);
		req.setAttribute("qnaFileList", qnaFileList);
		
		// 3. 댓글 관련 (댓글 좋아요 여부 필요) (List<ReplyVO>) reply 패키지
		// 댓글NO, 댓글작성자ID, 댓글작성자프사경로, 댓글작성자프사저장명, 댓글내용, 댓글 작성일
		List<ReplyVO> replyList = replyService.selectReplyList(postNo);
		req.setAttribute("replyList", replyList);

		// 4. 로그인id의 프로필 이미지 (댓글 작성칸)
		String prfImgNm = memberService.getPrfileImg(memId);
		if(prfImgNm == null) {
			prfImgNm = BaseVO.getDEFAULT_PROFILE_NAME();
		}
		req.setAttribute("sessionPrfImg",prfImgNm);
		
		// 5. 신고사유
		List<ReasonVO> reasonList = reportService.selectReasonList();
		req.setAttribute("reasonList", reasonList);
		return VIEW_PAGE;
	}
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
