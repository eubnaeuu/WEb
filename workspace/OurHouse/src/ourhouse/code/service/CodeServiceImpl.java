package ourhouse.code.service;


import ourhouse.code.dao.ICodeDao;
import ourhouse.common.vo.ColorVO;
import ourhouse.common.vo.HouseVO;
import ourhouse.common.vo.RoomVO;
import ourhouse.common.vo.SpaceVO;
import ourhouse.common.vo.StyleVO;

import java.util.ArrayList;
import java.util.List;

import ourhouse.code.dao.CodeDaoImpl;

public class CodeServiceImpl implements ICodeService{
	private static ICodeService codeService;
	
	private ICodeDao codeDao;
	
	private CodeServiceImpl() {
		codeDao = CodeDaoImpl.getInstance();
	}
	
	public static ICodeService getInstance() {
		if (codeService == null) {
			codeService = new CodeServiceImpl();
		}
		return codeService;
	}

	@Override
	public List<HouseVO> selectHouseList() {
		List<HouseVO> houseList = new ArrayList<>();
		
		try {
			houseList = codeDao.selectHouseList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return houseList;
	}

	@Override
	public List<RoomVO> selectRoomList() {
		List<RoomVO> roomList = new ArrayList<>();
		
		try {
			roomList = codeDao.selectRoomList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return roomList;
	}

	@Override
	public List<SpaceVO> selectSpaceList() {
		List<SpaceVO> spaceList = new ArrayList<>();
		
		try {
			spaceList = codeDao.selectSpaceList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return spaceList;
	}

	@Override
	public List<StyleVO> selectStyleList() {
		List<StyleVO> styleList = new ArrayList<>();
		
		try {
			styleList = codeDao.selectStyleList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return styleList;
	}
	
	@Override
	public List<ColorVO> selectColorList() {
		List<ColorVO> colorList = new ArrayList<>();
		
		try {
			colorList = codeDao.selectColorList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return colorList;
	}

	
}
