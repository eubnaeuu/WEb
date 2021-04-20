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
 * 쿠폰 조회 핸들러
 * @author 조예슬
 *
 */
public class ManageCouponHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/views/admin/addcoupon.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ICouponService couponService = CouponServiceImpl.getInstance();
		
		List<CouponManageVO> cpMgList= new ArrayList<>();
		
		cpMgList = couponService.getCouponMgList();
		
		req.setAttribute("cpMgList", cpMgList);

		
		return VIEW_PAGE;
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		
		return false;
	}

}
