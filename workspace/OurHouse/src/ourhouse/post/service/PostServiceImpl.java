package ourhouse.post.service;

import java.util.ArrayList;
import java.util.List;

import ourhouse.common.vo.CategoryVO;
import ourhouse.common.vo.HomePhotoVO;
import ourhouse.common.vo.PhotoDetailVO;
import ourhouse.common.vo.PostVO;
import ourhouse.common.vo.QnaListVO;
import ourhouse.post.dao.IPostDao;
import ourhouse.post.dao.PostDaoImpl;

public class PostServiceImpl implements IPostService{
	private static IPostService postService;
	
	private IPostDao postDao;
	
	private PostServiceImpl() {
		postDao = PostDaoImpl.getInstance();
	}
	
	public static IPostService getInstance() {
		if (postService == null) {
			postService = new PostServiceImpl();
		}
		return postService;
	}

	@Override
	public List<HomePhotoVO> selectTopPhotos() {
		
		List<HomePhotoVO> topPhotoList = new ArrayList<>();
		
		try {
			topPhotoList = postDao.selectTopPhotos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return topPhotoList;
	}
	@Override
	public List<HomePhotoVO> selectHomePhotos(HomePhotoVO paramVO) {
		
		List<HomePhotoVO> homePhotoList = new ArrayList<>();
		
		try {
			homePhotoList = postDao.selectHomePhotos(paramVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return homePhotoList;
	}

	@Override
	public List<QnaListVO> selectQnaList() {
		List<QnaListVO> qnaList = new ArrayList<>();
		
		try {
			qnaList = postDao.selectQnaList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return qnaList;
	}


	@Override
	public List<QnaListVO> categoryQnaList(CategoryVO categoryVO) {
		List<QnaListVO> qnaList = new ArrayList<>();
		
		try {
			qnaList = postDao.categoryQnaList(categoryVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return qnaList;
	}

	@Override
	public int updatePostView(int postNo) {
	
		int viewUpdate = 0;
		
		try {
			viewUpdate = postDao.updatePostView(postNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return viewUpdate;
	}

	@Override
	public PhotoDetailVO selectPhotoDetail(PostVO paramVO) {
		PhotoDetailVO photoDetailVO = new PhotoDetailVO();
		
		try {
			photoDetailVO = postDao.selectPhotoDetail(paramVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return photoDetailVO;
	}

	@Override
	public List<HomePhotoVO> selectCategoryHomePhotos(CategoryVO categoryVO) {
		List<HomePhotoVO> homeCatPhotoList = new ArrayList<>();
		
		try {
			homeCatPhotoList = postDao.selectCategoryHomePhotos(categoryVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return homeCatPhotoList;
	}

	@Override
	public int deletePost(int postNo) {
		
		int cnt = 0;
		
		try {
			cnt = postDao.deletePost(postNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<HomePhotoVO> searchPhotos(PostVO pv) {
		List<HomePhotoVO> searchPost = new ArrayList<>();
		
		try {
			searchPost = postDao.searchPhotos(pv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return searchPost;
	}

	@Override
	public List<HomePhotoVO> searchTotalPhotos(HomePhotoVO paramVO) {
		List<HomePhotoVO> searchPost = new ArrayList<>();
		
		try {
			searchPost = postDao.searchTotalPhotos(paramVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return searchPost;
	}

	@Override
	public List<QnaListVO> SearchTotalQnaList(QnaListVO qv) {
		List<QnaListVO> searchQna = new ArrayList<>();
		
		try {
			searchQna = postDao.SearchTotalQnaList(qv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return searchQna;
	}

	@Override
	public PhotoDetailVO selectQnaDetail(PostVO paramVO) {
		PhotoDetailVO qnaDetailVO = new PhotoDetailVO();
		
		try {
			qnaDetailVO = postDao.selectQnaDetail(paramVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qnaDetailVO;
	}


	
}
