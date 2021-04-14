package kr.or.ddit.member.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp); // 지워야함 : extends한 HttpServlet 의 doPost를 호출하는 것이기 때문에 현재 클래스의 doPost를 사용할 수 없음
		
		
		// 브라우저로 부터 받은 값을 사용하기 위해 request에서 parameter를 get.
		
		String flag = req.getParameter("flag");
		try {	
		// 
		if(flag.equals("L")) { // 목록조회 
			//flag.equals("L")은 flag가 NULL일경우 error남
			// 따라서 "L".equals(req)로 바꾸면 error안남. L문자가 Req와 같은지를 의미하는 것이기에
			
		
				//  ★ error
				List<MemberVO> list = retriveMemberlist(req);
				// 브라우저로 전달할 결과를 request에 attribute로 세팅
				req.setAttribute("list", list);
			// 결과를 받을 url 세팅
//			RequestDispatcher  disp = req.getRequestDispatcher("/JqueryPro/html/member/memberListResult.jsp"); // <== contextroot 포함하면 안됨!
			RequestDispatcher  disp = req.getRequestDispatcher("/html/member/memberListResult.jsp");
			
			disp.forward(req, resp);
			//회원 목록 조회
//			List<MemberVO> list = retriveMemberlist(req);

			} else if (flag.equals("C")) { // 등록
				createMember(req);
			} else if (flag.equals("R")) { // 단건 조회
			} else if (flag.equals("U")) { // 수정
			} else if (flag.equals("D")) { // 삭제
			} else if (flag.equals("CHKID")) { // ID중복검사
				// ☆
				MemberVO memberVo = checkMemberId(req);
				int resultCnt = 0;
				if (memberVo != null) {
					resultCnt = 1;
					req.setAttribute("resultCnt", resultCnt);
					RequestDispatcher disp = req.getRequestDispatcher("/html/member/idCheckResult.jsp");
					disp.forward(req, resp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private MemberVO checkMemberId(HttpServletRequest req) throws SQLException {
		String memId = req.getParameter("memId");
		MemberService service = new MemberService();
		
		return service.retrieveMember(memId);
		
	}

	private void createMember(HttpServletRequest req) throws SQLException {
		
		MemberVO memberVo = new MemberVO();
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		MemberService service = new MemberService();
		service.createMember(memberVo);
		
		// 그 외 정보들 VO에 세팅...
		
	}

	private List<MemberVO> retriveMemberlist(HttpServletRequest req) throws ServletException, IOException, SQLException {
		// ☆ null값인데 굳이 하는 이유는?
		
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		String memName = req.getParameter("memName");
		String memBir = req.getParameter("memBir");
		String memZip = req.getParameter("memZip");
		String memAdd1 = req.getParameter("memAdd1");
		String memAdd2 = req.getParameter("memAdd2");
		String memHp = req.getParameter("memHp");
		String memMail = req.getParameter("memMail");
		String memrecvEmailYn = req.getParameter("recvEmailYn");
		String memJob = req.getParameter("memJob");
		String memLike = req.getParameter("memLike");
		String memMemorialType = req.getParameter("memMemorialType");
		String memMemorialDate = req.getParameter("memMemorialDate");
//		String memMileage = req.getParameter("memMileage");
		String memComment = req.getParameter("memComment");
//		
//			System.out.println(memName);
		
		
		// form serialize를 사용해서 파라미터를 전달한 경우, request에 요소의 name으로 parameter가 매핑됨.
		// 예) <input type="text" name="userId"> ==> req.getParameter("userId")
		
		MemberVO memberVo = new MemberVO();
//		for(String tmpstr : str) {     //☆ 좀더 간단하게 넣을 방법 이없을까했는데..
//			String tmpstr2 = "set"+tmpstr;
//			memberVo.setMemId(tmpstr);
//		}
////		
		memberVo.setMemId(memId);
		memberVo.setMemPass(memPass);
		memberVo.setMemName(memName);
		memberVo.setMemBir(memBir);
		memberVo.setMemZip(memZip);
		memberVo.setMemAdd1(memAdd1);
		memberVo.setMemAdd2(memAdd2);
		memberVo.setMemHp(memHp);
		memberVo.setMemMail(memMail);
		memberVo.setRecvEmailYn(memrecvEmailYn);
		memberVo.setMemJob(memJob);
		memberVo.setMemLike(memLike);
		memberVo.setMemMemorialDate(memMemorialType);
		memberVo.setMemMemorialType(memMemorialDate);
//		memberVo.setMemMileage(Integer.valueOf(memMileage));
		memberVo.setMemComment(memComment);
		
		MemberService service = new MemberService();
		
		List<MemberVO> list = service.retrieveMemberList(memberVo);
		
		return list;
		}

		
	
}
