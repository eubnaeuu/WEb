package ourhouse.common.vo;

/**
 * 스트링 상수 모음
 * @author 이경륜
 */
public class BaseVO {
	
	public static final String Y = "Y";
	public static String getY() {
		return Y;
	}
	public static final String N = "N";
	public static String getN() {
		return N;
	}
	
	public static final String ACTION_C = "C";
	public static final String ACTION_R = "R";
	public static final String ACTION_U = "U";
	public static final String ACTION_D = "D";
	public static final String VIEW_TYPE_EDIT = "EDIT";
	
	public static final String DEFAULT_PROFILE_PATH = " "; // 기본프사 저장경로
	public static String getDEFAULT_PROFILE_PATH() {
		return DEFAULT_PROFILE_PATH;
	}
	public static final String DEFAULT_PROFILE_NAME = "default_profile.png"; // 기본프사 저장이름
	public static String getDEFAULT_PROFILE_NAME() {
		return DEFAULT_PROFILE_NAME;
	}
	
	public static final String DEFAULT_ID = " "; // 비로그인시 팔로우, 좋아요 표시되지않도록
	public static String getDEFAULT_ID() {
		return DEFAULT_ID;
	}
	
	
}
