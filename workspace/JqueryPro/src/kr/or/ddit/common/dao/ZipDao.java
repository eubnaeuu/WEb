package kr.or.ddit.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.base.dao.BaseDao;
import kr.or.ddit.common.vo.ZipVO;

public class ZipDao extends BaseDao {

	
	private SqlMapClient smc;
	
	public ZipDao() {
		smc = super.getSqlMapClient();
	}
	
	// TB_CODE 
	public List<ZipVO> retrieveSidoList() throws SQLException {
		return smc.queryForList("zip.retrieveSidoList");
	}
	public List<ZipVO> retrieveGugunList(ZipVO zipVo) throws SQLException {
		System.out.println("★ : "+zipVo.getSido());
		return smc.queryForList("zip.retrieveGugunList",zipVo);
	}
	public List<ZipVO> retrieveDongList(String str) throws SQLException {
		return smc.queryForList("zip.retrieveDongList",str);
	}
	public List<ZipVO> retrieveZipList(ZipVO zipVo) throws SQLException {
		return smc.queryForList("zip.retrieveZipList",zipVo);
	}

}
