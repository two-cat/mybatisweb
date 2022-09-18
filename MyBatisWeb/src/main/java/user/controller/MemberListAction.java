package user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.MemberDAO;
import user.model.MemberVO;

public class MemberListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//검색유형, 검색어 받아오기
		String findType=req.getParameter("findType");
		String findKeyword=req.getParameter("findKeyword");
		
		MemberDAO dao = new MemberDAO();
		List<MemberVO> arr = null;
		if(findType==null||findKeyword==null||findType.trim().isEmpty()
				||findKeyword.trim().isEmpty()) {
			arr=dao.listMember();//전체 회원목록 가져오기
		}else {
			//검색한 회원목록 가져오기
			arr=dao.findMember(findType, findKeyword);
		}
		
		req.setAttribute("memberList", arr);
		this.setViewPage("/member/list.jsp");
		this.setRedirect(false);
	}

}
