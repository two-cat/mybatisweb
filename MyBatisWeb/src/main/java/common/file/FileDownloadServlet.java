package common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/FileDown")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		download(request, response);		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		download(request, response);
	}

	private void download(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException
	{
		//1. 다운로드 받을 파일명 받아오기
		String fname=request.getParameter("fname");
		System.out.println("fname="+fname);
		
//		response.setContentType("application/unknown");
		response.setContentType("application/octet-stream");
		/*response 헤더에 컨텐트타입을 지정하는데,
		 * 브라우저는 컨텐트타입이 알 수 있는 형식이면
		 * 파싱해서 보여주고, 만약 모르는 형식이면 다운로드
		 * 창을 실행시킨다.
		 * */
		//한글명인 파일일 경우 8859-1 인코딩 처리하자
		String file_enc=new String(fname.getBytes(),"ISO-8859-1");		
		response.setHeader("Content-Disposition", "attachement; filename="+file_enc);
		
		//서버에 있는 파일과 InputStream 연결해서 읽어들이고, 브라우저 출력스트림을 통해
		//읽은 데이터를 클라이언트에게 내보내자.
		String path="C:/MyJava/upload/"+fname;
		
		FileInputStream fis=new FileInputStream(path);
		BufferedInputStream bis=new BufferedInputStream(fis);
		
		ServletOutputStream sos=response.getOutputStream();
		BufferedOutputStream bos=new BufferedOutputStream(sos);
		
		byte[] data=new byte[4000];
		int n=0;
		while((n=bis.read(data))!=-1) {
			bos.write(data, 0, n);
		}
		bos.flush();
		bos.close();
		bis.close();
		fis.close();
		sos.close();
		
	}

}



