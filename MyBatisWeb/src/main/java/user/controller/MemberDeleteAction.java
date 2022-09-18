package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.MemberDAO;

public class MemberDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//[1] ������ ȸ����ȣ �ޱ�
		String idx = req.getParameter("idx");		
		//[2] ��ȿ�� üũ => redirect������� memberlist.do�� �̵�
		if(idx==null||idx.trim().isEmpty()) {
			//res.sendRedirect("memberlist.do");
			this.setViewPage("memberlist.do");
			this.setRedirect(true);
			return;
		}
		//[3] MemberDAO���� deleteMember(idx)
		MemberDAO dao = new MemberDAO();
		int n = dao.deleteMember(Integer.parseInt(idx));
		//[4] �� ��� req�� ���� msg, loc (memberlist.do�� �̵�)
		String str = (n>0)? "���� ����":"���� ����";
		String loc = (n>0)? "memberlist.do":"javascript:history.back()";
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		//[5] /memo/msg.jsp�� forward������� �̵�
		this.setViewPage("/memo/msg.jsp");
		this.setRedirect(false);
	}

}
