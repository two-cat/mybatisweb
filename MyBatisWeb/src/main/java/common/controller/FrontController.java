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
						value = "C:\\Users\\김예린\\git\\mybatisweb\\MyBatisWeb\\src\\main\\webapp\\WEB-INF\\Command.properties")
		})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Object> cmdMap = new HashMap<>();
	//Command.properties파일에 저장된 값들을 해쉬맵에 옮길 예정
	private Properties props =new Properties();
	
	public void init(ServletConfig conf) throws ServletException {
		System.out.println("init()호출됨...");
		String fileName=conf.getInitParameter("config");
		System.out.println("fileName==="+fileName);
		try {
			FileReader fr=new FileReader(fileName);
			props.load(fr);
			//Command.properties파일의 내용을 Properties객체로 옮긴다.
			if(fr!=null) fr.close();
			//System.out.println(props.getProperty("/index.do"));
			
			Set<Object> set=props.keySet();
			//key값들을 반환
			for(Object key:set) {
				String cmd = key.toString(); //=> /index.do
				String className = props.getProperty(cmd);//=> common.controller.IndexAction
				System.out.println(cmd+": "+className);
				if(className!=null) {
					className = className.trim();//앞뒤 공백문자 제거
				}
				//className을 인스턴스화
				Class<?> cls=Class.forName(className);
				Object cmdInstance = cls.newInstance();
				//문자열(클래스이름)을 실제 객체로 메모리에 올려준다.
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
		// 1. 클라이언트의 요청 URI를 분석해서 해당 요청을 처리할 SubController(XXXAction) 를 생성하고
		// execute()를 호출한다.
		String uri=req.getRequestURI();
		System.out.println("uri: "+uri);//"/MVCWeb/index.do
		//컨텍스트명 구하기
		String ctx = req.getContextPath();// "/MVCWeb"
		//substring(int index)
		int len =ctx.length();
		String cmd = uri.substring(len);
		System.out.println("cmd: "+cmd);//"/index.do"
		/////////////////////////////////////////
		Object instance = cmdMap.get(cmd);
		if(instance==null) {
			System.out.println("Action이 null");
			throw new ServletException("Action이 null입니다");
		}
		AbstractAction action =(AbstractAction)instance;
		////////////////////////////////////////
		
		try {
			///////////////////////
			action.execute(req, res);//서브컨트롤러의 로직을 갖는 메소드 호출
			String viewPage=action.getViewPage();
			boolean isRedirect = action.isRedirect();
			//////////////////////
			if(isRedirect) {
				//redirect로 이동
				res.sendRedirect(viewPage);
				//새로운 request를 발생시켜서 viewPage로 이동한다
			}else {
				//forward로 이동
				RequestDispatcher disp = req.getRequestDispatcher(viewPage);
				disp.forward(req, res);
				//서버 내부에서 이동함. 이때 request, response를 가져감
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
