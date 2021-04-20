package ourhouse.post.service;

import java.util.List;

import ourhouse.common.vo.CategoryVO;
import ourhouse.common.vo.HomePhotoVO;
import ourhouse.common.vo.PhotoDetailVO;
import ourhouse.common.vo.PostVO;
import ourhouse.common.vo.QnaListVO;

public interface IPostService {

	/**
	 * 상단 최신 탑 조회수 글 2개 불러오는 메서드
	 * @return List<HomePhotoVO> 탑2 사진 게시글 vo를 담은 리스트
	 * @author 이경륜
	 */
	public List<HomePhotoVO> selectTopPhotos();
	
	/**
	 * 사진글 게시글 전체 불러오는 메서드
	 * @return List<HomePhotoVO> 홈화면 사진 게시글 VO를 담은 리스트
	 * @author 이경륜
	 * @param paramVO 로그인아이디담긴 vo (비로그인시 공백)
	 * @see
	 */
	public List<HomePhotoVO> selectHomePhotos(HomePhotoVO paramVO);
	
	/**
	 * 질문 게시글 전체 불러오는 메서드
	 * @author 조예슬
	 * @return
	 */
	public List<QnaListVO> selectQnaList();
	
	/**
	 * 카테고리 필터처리 메서드
	 * @author 조예슬
	 * @param CategoryVO categoryVO
	 * @return
	 */
	public List<QnaListVO> categoryQnaList(CategoryVO categoryVO);

	/**
	 * 게시글 클릭시 조회수 올라가는 메서드
	 * @author 이경륜
	 * @param postNo
	 * @return 성공시 1
	 */
	public int updatePostView(int postNo);

	/**
	 * 게시글 클릭시 해당 게시물의 정보를 불러오는 메서드
	 * @author 이경륜
	 * @param paramVO (로그인된 id와 게시글번호)
	 * @return photoDetailVO 게시글 정보
	 */
	public PhotoDetailVO selectPhotoDetail(PostVO paramVO);
	
	/**
	 * 카테고리 필터 적용 사진 조회 메서드
	 * @author 조예슬
	 * @param categoryVO
	 * @return List<HomePhotoVO> 조회후 사진 리스트
	 */
	public List<HomePhotoVO> selectCategoryHomePhotos(CategoryVO categoryVO);

	/**
	 * 사진 및 질문글 삭제 메서드
	 * @author 이경륜
	 * @param postNo
	 * @return 성공시 1
	 */
	public int deletePost(int postNo);
	
	/**
	 * 해시태그로 게시글 조회하는 메서드
	 * @author 조예슬
	 * @param hashtag
	 * @return List<HomePhotoVO> 검색어로 필터된 자료들
	 */
	public List<HomePhotoVO> searchPhotos(PostVO pv);
	
	/**
	 * 통합검색으로 사진글 가져오기
	 * @author 조예슬
	 * @param pv
	 * @return List<HomePhotoVO> 검색어로 필터된 자료들
	 */
	public List<HomePhotoVO> searchTotalPhotos(HomePhotoVO paramVO);
	
	/**
	 * 통합검색으로 질문글 가져오기
	 * @author 조예슬
	 * @param qv 
	 * @return List<QnaListVO> 검색어로 필터된 자료들
	 */
	public List<QnaListVO> SearchTotalQnaList(QnaListVO qv);
	
	/**
	 * 질문 게시글 클릭시 해당 게시물의 정보를 불러오는 메서드
	 * @author 이경륜
	 * @param paramVO (로그인된 id와 게시글번호)
	 * @return qnaDetailVO 게시글 정보
	 */
	public PhotoDetailVO selectQnaDetail(PostVO paramVO);


	



	
}
