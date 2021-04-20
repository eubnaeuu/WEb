package ourhouse.coupon.handler;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ourhouse.common.handler.CommandHandler;
import ourhouse.common.vo.AdminMemberVO;
import ourhouse.common.vo.CouponBoxVO;
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
public class TakeCouponHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/views/MycommonJson.jsp";
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		//1. 데이터 가져오기
		String memId = req.getParameter("memId");
		int memPoint = Integer.parseInt(req.getParameter("memPoint"));
		int couponPrice = Integer.parseInt(req.getParameter("couponPrice"));
		int cpMgNo = Integer.parseInt(req.getParameter("cpMgNo"));
		int stock = Integer.parseInt(req.getParameter("stock"));
		
		System.out.println("핸들러 왔음");
		System.out.println(memId);
		System.out.println(memPoint);
		System.out.println(couponPrice);
		System.out.println(cpMgNo);
		System.out.println(stock);
		//2. 서비스 객체 생성하기
		ICouponService couponService = CouponServiceImpl.getInstance();
		IMemberService memberService = MemberServiceImpl.getInstance();
		//3. 업데이트할 VO 생성 
		CouponManageVO cpMgVO = new CouponManageVO();
		CouponBoxVO cpBoxVO = new CouponBoxVO();
		MemberVO mv = new MemberVO();
		// (1)COUPON_MANAGE 테이블 재고 차감 UPDATE
		cpMgVO.setCpMgNo(cpMgNo);
		cpMgVO.setStock(stock-1);
		// (2)COUPON_BOX 테이블 회원 쿠폰 INSERT
		cpBoxVO.setCpMgNo(cpMgNo);
		cpBoxVO.setMemId(memId);
		// 쿠폰 코드 난수 만들기
		UUID one = UUID.randomUUID();
		String strTemp = one.toString();
		String cpCode2 = strTemp.substring(0,18);
		String cpCode3 = cpCode2.replaceAll("-", "");
		String num1 = cpCode3.substring(0,4);
		String num2 = cpCode3.substring(4,8);
		String num3 = cpCode3.substring(8,12);
		String num4 = cpCode3.substring(12,16);
		
		String cpCode = num1+"-"+num2+"-"+num3+"-"+num4;
		cpBoxVO.setCouponCode(cpCode);		

		// (3)MEMBER 테이블 POINT 차감 UPDATE
		mv.setMemId(memId);
		mv.setMemPoint(memPoint-couponPrice);


		int cnt1 = memberService.updateMemPoint(mv);
		int cnt2 = couponService.insertMemCouponBox(cpBoxVO);  // 회원등록 
		int cnt3 = couponService.updateCouponStock(cpMgVO);

		String msg = "실패";
		if(cnt1>0 && cnt2>0 && cnt3 >0) {
			msg ="성공";
		}
		Map<Object, Object> jsonMap = new HashMap<>();
		jsonMap.put("msg", msg);
		
		// Gson 으로 객체 저장	
		Gson gson = new Gson();
		String jsonString = gson.toJson(jsonMap);
		System.out.println(jsonString);
		req.setAttribute("jsonString", jsonString);
		// Gson 만들어 주러 감

		return "/views/MycommonJson.jsp";	
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;

	}

}
