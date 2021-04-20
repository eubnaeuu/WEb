package ourhouse.member.dao;

import java.util.List;

import ourhouse.common.vo.AdminMemberVO;
import ourhouse.common.vo.AtchFileVO;
import ourhouse.common.vo.FollowImgVO;
import ourhouse.common.vo.FollowVO;
import ourhouse.common.vo.MemberVO;
import ourhouse.common.vo.MyCouponListVO;
import ourhouse.common.vo.MyLikesVO;
import ourhouse.common.vo.MypagePhotoVO;
import ourhouse.common.vo.PostVO;
import ourhouse.common.vo.ProfileImgVO;
import ourhouse.common.vo.QnaListVO;
import ourhouse.common.vo.SearchMemberVO;

public interface IMemberDao {
	/**
	 * 회원의 정보를 가져오는 메서드 
	 * @param memId
	 * @return MemberVO
	 */
	MemberVO getMember(String memId) throws Exception;
	/**
	 * 내가 팔로우하는 사람들 수
	 * @param memId
	 * @return int 
	 */
	int getFollow(String memId) throws Exception;
	/**
	 * 나를 팔로우하는 사람들 수
	 * @param memId
	 * @return int
	 */
	int getFollower(String memId) throws Exception;
	/**
	 * 내가 좋아요 누른 숫자
	 * @param memId
	 * @return int
	 */	
	int getLikes(String memId) throws Exception;
	/**
	 * 내가 보유한 쿠폰 숫자
	 * @param memId
	 * @return int
	 */
	int getCoupon(String memId)  throws Exception ;
	/**
	 * 나의 프로필 이미지 가져오기
	 * @param memId
	 * @return String 이미지 이름 값
	 */
	String getPrfileImg(String memId) throws Exception;
	/**
	 * 나의 질문글 리스트
	 * @param memId
	 * @return List<PostVO>
	 */
	List<PostVO> getQnAList(String memId) throws Exception;
	/**
	 * 나의 게시글 리스트
	 * @param memId
	 * @return List<PostVO>
	 */
	List<PostVO> getPostList(String memId) throws Exception;
	
	/**
	 * 내가한 질문에대한 댓글 수
	 * @param qnANo
	 * @return int
	 */
	int getAnswer(int qnANo) throws Exception;
	
	/**
	 * 관리자가 모든 회원 조회하는 메서드
	 * @return List<MemberVO>
	 * @throws Exception
	 */
	List<AdminMemberVO> getMemList() throws Exception;
	
	/**
	 * 사진글 사진파일 가져오는 메서드
	 * @param postNo
	 * @return
	 */
	List<AtchFileVO> getAtchList(int postNo) throws Exception;
	
	/**
	 * 질문글 사진파일 가져오는 메서드
	 * @param qnANo
	 * @return
	 */
	List<AtchFileVO> getQatchList(int qnANo)throws Exception;
	
	/**
	 * 마이페이지 회원이 작성한 사진 가져오기
	 * @param memId
	 * @return
	 */
	List<MypagePhotoVO> getPhotoList(String memId)throws Exception;
	
	/**
	 * 회원가입
	 * MemberVO에 담긴 자료 DB에 insert하는 메서드
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환
	 */
	public Object signupMember(MemberVO mv) throws Exception;
	
	
	/**
	 * 회원조회
	 * 사용자에게 입력받은 아이디 받아서 DB에 일치한 아이디 있는지 조회
	 * @param memId 
	 * @return 일치한 아이디가 있다면 아이디에 대한 회원정보 VO로 반환
	 */
	MemberVO selectMember(String memId) throws Exception;
	
	/**
	 * 닉네임 같은 회원 조회
	 * 사용자에게 입력받은 닉네임 받아서 DB에 일치한 닉네임 있는지 조회
	 * @param memNickname 
	 * @return 일치한 닉네임이 있다면 닉네임에 대한 회원정보 VO로 반환
	 */
	MemberVO seletNickName(String memNickname) throws Exception;
	
