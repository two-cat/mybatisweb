package common.controller;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUploadAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//request entity body�� ���ԵǾ� ���� ���� �����͸� ��Ʈ�� �����ؼ�
		//���� �� �������� ����غ���.
		//����Ʈ Ÿ��, ���� ũ��
		String ctype=req.getContentType();
		//multipart/form-data; boundary=---------------------------7e5372103094e
		int len=req.getContentLength();//÷������ ũ��		
		ServletInputStream in=req.getInputStream();		
		byte[] data = new byte[1024];
		int n = 0,count = 0;
		String str ="";
		while((n = in.read(data)) != -1){
			String str2 = new String(data,0,n);
			str += str2;
			count += n;
		}
		System.out.println("count = " + count);
		req.setAttribute("ctype", ctype);
		req.setAttribute("len", len);
		req.setAttribute("requestData", str);


		this.setViewPage("/file/fileResult.jsp");
		this.setRedirect(false);
	}

}
