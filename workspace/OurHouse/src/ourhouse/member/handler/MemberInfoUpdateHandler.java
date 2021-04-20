package ourhouse.member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.MemberVO;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
/**
 * 회원정보수정페이지 
 * @author 김지현
 */
public class MemberInfoUpdateHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/mypage/memberInfoUpdate.jsp";
	private static final String LOGIN_PAGE = "/member/signin.do"; //다른주소로
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession(false);
		
		if(session == null) {
			return LOGIN_PAGE;
		}else {
			MemberVO memVO = (MemberVO) session.getAttribute("session");
			if(memVO == null) {
				return LOGIN_PAGE;
			}else {
				if(req.getMethod().equals("GET")) { //get일때
					
					//세션에서 아이디 가져오기
					String memId = memVO.getMemId();
					System.out.println(">>>세션에서 가져온 아이디 : " + memId);
					IMemberService memberService = MemberServiceImpl.getInstance();
					
					//세션에서 가져온 아이디 가지고 로그인한 회원 현재정보
					MemberVO  getVO = memberService.selectMember(memId);
					req.setAttribute("getVO", getVO);
					System.out.println(">>>로그인 회원정보 : "+ getVO);
					
					//프로필이미지 받아오기
					String img = memberService.getPrfileImg(memId);
					System.out.println(">>>프로필 사진 가져오기"+img);
					req.setAttribute("img", img);
					
					return VIEW_PAGE; //화면으로만 , 화면이나오기전에 핸들러를 들리고 화면만 보여진다.
					
				}else{//post일때
					
					// <일반 정보 업데이트>
					//1.요청 파라미터 가져오기
					String memEmail = req.getParameter("memEmail");
					String memNickname = req.getParameter("memNickname");
					String memIntro = req.getParameter("memIntro");
					
					MemberVO mv = new MemberVO();
					mv.setMemEmail(memEmail);
					mv.setMemNickname(memNickname);
					mv.setMemIntro(memIntro);
					
					//2.서비스 객체 생성하기
					IMemberService memberService = MemberServiceImpl.getInstance();
					int cnt = memberService.memberInfoUpdate(mv);
					
					if(cnt>0) {
						//업데이트 성공
						req.setAttribute("cnt", cnt);
					}
					

					// <프로필 사진 업데이트>
//					//서비스객체생성
//					IPhotoService photoService = PhotoServiceImpl.getInstance();
//					IAtchFileService fileService = AtchFileServiceImpl.getInstance();
//					
//					int uploadCnt = 0 ; // 파일 업로드 성공여부 판별
//					
//					if (Integer.parseInt(req.getParameter("fileCount")) == 1) { // 파일 첨부됨
//						
//						FileItem item = ((FileUploadRequestWrapper)req).getFileItem("atchFile");
//						
//						if(item != null && !item.getName().equals("")) { // 파일이 있으면
//							
//							// 1. 파일첨부시 필요한 컬럼 데이터 준비하기
//							ProfileImgVO paramVO = new ProfileImgVO();
//							paramVO.setMemId("chichi");
//							
//							// 2. 파일 업로드(후 db저장) 기능 호출
//							// 파일저장순서: 로컬에 저장된 후 db에 insert됨
//							
//							uploadCnt = fileService.saveAtchFile(paramVO, item); // 파일정보, 파일
//						}
//						
//						
//					}
				}
				// 파일 성공 마치고, 일반정보 업데이트 성공여부와 파일 업데이트 성공여부 둘다 확인해야함
				String url = "/views/mypage/memDeleteChk.jsp";
				return url;
			}
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
