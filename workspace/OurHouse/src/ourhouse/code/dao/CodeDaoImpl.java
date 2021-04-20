package ourhouse.code.dao;


import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourhouse.common.vo.ColorVO;
import ourhouse.common.vo.HouseVO;
import ourhouse.common.vo.RoomVO;
import ourhouse.common.vo.SpaceVO;
import ourhouse.common.vo.StyleVO;
import ourhouse.util.SqlMapClientFactory;

public class CodeDaoImpl implements ICodeDao{
	
	private static ICodeDao codeDao;
	
	private SqlMapClient smc;
	
	private CodeDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static ICodeDao getInstance() {
		if (codeDao == null) {
			codeDao = new CodeDaoImpl();
		}
		return codeDao;
	}

	@Override
	public List<HouseVO> selectHouseList() throws Exception {
		List<HouseVO> houseList = smc.queryForList("code.selectHouseList");
		return houseList;
	}

	@Override
	public List<RoomVO> selectRoomList() throws Exception {
		List<RoomVO> roomList = smc.queryForList("code.selectRoomList");
		return roomList;
	}

	@Override
	public List<SpaceVO> selectSpaceList() throws Exception {
		List<SpaceVO> spaceList = smc.queryForList("code.selectSpaceList");
		return spaceList;
	}

	@Override
	public List<StyleVO> selectStyleList() throws Exception {
		List<StyleVO> styleList = smc.queryForList("code.selectStyleList");
		return styleList;
	}

	@Override
	public List<ColorVO> selectColorList() throws Exception {
		List<ColorVO> colorList = smc.queryForList("code.selectColorList");
		return colorList;
	}
	
}