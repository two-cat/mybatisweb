package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;
import java.io.*;
/*
#라이브러리 다운로드
[1] http://www.servlets.com/cos
=>zip파일 다운로드 받아 압축풀면=>  cos.jar파일을 복사하여
"프로젝트/WEB-INF/lib" 아래 붙여넣기 한다.

[2] 업로드할 디렉토리 생성=>C:/MyJava/upload

[3] 패키지 import :
com.oreilly.servlet.*,com.oreilly.servlet.multipart.*

[4] MultipartRequest객체를 생성만 해도 파일업로드가 된다.
	만약 파일 용량이 크거나 오류날 경우 => IOException이 발생됨
*/
public class FileUploadAction2 extends AbstractAction {
	
	private final String UP_DIR="C:/MyJava/upload";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		File dir=new File(UP_DIR);
		if(!dir.exists()) {
			dir.mkdirs();//업로드 디렉토리 만들기
		}
		try {
		FileRenamePolicy fp=new DefaultFileRenamePolicy();//동일한 파일이 있을 경우 파일명에 인덱스 번호를 붙여줌
		MultipartRequest mr=new MultipartRequest(req, UP_DIR,100*1024*1024,"utf-8", fp);
													//최대 업로드 용량: 100Mb
		//mr이 잘 생성되면 업로드 성공
			System.out.println("업로드 성공");
			//[1] 올린이
			//String name=req.getParameter("name"); [x]
			String name = mr.getParameter("name"); //[o]
			
			//[2] 첨부파일명=> getFileSystemName("파라미터명")으로 얻어옴에 주의하자
			
			String filename = mr.getFilesystemName("filename");//물리적 파일명
			String originFile = mr.getOriginalFileName("filename");//원본 파일명
			
			System.out.println("올린이: "+name);
			System.out.println("업로드 파일명1: "+filename+"=> 원본파일명: "+originFile);
			
			//[3] 파일크기
			File f=mr.getFile("filename");
			long fsize=0;
			if(f!=null) {
				fsize= f.length();//파일크기
				System.out.println("파일크기: "+fsize);
			}
			req.setAttribute("filename", filename);
			req.setAttribute("fsize", fsize);
			req.setAttribute("origin", originFile);
			
		}catch(IOException e) {
			System.out.println("업로드 실패: "+e);
			e.printStackTrace();
		}

		this.setRedirect(false);
		this.setViewPage("/file/fileResult2.jsp");
	}

}
