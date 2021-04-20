package ourhouse.coupon.handler;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class CouponListHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/views/coupon/couponlist.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		
		
		HttpSession s = req.getSession(false);
		MemberVO memVO = new MemberVO();
		
		if(s==null) { // 비로그인 상태일 때
		
			ICouponService couponService = CouponServiceImpl.getInstance();
			
			List<CouponManageVO> cpMgList= new ArrayList<>();
			
			cpMgList = couponService.getCouponMgList();
			memVO.setMemId(" ");
			req.setAttribute("memberVO", memVO);
			req.setAttribute("cpMgList", cpMgList);
			return VIEW_PAGE;	
		}else {
			   memVO = (MemberVO)s.getAttribute("session");
			if(memVO==null){
				ICouponService couponService = CouponServiceImpl.getInstance();
				
				List<CouponManageVO> cpMgList= new ArrayList<>();
				
				cpMgList = couponService.getCouponMgList();
				

				req.setAttribute("memberVO", memVO);
				req.setAttribute("cpMgList", cpMgList);
				return VIEW_PAGE;		
			}else {
				IMemberService memberService = MemberServiceImpl.getInstance();
				MemberVO memVO2 = new MemberVO();
				memVO2 = memberService.getMember(memVO.getMemId());
				System.out.println(memVO2.toString());
				
				ICouponService couponService = CouponServiceImpl.getInstance();
				
				List<CouponManageVO> cpMgList= new ArrayList<>();
				
				cpMgList = couponService.getCouponMgList();
				
				req.setAttribute("memberVO", memVO2);
				req.setAttribute("cpMgList", cpMgList);
				
				return VIEW_PAGE;	
			}
		}
	}

	@Override
	public boolean isRedirect(HttpServletRequest req) {
			return false;
	}

}
