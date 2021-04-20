package ourhouse.post.dao;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import ourhouse.common.vo.CategoryVO;
import ourhouse.common.vo.HomePhotoVO;
import ourhouse.common.vo.PhotoDetailVO;
import ourhouse.common.vo.PostVO;
import ourhouse.common.vo.QnaListVO;
import ourhouse.util.SqlMapClientFactory;

public class PostDaoImpl implements IPostDao{
	
	private static IPostDao postDao;
	
	private SqlMapClient smc;
	
	private PostDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IPostDao getInstance() {
		if (postDao == null) {
			postDao = new PostDaoImpl();
		}
		return postDao;
	}

	@Override
	public List<HomePhotoVO> selectTopPhotos() throws Exception{
		
		List<HomePhotoVO> topPhotoList = smc.queryForList("home.selectTopPhotos");

		return topPhotoList;
	}

	@Override
	public List<HomePhotoVO> selectHomePhotos(HomePhotoVO paramVO) throws Exception {
		
		List<HomePhotoVO> homePhotoList = smc.queryForList("home.selectHomePhotos", paramVO);
		
		return homePhotoList;
	}

	@Override
	public List<QnaListVO> selectQnaList() throws Exception {
		
		List<QnaListVO> qnaList = smc.queryForList("qnalist.selectQnaList");
		
		return qnaList;
	}

	@Override
	public List<QnaListVO> categoryQnaList(CategoryVO categoryVO) throws Exception {
		List<QnaListVO> categoryList = smc.queryForList("qnalist.categoryQnaList",categoryVO);
		
		return categoryList;
	}

	@Override
	public int updatePostView(int postNo) throws Exception {
		
		int viewCount = smc.update("post.updatePostView",postNo);
		
		return viewCount;
	}

	@Override
	public PhotoDetailVO selectPhotoDetail(PostVO paramVO) throws Exception {
		
		PhotoDetailVO photoDetailVO = (PhotoDetailVO) smc.queryForObject("post.selectPhotoDetail",paramVO);
		
		return photoDetailVO;
	}

	@Override
	public List<HomePhotoVO> selectCategoryHomePhotos(CategoryVO categoryVO) throws Exception {
	
		List<HomePhotoVO> homeCatPhotoList = smc.queryForList("home.selectCategoryHomePhotos",categoryVO);
		
		return homeCatPhotoList;
	}

	@Override
	public int deletePost(int postNo) throws Exception {
		
		int cnt = smc.delete("post.deletePost", postNo);
		
		return cnt;
	}

	@Override
	public List<HomePhotoVO> searchPhotos(PostVO pv) throws Exception {
		List<HomePhotoVO> searchPost = smc.queryForList("home.searchPhotos",pv);
		
		return searchPost;
	}

	@Override
	public List<HomePhotoVO> searchTotalPhotos(HomePhotoVO paramVO) throws Exception {
		List<HomePhotoVO> searchPost = smc.queryForList("home.searchTotalPhotos",paramVO);
		
		return searchPost;
	}

	@Override
	public List<QnaListVO> SearchTotalQnaList(QnaListVO qv) throws Exception {
		List<QnaListVO> searchQna = smc.queryForList("qnalist.SearchTotalQnaList",qv);
		
		return searchQna;
	}

	@Override
	public PhotoDetailVO selectQnaDetail(PostVO paramVO) throws Exception {
		PhotoDetailVO qnaDetailVO = (PhotoDetailVO) smc.queryForObject("post.selectQnaDetail",paramVO);
		return qnaDetailVO;
	}


	
}