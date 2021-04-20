package ourhouse.photo.service;

import java.util.List;

import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.MypagePhotoVO;
import ourhouse.common.vo.PostVO;

public interface IPhotoService {

	/**
	 * 사진 저장 전 글 먼저 올리는 메서드
	 * @param postVO 글정보가 담긴 vo
	 * @return 성공1 실패0
	 * @author 이경륜
	 */
	public int writePhoto(PostVO postVO);

	/**
	 * 게시글 사진 불러오는 메서드
	 * @param postNo
	 * @return photoList 파일vo담긴 리스트
	 * @author 이경륜
	 */
	public List<AtchFileVO> selectPhotoFiles(int postNo);

	/**
	 * 사진 작성자의 최근게시물 4개 보여주는 메서드
	 * @param postNo
	 * @return MypagePhotoVO list 
	 */
	public List<MypagePhotoVO> selectFourPhotos(int postNo);

}