	/**
	 * 이메일 같은 회원 조회
	 * 사용자에게 입력받은 이메일 받아서 DB에 일치한 이메일 있는지 조회
	 * @param memNickname 
	 * @return 일치한 이메일이 있다면 이메일에 대한 회원정보 VO로 반환
	 */
	MemberVO selectEmail(String memEmail) throws Exception;
	
	
	/**
	 * 회원탈퇴 
	 * "Y"를 가져가서 memdelte를 "Y"로 수정
	 * @param mv
	 * @return 수정 성공하면 1을 반환
	 */
	int memDelete(MemberVO mv) throws Exception;
	
	/**
	 * 회원이 구매한 쿠폰을 조회
	 * @param mcVO
	 * @return
	 */
	List<MyCouponListVO> selectCouponList(String memId) throws Exception;
	
	/**
	 * 팔로우 기능
	 * @param targetId
	 * @return
	 */
	int insertFollowMember(String targetId) throws Exception;
	
	/**
	 * 회원비밀번호 재설정 
	 * @param mv 사용자가 입력한 이메일과 비밀번호를 가져가서 수정
	 * @return 수정성공이면 1을 반환
	 */
	int updateMemPass(MemberVO mv) throws Exception;
	
	/**
	 * 회원이 입력한 새비밀번호와 동일한 비밀번호인지 중복체크
	 * @param mv
	 * @return 회원이 입력한 새비밀번호와 동일한 회원정보를 vo에 담아서 들고옴
	 */
	MemberVO selectMemPass(MemberVO mv) throws Exception;
	
	/**
	 * 나의 질문글 상세보기 정보 가져오는 메서드
	 * @author 조예슬
	 * @param memId
	 * @return List<QnaListVO> 
	 */
	List<QnaListVO> memberQnaList(String memId)throws Exception;
	/**
	 * 내가 좋아요 누른 글 상세페이지
	 * @author 조예슬
	 * @param memId
	 * @return List<MyLikesVO> 
	 */
	List<MyLikesVO> myLikesList(String memId)throws Exception;
	
	/**
	 * 회원정보 수정 (별명,한줄소개) 
	 * @param mv 사용자가 입력한 별명,한줄소개,이메일(readonly)
	 * @return 업데이트 성공시 1 반환
	 */
	int memberInfoUpdate(MemberVO mv)throws Exception;
	/**
	 * 회원이 쿠폰을 구매함에따라 포인트 차감
	 * @author 조예슬
	 * @param mv
	 * @return
	 */
	int updateMemPoint(MemberVO mv)throws Exception;
	/**
	 * 통합검색으로 다른 유저 검색
	 * @author 조예슬
	 * @param pv
	 * @return List<ProfileImgVO> 다른 유저 정보
	 */
	List<SearchMemberVO> searchTotalUser(SearchMemberVO sv)throws Exception;
	
	/**
	 * 질문게시판 댓글 등록후 포인트 50점 업데이트
	 * @param replyNo
	 * @return 성공시 1 
	 */
	int updateReplyPoint(int replyNo) throws Exception;
	
	/**
	 * 팔로잉 목록 조회
	 * @return 팔로잉 목록
	 */
	List<FollowVO> getFollowingList(FollowVO fv) throws Exception;
	
	/**
	 * 팔로잉 이미지 목록 조회
	 * @param fv
	 * @return 팔로잉 이미지
	 * @throws Exception
	 */
	List<FollowImgVO> getFollowingImg(FollowImgVO fivo) throws Exception;
	
	/**
	 * 팔로워 목록 조회
	 * @param fv
	 * @return 팔로워 목록
	 * @throws Exception
	 */
	List<FollowVO> getFollowerList(FollowVO fv) throws Exception;
	
	/**
	 * 팔로워 이미지 목록 조회
	 * @param fivo
	 * @return 팔로워 이미지 목록
	 * @throws Exception
	 */
	List<FollowImgVO> getFollowerImg(FollowImgVO fivo) throws Exception;
	


	
}
