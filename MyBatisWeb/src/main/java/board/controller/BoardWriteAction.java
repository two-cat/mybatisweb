package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;
import java.io.*;

public class BoardWriteAction extends AbstractAction {
	
	private final String UP_DIR="C:/MyJava/upload";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//1. 파일 업로드 처리
		MultipartRequest mr=null;
		try {
			FileRenamePolicy fp = new DefaultFileRenamePolicy();
			mr=new MultipartRequest(req,UP_DIR,100*1024*1024, "utf-8", fp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String name =mr.getParameter("name");
		String subject =mr.getParameter("subject");
		String content =mr.getParameter("content");
		//첨부파일명 얻기
		String filename = mr.getFilesystemName("filename");
		//파일크기 얻기
		long fsize=0;
		File f=mr.getFile("filename");
		if(f!=null) {
			fsize=f.length();
		}
		
		if(name==null||subject==null||name.trim().isEmpty()||subject.trim().isEmpty()) {
			this.setViewPage("form.ict");
			this.setRedirect(true);
			return;
		}
		
		BoardVO vo=new BoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setFilename(filename);
		vo.setFilesize(fsize);
		
		BoardDAOMyBatis dao = new BoardDAOMyBatis();
		
		int n = dao.insertBoard(vo);
		/*int n=0;
		for(int i=0;i<10;i++) {
			n=dao.insertBoard(vo);
		}*/
		
		// MyBatisWeb/user/board/writeEnd.ict
		String str=(n>0)?"글쓰기 성공":"글쓰기 실패";
		String loc=(n>0)?"../../board/list.ict":"javascript:history.back()";
		
		req.setAttribute("msg", str);
		req.setAttribute("loc", loc);
		this.setViewPage("/common/msg.jsp");
		this.setRedirect(false);
	}

}
