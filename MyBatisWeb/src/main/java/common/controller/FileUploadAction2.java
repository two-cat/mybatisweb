package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;
import java.io.*;
/*
#���̺귯�� �ٿ�ε�
[1] http://www.servlets.com/cos
=>zip���� �ٿ�ε� �޾� ����Ǯ��=>  cos.jar������ �����Ͽ�
"������Ʈ/WEB-INF/lib" �Ʒ� �ٿ��ֱ� �Ѵ�.

[2] ���ε��� ���丮 ����=>C:/MyJava/upload

[3] ��Ű�� import :
com.oreilly.servlet.*,com.oreilly.servlet.multipart.*

[4] MultipartRequest��ü�� ������ �ص� ���Ͼ��ε尡 �ȴ�.
	���� ���� �뷮�� ũ�ų� ������ ��� => IOException�� �߻���
*/
public class FileUploadAction2 extends AbstractAction {
	
	private final String UP_DIR="C:/MyJava/upload";

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		File dir=new File(UP_DIR);
		if(!dir.exists()) {
			dir.mkdirs();//���ε� ���丮 �����
		}
		try {
		FileRenamePolicy fp=new DefaultFileRenamePolicy();//������ ������ ���� ��� ���ϸ� �ε��� ��ȣ�� �ٿ���
		MultipartRequest mr=new MultipartRequest(req, UP_DIR,100*1024*1024,"utf-8", fp);
													//�ִ� ���ε� �뷮: 100Mb
		//mr�� �� �����Ǹ� ���ε� ����
			System.out.println("���ε� ����");
			//[1] �ø���
			//String name=req.getParameter("name"); [x]
			String name = mr.getParameter("name"); //[o]
			
			//[2] ÷�����ϸ�=> getFileSystemName("�Ķ���͸�")���� ���ȿ� ��������
			
			String filename = mr.getFilesystemName("filename");//������ ���ϸ�
			String originFile = mr.getOriginalFileName("filename");//���� ���ϸ�
			
			System.out.println("�ø���: "+name);
			System.out.println("���ε� ���ϸ�1: "+filename+"=> �������ϸ�: "+originFile);
			
			//[3] ����ũ��
			File f=mr.getFile("filename");
			long fsize=0;
			if(f!=null) {
				fsize= f.length();//����ũ��
				System.out.println("����ũ��: "+fsize);
			}
			req.setAttribute("filename", filename);
			req.setAttribute("fsize", fsize);
			req.setAttribute("origin", originFile);
			
		}catch(IOException e) {
			System.out.println("���ε� ����: "+e);
			e.printStackTrace();
		}

		this.setRedirect(false);
		this.setViewPage("/file/fileResult2.jsp");
	}

}
