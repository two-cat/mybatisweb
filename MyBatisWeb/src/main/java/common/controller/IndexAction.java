package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Sub Controller : �ϲ� AbstractAction�� ��ӹ޴´�.
public class IndexAction extends AbstractAction {
   
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("IndexAction�� execute()�޼ҵ� ȣ���...");
		
		req.setAttribute("title", "MyBatisWeb Index������");
		//�������� ����
		this.setViewPage("/index.jsp");
		//�̵���� ����
		this.setRedirect(false);//false�ָ� forward������� �̵�
	}

}
