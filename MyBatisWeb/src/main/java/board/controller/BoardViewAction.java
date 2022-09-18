package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import reply.model.ReplyDAOMyBatis;
import reply.model.ReplyVO;

public class BoardViewAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//[1] �۹�ȣ �޾ƿ���
		String idx=req.getParameter("idx");
		//[2] ��ȿ�� üũ list.ict�� redirect������� ����
		if(idx==null||idx.trim().isEmpty()) {
			this.setRedirect(true);
			this.setViewPage("list.ict");
			return;
		}
		
		//[3] BoardDAOMyBatis����
		
		//		BoardVO selectBoardByIdx(�۹�ȣ)
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		//        (��ȸ�� ����)		
		int n=dao.updateReadnum(Integer.parseInt(idx.trim()));
		
		BoardVO board = dao.selectBoardByIdx(Integer.parseInt(idx.trim()));
		
		//[4] �� ����� �޾Ƽ� req�� ����=> "board" Ű������ ����
		req.setAttribute("board", board);
		
		//[5] �ش� �Խñۿ� ���� ��� ��� ��������
		ReplyDAOMyBatis reDao = new ReplyDAOMyBatis();
		List<ReplyVO> replyArr = reDao.listReply(Integer.parseInt(idx.trim()));
		req.setAttribute("replyArr", replyArr);
		
		this.setViewPage("/board/boardView.jsp");
		this.setRedirect(false);
	}

}
