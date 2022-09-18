package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.model.MemberDAO;
import user.model.MemberVO;

public class MemberEditEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//req.setCharacterEncoding("utf-8");//추후 한글처리 필터로 처리 예정
		
		String idx = req.getParameter("idx");
		String name=req.getParameter("name");
		String userid=req.getParameter("userid");
		String pwd=req.getParameter("pwd");
		String hp1=req.getParameter("hp1");
		String hp2=req.getParameter("hp2");
		String hp3=req.getParameter("hp3");
		String zipcode=req.getParameter("zipcode");
		String addr1=req.getParameter("addr1");
		String addr2=req.getParameter("addr2");
		String mileage=req.getParameter("mileage");
		//유효성 체크
		if(idx==null||name==null||userid==null||pwd==null||
				idx.trim().isEmpty()||
				name.trim().isEmpty()||userid.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("join.do");//회원가입 페이지 지정
			this.setRedirect(true);//redirect로 이동
			return;
		}
		
		int idx_int=Integer.parseInt(idx.trim());
		int mileage_int = Integer.parseInt(mileage.trim());
		
		MemberVO user=new MemberVO(idx_int,name,userid, pwd, hp1,hp2,hp3,zipcode, addr1, addr2,null, mileage_int);
		MemberDAO dao=new MemberDAO();
		
		int n = dao.updateMember(user);
		
		String str=(n>0)?"회원정보 수정 완료":"정보 수정 실패";
		String loc=(n>0)?"memberlist.do":"javascript:history.back()";
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);		
		this.setViewPage("/memo/msg.jsp");
		this.setRedirect(false);
		
	}

}
