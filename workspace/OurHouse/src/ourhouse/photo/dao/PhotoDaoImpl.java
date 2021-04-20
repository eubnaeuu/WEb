package ourhouse.photo.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.MypagePhotoVO;
import ourhouse.common.vo.PostVO;
import ourhouse.util.SqlMapClientFactory;

public class PhotoDaoImpl implements IPhotoDao{
	
	private static IPhotoDao photoDao;
	
	private SqlMapClient smc;
	
	private PhotoDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IPhotoDao getInstance() {
		if (photoDao == null) {
			photoDao = new PhotoDaoImpl();
		}
		return photoDao;
		
	}

	@Override
	public int writePhoto(PostVO postVO) throws Exception {
		
		int cnt = (int) smc.insert("post.insertPhotoPost", postVO);

		return cnt;
	}

	@Override
	public List<AtchFileVO> selectPhotoFiles(int postNo) throws Exception {
		
		List<AtchFileVO> photoFileList = smc.queryForList("atchFile.selectPhotoFiles", postNo);
		
		return photoFileList;
	}

	@Override
	public List<MypagePhotoVO> selectFourPhotos(int postNo) throws Exception {
		
		List<MypagePhotoVO> fourPhotoList = smc.queryForList("atchFile.selectFourPhotos", postNo);
		
		return fourPhotoList;
	}
	
}