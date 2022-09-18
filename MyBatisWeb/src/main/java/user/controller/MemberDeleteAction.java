package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.MemberDAO;

public class MemberDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//[1] 삭제할 회원번호 받기
		String idx = req.getParameter("idx");		
		//[2] 유효성 체크 => redirect방식으로 memberlist.do로 이동
		if(idx==null||idx.trim().isEmpty()) {
			//res.sendRedirect("memberlist.do");
			this.setViewPage("memberlist.do");
			this.setRedirect(true);
			return;
		}
		//[3] MemberDAO생성 deleteMember(idx)
		MemberDAO dao = new MemberDAO();
		int n = dao.deleteMember(Integer.parseInt(idx));
		//[4] 그 결과 req에 저장 msg, loc (memberlist.do로 이동)
		String str = (n>0)? "삭제 성공":"삭제 실패";
		String loc = (n>0)? "memberlist.do":"javascript:history.back()";
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		//[5] /memo/msg.jsp로 forward방식으로 이동
		this.setViewPage("/memo/msg.jsp");
		this.setRedirect(false);
	}

}
