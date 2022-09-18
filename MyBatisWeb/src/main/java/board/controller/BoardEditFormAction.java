package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.MemberVO;

public class BoardEditFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String idx=req.getParameter("idx");
		if(idx==null||idx.trim().isEmpty()) {
			this.setRedirect(true);
			this.setViewPage("../../board/list.ict");
			return;
		}
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		BoardVO board = dao.selectBoardByIdx(Integer.parseInt(idx.trim()));
		
		HttpSession session=req.getSession();
		MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
		
		if(board.getName().equals(loginUser.getUserid())) {
			
			req.setAttribute("board", board);
			this.setViewPage("/board/boardEdit.jsp");
			this.setRedirect(false);
			
		}else {
			req.setAttribute("msg", "글 작성자만 수정 가능해요");
			req.setAttribute("loc", "javascript:history.back()");
			this.setViewPage("/common/msg.jsp");
			this.setRedirect(false);
		}

	}

}
