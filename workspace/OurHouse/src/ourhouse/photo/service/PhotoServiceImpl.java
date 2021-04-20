package ourhouse.photo.service;

import java.util.ArrayList;
import java.util.List;

import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.MypagePhotoVO;
import ourhouse.common.vo.PostVO;
import ourhouse.photo.dao.IPhotoDao;
import ourhouse.photo.dao.PhotoDaoImpl;

public class PhotoServiceImpl implements IPhotoService{
	private static IPhotoService photoService;
	
	private IPhotoDao photoDao;
	
	private PhotoServiceImpl() {
		photoDao = PhotoDaoImpl.getInstance();
	}
	
	public static IPhotoService getInstance() {
		if (photoService == null) {
			photoService = new PhotoServiceImpl();
		}
		return photoService;
	}

	@Override
	public int writePhoto(PostVO postVO) {
		
		int cnt = 0;

		try {
			cnt = photoDao.writePhoto(postVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<AtchFileVO> selectPhotoFiles(int postNo) {
		
		List<AtchFileVO> photoFileList = new ArrayList<AtchFileVO>();
		
		try {
			photoFileList = photoDao.selectPhotoFiles(postNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photoFileList;
	}

	@Override
	public List<MypagePhotoVO> selectFourPhotos(int postNo) {
		
		List<MypagePhotoVO> fourPhotoList = new ArrayList<>();
		try {
			fourPhotoList = photoDao.selectFourPhotos(postNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fourPhotoList;
	}

}
