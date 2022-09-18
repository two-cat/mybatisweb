package user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import user.model.MemberDAO;
import user.model.MemberVO;
import user.model.NotMemberException;

public class LoginEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		//1. ���̵�,���,saveId�Ķ���� �� �ޱ�
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		String saveId = req.getParameter("saveId");
		
		//2. ��ȿ�� üũ => login.do�� redirect
		if(userid==null||pwd==null||userid.trim().isEmpty()||pwd.trim().isEmpty())
		{
			this.setViewPage("login.ict");
			this.setRedirect(true);
			return;
		}
		MemberDAO dao=new MemberDAO();
		try {
			//3. MemberDAO���� loginCheck()
			MemberVO loginUser = dao.loginCheck(userid, pwd);
			//4. =>���� ó���ϱ�
			
			//5. MemberVO�� null�� �ƴϸ� ==> ���� ������ => req�̿��ؼ�
			//   ���ǿ� "loginUser"�� �����ϱ�
			if(loginUser!=null) {
				HttpSession session= req.getSession();
				session.setAttribute("loginUser", loginUser);
							
				//���̵� ����
				Cookie ck=new Cookie("uid", loginUser.getUserid());
				System.out.println(">>>"+loginUser.getUserid());
				if(saveId!=null) {
					ck.setMaxAge(7*24*60*60);//7�ϰ� ����
				}else {
					ck.setMaxAge(0);//��Ű ����
				}
				ck.setPath("/");
				res.addCookie(ck);
			}//if--------
			
			this.setViewPage("index.ict");
			this.setRedirect(true);
		}catch(NotMemberException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("loc", "login.ict");
			
			this.setViewPage("/common/msg.jsp");
			this.setRedirect(false);			
		}
	}

}
