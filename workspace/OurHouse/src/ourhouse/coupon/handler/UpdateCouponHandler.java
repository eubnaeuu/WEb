package ourhouse.coupon.handler;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.AdminMemberVO;
import ourhouse.common.vo.CouponManageVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.coupon.service.CouponServiceImpl;
import ourhouse.coupon.service.ICouponService;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
/**
 * 쿠폰수정
 * @author 조예슬
 *
 */
public class UpdateCouponHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/views/admin/updatecoupon.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// get인 경우
		if(req.getMethod().equals("GET")) {
		ICouponService couponService = CouponServiceImpl.getInstance();
		
		int couponNo = Integer.parseInt(req.getParameter("couponNo"));
		List<CouponManageVO> cpMgList= new ArrayList<>();
		
		CouponManageVO couponVO = new CouponManageVO();
		couponVO = couponService.getCouponMgVO(couponNo);
		
		
		req.setAttribute("couponVO", couponVO);

		
		return VIEW_PAGE;
		// post 인경우
		}else {
			
			String shopId = req.getParameter("shopId");
			int price = Integer.parseInt(req.getParameter("price"));
			int stock = Integer.parseInt(req.getParameter("stock"));
			int couponNo = Integer.parseInt(req.getParameter("couponNo"));
			
			//2. 서비스 객체 생성하기
			ICouponService couponService = CouponServiceImpl.getInstance();
			CouponManageVO couponVO = new CouponManageVO();
			couponVO.setShopId(shopId);
			couponVO.setPrice(price);
			couponVO.setStock(stock);
			couponVO.setCpMgNo(couponNo);
			

			
			int cnt = couponService.updateCoupon(couponVO);  // 회원등록 
			
			
			System.out.println("결과 가지고 왔나?"+cnt);
			System.out.println(cnt);
			String msg = "";
			
			if(cnt>0) {
				msg = "성공";
			}else {
				msg = "실패";
			}
			
			//***  forward 와 include 의 차이
			//PrintWriter out = resp.getWriter();
			//out.println("안녕하세요.방가방가");
			//out.flush();
			
			//resp.getWriter().println("안녕하세요. 방가방가");
			
			// 보조스트림으로 write 한것은 buffer에 차곡차곡 쌓여서 ㄷ ㅏ차면 flush가 일어난다. 그때 비로소 clinet에 날라간다. 
			//	=> 아직 buffer가 다 안찼을때 forward를 시켜버리면 forward는 기존의 것을 무시해버리기때문에 buffer에 쌓인것을 clear해버린다. 
			
			// 4. 목록 조회화면으로 이동   ( 보통 insert하고 나면 redirect로 이동 왜냐하면 ctrl +r 로 계속 insert될수있어서)
			//req.getRequestDispatcher("/displayMemberAll").forward(req, resp);
			
			// url 먼저 만들기
			String redirectUrl = req.getContextPath() + "/admin/coupon.do?msg="+URLEncoder.encode(msg,"utf-8");
			
			return redirectUrl;	
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		}else {
			return true;
		}
	}

}
