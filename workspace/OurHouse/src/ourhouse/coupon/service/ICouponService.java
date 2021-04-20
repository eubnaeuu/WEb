package ourhouse.coupon.service;

import java.util.List;

import ourhouse.common.vo.CouponBoxVO;
import ourhouse.common.vo.CouponManageVO;

public interface ICouponService {
	
	/**
	 * 쿠폰관리항목을 전체 조회하는 메서드
	 * @author 조예슬
	 * @return
	 */
	List<CouponManageVO> getCouponMgList() throws Exception;
	/**
	 * 쿠폰번호에따른 쿠폰VO를 가져오는 메서드
	 * @author 조예슬
	 * @param couponNo
	 * @return
	 */
	CouponManageVO getCouponMgVO(int couponNo)throws Exception;
	/**
	 * 쿠폰 수정 메서드
	 * @author 조예슬
	 * @param couponVO
	 * @return
	 */
	int updateCoupon(CouponManageVO couponVO)throws Exception;
	
	/**
	 * 쿠폰 삭제 메서드
	 * @author 조예슬
	 * @param couponNo
	 * @return
	 */
	int deleteCoupon(CouponManageVO mv) throws Exception;
	/**
	 * 쿠폰 추가 메서드
	 * @author 조예슬
	 * @param couponVO
	 * @return
	 */
	int insertCoupon(CouponManageVO couponVO) throws Exception;
	/**
	 * 회원이구매한 쿠폰 BOX 추가 메서드
	 * @author 조예슬
	 * @param cpBoxVO
	 * @return
	 */
	int insertMemCouponBox(CouponBoxVO cpBoxVO);
	/**
	 * 회원이쿠폰 구메함에 따라 쿠폰 재고 수량 차감 메서드 
	 * @author 조예슬
	 * @param cpMgVO
	 * @return
	 */
	int updateCouponStock(CouponManageVO cpMgVO);

}
