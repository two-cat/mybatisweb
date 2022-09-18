package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.model.TestDAOMyBatis;

public class TestAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setAttribute("title", "From TestAction");
		
		TestDAOMyBatis dao=new TestDAOMyBatis();
		String count=dao.getTotalCount();
		
		req.setAttribute("count", count);
		
		this.setViewPage("/test.jsp");
		this.setRedirect(false);

	}

}
