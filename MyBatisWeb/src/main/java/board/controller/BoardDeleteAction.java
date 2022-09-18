package board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.MemberVO;

public class BoardDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//[1] ������ �۹�ȣ �޾ƿ���
		String idx=req.getParameter("idx");		
		//[2] ��ȿ�� üũ => "../../board/list.ict"�� redirect �̵���� ����
		if(idx==null||idx.trim().isEmpty()) {
			this.setRedirect(true);
			this.setViewPage("../../board/list.ict");
			return;
		}
		
		//[3] �۹�ȣ�� �۾� ����� ���̵� ��������==> 
		//   BoardDAOMyBatis �� selectBoardByIdx(�۹�ȣ)==> BoardVO��ȯ
		//   getName()==>���̵� ��ȯ
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		
		BoardVO board=dao.selectBoardByIdx(Integer.parseInt(idx.trim()));
		
		//[4] ���ǿ� ����� loginUser�� ��������
		HttpSession session=req.getSession();
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		
		//[5] �۾� ����� ���̵�� �α����� ����� ���̵� ��ġ�ϴٸ� 
		// [5-1] ����� ���� ���� ó������
		// [5_2] �θ���� ����ó��
		//    �Ǵ� �����ڶ�� ���� ó��
		// [5_3] ������ �÷ȴ� �ۿ� ÷�������� �ִٸ� �������� �ش� ÷�������� ���� ó��
		// �� ��� ���� ��� ����(commit)�ϴ��� �ƴϸ� 1���� �����ϸ� ��� rollback�ؾ� ��
		//   BoardDAOMyBatis�� deleteBoard(�۹�ȣ)
		//[6] �װ��  �޽���,�̵���� ���� => list.ict�� �̵���� ����
		if(board.getName().equals(loginUser.getUserid())||
				loginUser.getUserid().equals("admin")) {
			
			int n= dao.deleteBoard(Integer.parseInt(idx.trim()));
			
			if(n>0) {
				//÷�������� �ִٸ� �������� ÷������ ���� ó��
				if(board.getFilename()!=null) {
					String UP_DIR="C:/MyJava/upload";
					File f=new File(UP_DIR, board.getFilename());
					if(f!=null &&f.exists()) {
						f.delete();//���� ó��
					}
				}
			}
			
			String str=(n>0)?"���� ����":"���� ����";
			String loc=(n>0)?"../../board/list.ict":"javascript:history.back()";
			req.setAttribute("msg", str);
			req.setAttribute("loc", loc);
			
		}else {
			req.setAttribute("msg", "�ش���� �ۼ��� ����� ������ �� �־��");
			req.setAttribute("loc", "javascript:history.back()");
		}
		
		this.setViewPage("/common/msg.jsp");
		this.setRedirect(false);

	}

}
