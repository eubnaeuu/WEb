package kr.or.ddit.common.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.common.dao.ZipDao;
import kr.or.ddit.common.vo.ZipVO;

public class ZipService {
private ZipDao dao;
	public ZipService() {
		if(dao ==null) {
			dao = new ZipDao();
		}
	}
	public List<ZipVO> retrieveSidoList() throws SQLException {
		return dao.retrieveSidoList();
	}
	public List<ZipVO> retrieveGugunList(ZipVO zipVo) throws SQLException {
		return dao.retrieveGugunList(zipVo);
	}
	public List<ZipVO> retrieveDongList(String str) throws SQLException {
		return dao.retrieveDongList(str);
	}
	public List<ZipVO> retrieveZipList(ZipVO zipVo) throws SQLException {
		return dao.retrieveZipList(zipVo);
	}

}
