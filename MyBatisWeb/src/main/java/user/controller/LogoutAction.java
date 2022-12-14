package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;

public class LogoutAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//세션 변수를 모두 무효화
		HttpSession session = req.getSession();
		
		session.invalidate();
		
		this.setViewPage("index.ict");
		this.setRedirect(true);

	}

}
