package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
public class FileListAction extends AbstractAction {

	private final String UP_DIR="C:/MyJava/upload";
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// UP_DIR ���丮�� ���ϸ���� ������.
		File dir=new File(UP_DIR);
		
		File files[]=dir.listFiles();
		
		req.setAttribute("files", files);
		
		this.setViewPage("/file/fileList.jsp");
		this.setRedirect(false);
	}

}
