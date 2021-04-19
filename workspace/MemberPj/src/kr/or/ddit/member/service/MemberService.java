package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.member.dao.MemberDao;
import kr.or.ddit.member.vo.MemberVO;

public class MemberService {
	// singleton 패턴 적용
	private MemberDao dao;
	
	public MemberService() {
		if(dao == null)
			dao = new MemberDao();
	}
	
	public MemberVO retrieveMember(String memberId) throws SQLException {
		// 검증 작업이 추가되어야 함. => .java 파일은 컴파일 되어서 서버에 올라가기에 내용수정이 제한됨. 최대한 꼼꼼하게 한다생각
		MemberVO memberVo = dao.retrieveMember(memberId);
		return memberVo;
	}
	
	public List<MemberVO> retrieveMemberList(MemberVO memberVo) throws SQLException {
		List<MemberVO> list = dao.retrieveMemberList(memberVo);
		return list;
	}
	
	public void createMember(MemberVO memberVo) throws SQLException {
		// 1. 등록전 유효성 체크
		// 	1). 중복된 ID인지 체크
		MemberVO resultVo = dao.retrieveMember(memberVo.getMemId());
		if(resultVo != null) {
			return;
		}
		// 	2). ID, password 등 유효한 값인지 체크
		
		// 2. DB에 insert 하기
		dao.createMember(memberVo);
	}
	
}
