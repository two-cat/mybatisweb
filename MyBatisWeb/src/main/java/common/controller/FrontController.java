package common.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(
		urlPatterns = { "*.ict" }, 
		initParams = { 
				@WebInitParam(name = "config", 
						value = "C:\\Users\\�迹��\\git\\mybatisweb\\MyBatisWeb\\src\\main\\webapp\\WEB-INF\\Command.properties")
		})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Object> cmdMap = new HashMap<>();
	//Command.properties���Ͽ� ����� ������ �ؽ��ʿ� �ű� ����
	private Properties props =new Properties();
	
	public void init(ServletConfig conf) throws ServletException {
		System.out.println("init()ȣ���...");
		String fileName=conf.getInitParameter("config");
		System.out.println("fileName==="+fileName);
		try {
			FileReader fr=new FileReader(fileName);
			props.load(fr);
			//Command.properties������ ������ Properties��ü�� �ű��.
			if(fr!=null) fr.close();
			//System.out.println(props.getProperty("/index.do"));
			
			Set<Object> set=props.keySet();
			//key������ ��ȯ
			for(Object key:set) {
				String cmd = key.toString(); //=> /index.do
				String className = props.getProperty(cmd);//=> common.controller.IndexAction
				System.out.println(cmd+": "+className);
				if(className!=null) {
					className = className.trim();//�յ� ���鹮�� ����
				}
				//className�� �ν��Ͻ�ȭ
				Class<?> cls=Class.forName(className);
				Object cmdInstance = cls.newInstance();
				//���ڿ�(Ŭ�����̸�)�� ���� ��ü�� �޸𸮿� �÷��ش�.
				/////////////////////////////////
				cmdMap.put(cmd, cmdInstance);
				////////////////////////////
			}//for------------------
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		process(request, response);
	}

	public void process(HttpServletRequest req, HttpServletResponse res)
	throws IOException,ServletException{
		// 1. Ŭ���̾�Ʈ�� ��û URI�� �м��ؼ� �ش� ��û�� ó���� SubController(XXXAction) �� �����ϰ�
		// execute()�� ȣ���Ѵ�.
		String uri=req.getRequestURI();
		System.out.println("uri: "+uri);//"/MVCWeb/index.do
		//���ؽ�Ʈ�� ���ϱ�
		String ctx = req.getContextPath();// "/MVCWeb"
		//substring(int index)
		int len =ctx.length();
		String cmd = uri.substring(len);
		System.out.println("cmd: "+cmd);//"/index.do"
		/////////////////////////////////////////
		Object instance = cmdMap.get(cmd);
		if(instance==null) {
			System.out.println("Action�� null");
			throw new ServletException("Action�� null�Դϴ�");
		}
		AbstractAction action =(AbstractAction)instance;
		////////////////////////////////////////
		
		try {
			///////////////////////
			action.execute(req, res);//������Ʈ�ѷ��� ������ ���� �޼ҵ� ȣ��
			String viewPage=action.getViewPage();
			boolean isRedirect = action.isRedirect();
			//////////////////////
			if(isRedirect) {
				//redirect�� �̵�
				res.sendRedirect(viewPage);
				//���ο� request�� �߻����Ѽ� viewPage�� �̵��Ѵ�
			}else {
				//forward�� �̵�
				RequestDispatcher disp = req.getRequestDispatcher(viewPage);
				disp.forward(req, res);
				//���� ���ο��� �̵���. �̶� request, response�� ������
			}
		} catch (Exception e) {
 
			e.printStackTrace();
			throw new ServletException(e);
		}
		
	}//process()-------------------------
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
