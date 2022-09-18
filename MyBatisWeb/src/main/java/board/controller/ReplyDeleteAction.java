package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import reply.model.ReplyDAOMyBatis;

public class ReplyDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 1.��۹�ȣ(pk), �θ�۹�ȣ �޾ƿ���
		// /user/board/replyDel.ict
		String re_idx = req.getParameter("re_idx");
		String idx_fk = req.getParameter("idx_fk");
		
		System.out.println(re_idx+"/"+idx_fk);
		
		//2. ��ȿ�� üũ "`../../board/view.ict?idx=�θ�۹�ȣ" => redirect�̵�
		if(re_idx==null||idx_fk==null||re_idx.trim().isEmpty()||idx_fk.trim().isEmpty()) {
			this.setViewPage("../../board/view.ict?idx="+idx_fk);
			this.setRedirect(true);
			return;
		}
		
		//3. ReplyDAOMyBatis���� deleteReply(��۹�ȣ)
		ReplyDAOMyBatis rdao=new ReplyDAOMyBatis();
		int n=rdao.deleteReply(Integer.parseInt(re_idx.trim()));
		
		//4.  ����ó���� "`../../board/view.ict?idx=�θ�۹�ȣ" => redirect�̵�
		this.setRedirect(true);
		this.setViewPage("../../board/view.ict?idx="+idx_fk);

	}

}
