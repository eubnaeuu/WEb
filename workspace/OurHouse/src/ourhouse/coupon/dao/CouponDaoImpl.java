package ourhouse.coupon.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourhouse.common.vo.AdminMemberVO;
import ourhouse.common.vo.CouponBoxVO;
import ourhouse.common.vo.CouponManageVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.util.SqlMapClientFactory;

public class CouponDaoImpl implements ICouponDao{
	
	private static ICouponDao couponDao;
	
	private SqlMapClient smc;
	
	private CouponDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ICouponDao getInstance() {
		if (couponDao == null) {
			couponDao = new CouponDaoImpl();
		}
		return couponDao;
		
	}

	@Override
	public List<CouponManageVO> getCouponMgList() throws Exception {
		List<CouponManageVO> getCouponMgList = new ArrayList<CouponManageVO>();
		
		getCouponMgList = (List<CouponManageVO>) smc.queryForList("couponmanage.getCouponManageList");
		
		return getCouponMgList;
	}

	@Override
	public CouponManageVO getCouponMgVO(int couponNo) throws Exception {

		CouponManageVO couponVO = (CouponManageVO) smc.queryForObject("couponmanage.getCouponVO",couponNo);
		
		return couponVO;
	}

	@Override
	public int updateCoupon(CouponManageVO couponVO) throws Exception {
		int cnt=0;
		cnt = (int) smc.update("couponmanage.updateCoupon", couponVO);	
		return cnt;
	}

	@Override
	public int deleteCoupon(CouponManageVO mv) throws Exception {
		int cnt=0;
		cnt = (int) smc.delete("couponmanage.deleteCoupon", mv);	
		return cnt;
	}

	@Override
	public int insertCoupon(CouponManageVO couponVO) throws Exception {
		int cnt=0;
		Object obj  =  smc.insert("couponmanage.insertCoupon", couponVO);	
		if(obj==null) {
			cnt=1;
		}
		return cnt;
	}

	@Override
	public int insertMemCouponBox(CouponBoxVO cpBoxVO) throws Exception {
		int cnt=0;
		Object obj  =  smc.insert("coupon.insertMemCouponBox", cpBoxVO);	
		if(obj==null) {
			cnt=1;
		}
		return cnt;
	}

	@Override
	public int updateCouponStock(CouponManageVO cpMgVO) throws Exception {
		int cnt=0;
		
		  cnt =  smc.update("couponmanage.updateCouponStock", cpMgVO);	
		
		return cnt;
	}
	
}