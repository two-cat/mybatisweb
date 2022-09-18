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
			req.setAttribute("msg", "�߸� ���� ����Դϴ�");
			req.setAttribute("loc", "javascript:history.back()");
			this.setViewPage("/memo/msg.jsp");
			this.setRedirect(false);
			return;
		}
		
		// [1] ȸ����ȣ �ޱ�
		//[2] ��ȿ�� üũ memberlist.do�� redirect�̵�
		String idx = req.getParameter("idx");		
		if(idx==null||idx.trim().isEmpty()) {
				this.setViewPage("memberlist.do");
				this.setRedirect(true);
				return;
		}
		//[3] MemberDAO�����ؼ� selectMemberByIdx(idx)ȣ��
		MemberDAO dao = new MemberDAO();
		MemberVO vo =dao.selectMemberByIdx(Integer.parseInt(idx));
		//[4] req�� ��ȯ���� MemberVO�� ���� =>"user" key������ ����
		req.setAttribute("user", vo);
		
		//[5] viewPage==> /member/update.jsp�� ����, forward�̵�
		this.setViewPage("/member/update.jsp");
		this.setRedirect(false);
	}
}
