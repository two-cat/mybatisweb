package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.MemberDAO;

public class IdCheckAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String method = req.getMethod();
		System.out.println(method);
		//get방식이면 id입력 폼을 보여주고
		if(method.equalsIgnoreCase("get")) {
			req.setAttribute("mode", "get");
			
			this.setViewPage("/member/idCheck.jsp");
			this.setRedirect(false);
			
		}else {
			//post방식이면 db에서 id중복여부 체크 결과를 보여줌
			String userid = req.getParameter("userid");
			//유효성 체크
			if(userid==null||userid.trim().isEmpty()) {
				req.setAttribute("msg", "아이디를 입력해야 해요");
				req.setAttribute("loc", "javascript:history.back()");
				
				this.setViewPage("/memo/msg.jsp");
				this.setRedirect(false);
				return;
			}			
			MemberDAO dao=new MemberDAO();
			boolean isUse = dao.idCheck(userid);			
			String result =(isUse)?"ok":"fail";			
			req.setAttribute("mode", "post");
			req.setAttribute("result", result);
			req.setAttribute("userid", userid);
			
			this.setViewPage("/member/idCheck.jsp");
			this.setRedirect(false);			
		}		
	}
}
