package ourhouse.photo.dao;

import java.util.List;

import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.MypagePhotoVO;
import ourhouse.common.vo.PostVO;

public interface IPhotoDao {

	/**
	 * 사진 저장 전 글 먼저 올리는 메서드
	 * @param postVO 글정보가 담긴 vo
	 * @return 성공1
	 */
	public int writePhoto(PostVO postVO) throws Exception;
	
	/**
	 * 테스트용 디테일페이지 파일로드
	 * @param postNo
	 * @return photoList 파일vo담긴 리스트
	 */
	public List<AtchFileVO> selectPhotoFiles(int postNo) throws Exception;

	/**
	 * 사진 작성자의 최근게시물 4개 보여주는 메서드
	 * @param postNo
	 * @return MypagePhotoVO list 
	 */
	public List<MypagePhotoVO> selectFourPhotos(int postNo) throws Exception;

}
