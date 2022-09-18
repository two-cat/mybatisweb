package user.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import user.model.MemberDAO;
import user.model.MemberVO;
import user.model.NotMemberException;

public class LoginEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		//1. 아이디,비번,saveId파라미터 값 받기
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		String saveId = req.getParameter("saveId");
		
		//2. 유효성 체크 => login.do로 redirect
		if(userid==null||pwd==null||userid.trim().isEmpty()||pwd.trim().isEmpty())
		{
			this.setViewPage("login.ict");
			this.setRedirect(true);
			return;
		}
		MemberDAO dao=new MemberDAO();
		try {
			//3. MemberDAO생성 loginCheck()
			MemberVO loginUser = dao.loginCheck(userid, pwd);
			//4. =>예외 처리하기
			
			//5. MemberVO가 null이 아니면 ==> 세션 얻어오기 => req이용해서
			//   세션에 "loginUser"를 저장하기
			if(loginUser!=null) {
				HttpSession session= req.getSession();
				session.setAttribute("loginUser", loginUser);
							
				//아이디 저장
				Cookie ck=new Cookie("uid", loginUser.getUserid());
				System.out.println(">>>"+loginUser.getUserid());
				if(saveId!=null) {
					ck.setMaxAge(7*24*60*60);//7일간 보관
				}else {
					ck.setMaxAge(0);//쿠키 삭제
				}
				ck.setPath("/");
				res.addCookie(ck);
			}//if--------
			
			this.setViewPage("index.ict");
			this.setRedirect(true);
		}catch(NotMemberException e) {
			req.setAttribute("msg", e.getMessage());
			req.setAttribute("loc", "login.ict");
			
			this.setViewPage("/common/msg.jsp");
			this.setRedirect(false);			
		}
	}

}
