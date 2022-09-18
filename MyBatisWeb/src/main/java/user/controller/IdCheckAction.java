package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.MemberDAO;

public class IdCheckAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String method = req.getMethod();
		System.out.println(method);
		//get����̸� id�Է� ���� �����ְ�
		if(method.equalsIgnoreCase("get")) {
			req.setAttribute("mode", "get");
			
			this.setViewPage("/member/idCheck.jsp");
			this.setRedirect(false);
			
		}else {
			//post����̸� db���� id�ߺ����� üũ ����� ������
			String userid = req.getParameter("userid");
			//��ȿ�� üũ
			if(userid==null||userid.trim().isEmpty()) {
				req.setAttribute("msg", "���̵� �Է��ؾ� �ؿ�");
				req.setAttribute("loc", "javascript:history.back()");
				
				this.setViewPage("/memo/msg.jsp");
				this.setRedirect(false);
				return;
			}			
			MemberDAO dao=new MemberDAO();
			boolean isUse = dao.idCheck(userid);			
			String result =(isUse)?"ok":"fail";			
			req.setAttribute("mode", "post");
			req.setAttribute("result", result);
			req.setAttribute("userid", userid);
			
			this.setViewPage("/member/idCheck.jsp");
			this.setRedirect(false);			
		}		
	}
}
