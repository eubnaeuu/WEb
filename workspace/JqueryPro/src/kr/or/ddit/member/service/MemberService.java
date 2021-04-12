package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.vo.MemberVO;

public class MemberService {
	// singleton 패턴 적용
	private MemberDao dao;
	
	public MemberService() {
		if(dao == null)
			dao = new MemberDao();
	}
	
	
	
	
	public List<MemberVO> retrieveMemberList(MemberVO memberVo) throws SQLException {
		List<MemberVO> list = dao.retrieveMemberList(memberVo);
		return list;
	}
	
	public void createMember(MemberVO memberVo) {
		// 1. 등록전 유효성 체크
		// 	1). 중복된 ID인지 체크
			dao.retrieveMemberList(memberVo.getMemId());
			return;
		// 	2). ID, password 등 유요한 값인지 체크
		
		// 2. DB에 insert 하기
	}
	
}
