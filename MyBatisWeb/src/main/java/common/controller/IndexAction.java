package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Sub Controller : 일꾼 AbstractAction를 상속받는다.
public class IndexAction extends AbstractAction {
   
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("IndexAction의 execute()메소드 호출됨...");
		
		req.setAttribute("title", "MyBatisWeb Index페이지");
		//뷰페이지 지정
		this.setViewPage("/index.jsp");
		//이동방식 지정
		this.setRedirect(false);//false주면 forward방식으로 이동
	}

}
