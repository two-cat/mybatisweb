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
		//�˻�����, �˻��� �޾ƿ���
		String findType=req.getParameter("findType");
		String findKeyword=req.getParameter("findKeyword");
		
		MemberDAO dao = new MemberDAO();
		List<MemberVO> arr = null;
		if(findType==null||findKeyword==null||findType.trim().isEmpty()
				||findKeyword.trim().isEmpty()) {
			arr=dao.listMember();//��ü ȸ����� ��������
		}else {
			//�˻��� ȸ����� ��������
			arr=dao.findMember(findType, findKeyword);
		}
		
		req.setAttribute("memberList", arr);
		this.setViewPage("/member/list.jsp");
		this.setRedirect(false);
	}

}
