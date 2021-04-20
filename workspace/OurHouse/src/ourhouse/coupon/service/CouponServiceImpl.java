package ourhouse.coupon.service;

import ourhouse.coupon.dao.ICouponDao;

import java.util.ArrayList;
import java.util.List;

import ourhouse.common.vo.CouponBoxVO;
import ourhouse.common.vo.CouponManageVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.coupon.dao.CouponDaoImpl;

public class CouponServiceImpl implements ICouponService{
	private static ICouponService couponService;
	
	private ICouponDao couponDao;
	
	private CouponServiceImpl() {
		couponDao = CouponDaoImpl.getInstance();
	}
	
	public static ICouponService getInstance() {
		if (couponService == null) {
			couponService = new CouponServiceImpl();
		}
		return couponService;
	}

	@Override
	public List<CouponManageVO> getCouponMgList() {
		List<CouponManageVO> cpMgList = new ArrayList<CouponManageVO>();
		try {
			cpMgList = couponDao.getCouponMgList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cpMgList;
	}

	@Override
	public CouponManageVO getCouponMgVO(int couponNo) throws Exception {
		CouponManageVO couponVO = new CouponManageVO();
		
		try {
			couponVO =  couponDao.getCouponMgVO(couponNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return couponVO;
	}

	@Override
	public int updateCoupon(CouponManageVO couponVO) throws Exception {
		int cnt=0;
		try {
			cnt =  couponDao.updateCoupon(couponVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteCoupon(CouponManageVO mv) throws Exception {
		int cnt=0;
		try {
			cnt =  couponDao.deleteCoupon(mv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertCoupon(CouponManageVO couponVO) throws Exception {
		int cnt=0;
		try {
			cnt =  couponDao.insertCoupon(couponVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertMemCouponBox(CouponBoxVO cpBoxVO) {
		int cnt=0;
		try {
			cnt =  couponDao.insertMemCouponBox(cpBoxVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateCouponStock(CouponManageVO cpMgVO) {
		int cnt=0;
		try {
			cnt =  couponDao.updateCouponStock(cpMgVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	
}
