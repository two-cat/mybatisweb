package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.controller.AbstractAction;
import board.model.*;

public class BoardFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		
		this.setViewPage("/board/boardWrite.jsp");
		this.setRedirect(false);
	}

}
