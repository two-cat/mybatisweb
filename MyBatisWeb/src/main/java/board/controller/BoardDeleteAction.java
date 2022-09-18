package board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.MemberVO;

public class BoardDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//[1] 삭제할 글번호 받아오기
		String idx=req.getParameter("idx");		
		//[2] 유효성 체크 => "../../board/list.ict"로 redirect 이동방식 설정
		if(idx==null||idx.trim().isEmpty()) {
			this.setRedirect(true);
			this.setViewPage("../../board/list.ict");
			return;
		}
		
		//[3] 글번호로 글쓴 사람의 아이디값 가져오기==> 
		//   BoardDAOMyBatis 의 selectBoardByIdx(글번호)==> BoardVO반환
		//   getName()==>아이디 반환
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		
		BoardVO board=dao.selectBoardByIdx(Integer.parseInt(idx.trim()));
		
		//[4] 세션에 저장된 loginUser를 꺼내오기
		HttpSession session=req.getSession();
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		
		//[5] 글쓴 사람의 아이디와 로그인한 사람의 아이디가 일치하다면 
		// [5-1] 댓글을 먼저 삭제 처리하자
		// [5_2] 부모글을 삭제처리
		//    또는 관리자라면 삭제 처리
		// [5_3] 기존에 올렸던 글에 첨부파일이 있다면 서버에서 해당 첨부파일을 삭제 처리
		// 이 모든 것이 모두 성공(commit)하던지 아니면 1개라도 실패하면 모두 rollback해야 함
		//   BoardDAOMyBatis의 deleteBoard(글번호)
		//[6] 그결과  메시지,이동경로 저장 => list.ict로 이동경로 지정
		if(board.getName().equals(loginUser.getUserid())||
				loginUser.getUserid().equals("admin")) {
			
			int n= dao.deleteBoard(Integer.parseInt(idx.trim()));
			
			if(n>0) {
				//첨부파일이 있다면 서버에서 첨부파일 삭제 처리
				if(board.getFilename()!=null) {
					String UP_DIR="C:/MyJava/upload";
					File f=new File(UP_DIR, board.getFilename());
					if(f!=null &&f.exists()) {
						f.delete();//삭제 처리
					}
				}
			}
			
			String str=(n>0)?"삭제 성공":"삭제 실패";
			String loc=(n>0)?"../../board/list.ict":"javascript:history.back()";
			req.setAttribute("msg", str);
			req.setAttribute("loc", loc);
			
		}else {
			req.setAttribute("msg", "해당글을 작성한 사람만 삭제할 수 있어요");
			req.setAttribute("loc", "javascript:history.back()");
		}
		
		this.setViewPage("/common/msg.jsp");
		this.setRedirect(false);

	}

}
