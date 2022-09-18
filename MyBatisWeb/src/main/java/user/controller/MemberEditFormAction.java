package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.MemberDAO;
import user.model.MemberVO;

public class MemberEditFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String method=req.getMethod();
		if(!method.equalsIgnoreCase("post")) {
			req.setAttribute("msg", "잘못 들어온 경로입니다");
			req.setAttribute("loc", "javascript:history.back()");
			this.setViewPage("/memo/msg.jsp");
			this.setRedirect(false);
			return;
		}
		
		// [1] 회원번호 받기
		//[2] 유효성 체크 memberlist.do로 redirect이동
		String idx = req.getParameter("idx");		
		if(idx==null||idx.trim().isEmpty()) {
				this.setViewPage("memberlist.do");
				this.setRedirect(true);
				return;
		}
		//[3] MemberDAO생성해서 selectMemberByIdx(idx)호출
		MemberDAO dao = new MemberDAO();
		MemberVO vo =dao.selectMemberByIdx(Integer.parseInt(idx));
		//[4] req에 반환받은 MemberVO를 저장 =>"user" key값으로 저장
		req.setAttribute("user", vo);
		
		//[5] viewPage==> /member/update.jsp로 지정, forward이동
		this.setViewPage("/member/update.jsp");
		this.setRedirect(false);
	}
}
