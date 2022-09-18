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
		//1. �ٿ�ε� ���� ���ϸ� �޾ƿ���
		String fname=request.getParameter("fname");
		System.out.println("fname="+fname);
		
//		response.setContentType("application/unknown");
		response.setContentType("application/octet-stream");
		/*response ����� ����ƮŸ���� �����ϴµ�,
		 * �������� ����ƮŸ���� �� �� �ִ� �����̸�
		 * �Ľ��ؼ� �����ְ�, ���� �𸣴� �����̸� �ٿ�ε�
		 * â�� �����Ų��.
		 * */
		//�ѱ۸��� ������ ��� 8859-1 ���ڵ� ó������
		String file_enc=new String(fname.getBytes(),"ISO-8859-1");		
		response.setHeader("Content-Disposition", "attachement; filename="+file_enc);
		
		//������ �ִ� ���ϰ� InputStream �����ؼ� �о���̰�, ������ ��½�Ʈ���� ����
		//���� �����͸� Ŭ���̾�Ʈ���� ��������.
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



