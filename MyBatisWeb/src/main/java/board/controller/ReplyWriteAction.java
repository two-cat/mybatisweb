package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import reply.model.ReplyDAOMyBatis;
import reply.model.ReplyVO;

public class ReplyWriteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1. re_name, idx_fk, re_content  값 받기
			String re_name = req.getParameter("re_name");
			String idx_fk = req.getParameter("idx_fk");
			String re_content = req.getParameter("re_content");

		//					/user/board/replyEnd.ict
		//2. 유효성 체크 ==> // "../../board/view.ict"로 redirect이동
			if(idx_fk==null||idx_fk.trim().isEmpty()) {
				this.setRedirect(true);
				this.setViewPage("../../board/list.ict");
				return;
			}
			if(re_name==null||re_name.trim().isEmpty()) {
				this.setRedirect(true);
				this.setViewPage("../../board/view.ict?idx="+idx_fk);
				return;
			}
		
		
		//3. ReplyVO 에 담아주기
			int idx_fk_int=Integer.parseInt(idx_fk.trim());
			ReplyVO rvo=new ReplyVO(0,re_name,re_content,0, null, idx_fk_int);
		
		//4. ReplyDAOMyBatis생성해서 insertReply(rvo)
			ReplyDAOMyBatis dao=new ReplyDAOMyBatis();
			
			int n=dao.insertReply(rvo);
		
		this.setViewPage("../../board/view.ict?idx="+idx_fk);
		this.setRedirect(true);
	}

}
