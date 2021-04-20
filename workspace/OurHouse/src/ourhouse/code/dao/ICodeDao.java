package ourhouse.code.dao;

import java.util.List;

import ourhouse.common.vo.ColorVO;
import ourhouse.common.vo.HouseVO;
import ourhouse.common.vo.RoomVO;
import ourhouse.common.vo.SpaceVO;
import ourhouse.common.vo.StyleVO;

public interface ICodeDao {

	/**
	 * 주거형태 리스트 불러오는 메서드
	 * @return List<HouseVO> 주거형태 리스트
	 * @author 이경륜
	 */
	public List<HouseVO> selectHouseList() throws Exception;

	/**
	 * 공간 리스트 불러오는 메서드
	 * @return List<RoomVO> 공간 리스트
	 * @author 이경륜
	 */
	public List<RoomVO> selectRoomList() throws Exception;

	/**
	 * 평수 리스트 불러오는 메서드
	 * @return List<SpaceVO> 평수 리스트
	 * @author 이경륜
	 */
	public List<SpaceVO> selectSpaceList() throws Exception;

	/**
	 * 스타일 리스트 불러오는 메서드
	 * @return List<StyleVO> 스타일 리스트
	 * @author 이경륜
	 */
	public List<StyleVO> selectStyleList() throws Exception;
	
	/**
	 * 컬러 리스트 불러오는 메서드
	 * @return List<ColorVO> 컬러 리스트
	 * @author 이경륜
	 */
	public List<ColorVO> selectColorList() throws Exception;
}
