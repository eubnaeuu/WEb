package ourhouse.coupon.handler;

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
 * 관리자 쿠폰 삭제 
 * @author 조예슬
 *
 */
public class DeleteCouponHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String No = req.getParameter("couponNo");
		
		CouponManageVO mv = new CouponManageVO();
		int couponNo = Integer.parseInt(No);
		mv.setCpMgNo(couponNo);
		mv.setCpDelete("Y");
		//2. 서비스 객체 생성하기
		ICouponService couponService = CouponServiceImpl.getInstance();
		
		//3. 회원정보 삭제
		int cnt = couponService.deleteCoupon(mv);  // 회원정보 삭제
		System.out.println("결과"+cnt);

		//4. 목록 조회화면으로 이동
		String Url = "/admin/coupon.do?msg=성공";
		return Url;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		
		return false;
	}

}
