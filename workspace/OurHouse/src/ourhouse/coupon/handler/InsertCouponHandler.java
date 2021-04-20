package ourhouse.coupon.handler;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.AdminMemberVO;
import ourhouse.common.vo.CouponManageVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.coupon.service.CouponServiceImpl;
import ourhouse.coupon.service.ICouponService;
import ourhouse.member.service.IMemberService;
import ourhouse.member.service.MemberServiceImpl;
/**
 * 쿠폰생성 
 * @author 조예슬
 *
 */
public class InsertCouponHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/views/admin/writecoupon.jsp";
	private static final String GSON_PAGE = "/views/MycommonJson.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}else {
		// get인 경우
		String shopId = req.getParameter("shopId");
		int price = Integer.parseInt(req.getParameter("price"));
		int stock = Integer.parseInt(req.getParameter("stock"));
		
		//2. 서비스 객체 생성하기
		ICouponService couponService = CouponServiceImpl.getInstance();
		CouponManageVO couponVO = new CouponManageVO();
		couponVO.setShopId(shopId);
		couponVO.setPrice(price);
		couponVO.setStock(stock);
		couponVO.setCpDelete("N");

		
		int cnt = couponService.insertCoupon(couponVO);  // 회원등록 
		
		
		System.out.println("결과 가지고 왔나?"+cnt);
		System.out.println(cnt);
		String msg = "";
		
		if(cnt>0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		List<String> json = new ArrayList<>();
		json.add(msg);
		// Gson 으로 객체 저장	
		Gson gson = new Gson();
		String jsonString = gson.toJson(json);
		req.setAttribute("jsonString", jsonString);
		// Gson 만들어 주러 감
		
		return GSON_PAGE;	
	
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

}
