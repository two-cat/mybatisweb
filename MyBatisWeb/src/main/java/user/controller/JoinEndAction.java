package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.MemberDAO;
import user.model.MemberVO;

public class JoinEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//req.setCharacterEncoding("utf-8");//���� �ѱ�ó�� ���ͷ� ó�� ����
		String name=req.getParameter("name");
		String userid=req.getParameter("userid");
		String pwd=req.getParameter("pwd");
		String hp1=req.getParameter("hp1");
		String hp2=req.getParameter("hp2");
		String hp3=req.getParameter("hp3");
		String zipcode=req.getParameter("zipcode");
		String addr1=req.getParameter("addr1");
		String addr2=req.getParameter("addr2");
		//��ȿ�� üũ
		if(name==null||userid==null||pwd==null||
				name.trim().isEmpty()||userid.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("join.do");//ȸ������ ������ ����
			this.setRedirect(true);//redirect�� �̵�
			return;
		}
		MemberVO user=new MemberVO(0,name,userid, pwd, hp1,hp2,hp3,zipcode, addr1, addr2,null, 1000);
		MemberDAO dao=new MemberDAO();
		int n = dao.insertMember(user);
		String str=(n>0)?"ȸ������ ó�� �Ϸ�":"ȸ������ ����";
		String loc=(n>0)?"admin/memberlist.do":"javascript:history.back()";
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);		
		this.setViewPage("/memo/msg.jsp");
		this.setRedirect(false);
		
	}

}
