package board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardEditEndAction extends AbstractAction {

	private final String UP_DIR="C:/MyJava/upload";
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		
		FileRenamePolicy fp = new DefaultFileRenamePolicy();
		MultipartRequest mr
		=new MultipartRequest(req, UP_DIR,100*1024*1024,"utf-8", fp);
		
		
		String idx = mr.getParameter("idx");
		String name = mr.getParameter("name");
		String subject = mr.getParameter("subject");
		String content = mr.getParameter("content");
		//예전에 첨부했던 파일명
		String old_filename=mr.getParameter("old_filename");
		
		//새로 첨부하는 파일
		String filename=mr.getFilesystemName("filename");
		
		long filesize=0;
		if(filename!=null) {
			File f=mr.getFile("filename");
			filesize=f.length();
			//예전에 첨부했던 파일이 있다면 서버에서 삭제처리 해야 함
			if(old_filename!=null &&  !old_filename.trim().isEmpty()) {
				File delF=new File(UP_DIR, old_filename);
				if(delF.exists()) {
					boolean b=delF.delete();
					System.out.println("파일 삭제 처리 여부: "+b);
				}
			}
			
		}
		
		if(idx==null||name==null||subject==null||
				idx.trim().isEmpty()||name.trim().isEmpty()
				||subject.trim().isEmpty()) {
			this.setRedirect(true);
			this.setViewPage("../../board/list.ict");
			return;
		}		
		BoardVO board=new BoardVO(Integer.parseInt(idx.trim()), name, subject, 
				content,0,filename,filesize,null);

		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		
		int n =dao.updateBoard(board);
		
		String str=(n>0)?"글 수정 성공":"글 수정 실패";
		String loc=(n>0)?"../../board/list.ict":"javascript:history.back()";
		
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		this.setRedirect(false);
		this.setViewPage("/common/msg.jsp");
	}

}


