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
		//[1] 글번호 받아오기
		String idx=req.getParameter("idx");
		//[2] 유효성 체크 list.ict로 redirect방식으로 설정
		if(idx==null||idx.trim().isEmpty()) {
			this.setRedirect(true);
			this.setViewPage("list.ict");
			return;
		}
		
		//[3] BoardDAOMyBatis생성
		
		//		BoardVO selectBoardByIdx(글번호)
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		//        (조회수 증가)		
		int n=dao.updateReadnum(Integer.parseInt(idx.trim()));
		
		BoardVO board = dao.selectBoardByIdx(Integer.parseInt(idx.trim()));
		
		//[4] 그 결과를 받아서 req에 저장=> "board" 키값으로 저장
		req.setAttribute("board", board);
		
		//[5] 해당 게시글에 대한 댓글 목록 가져오기
		ReplyDAOMyBatis reDao = new ReplyDAOMyBatis();
		List<ReplyVO> replyArr = reDao.listReply(Integer.parseInt(idx.trim()));
		req.setAttribute("replyArr", replyArr);
		
		this.setViewPage("/board/boardView.jsp");
		this.setRedirect(false);
	}

}
