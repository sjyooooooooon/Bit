package com.bit.twitter.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.twitter.action.ICommandAction;


public class MainController extends HttpServlet {

	private Map<String, Object> commandMap = new HashMap<>();  //properties파일에서 읽어오기 위해서 Map

	// web.xml을 읽어서 메모리에 로드할때 톰캣이 호출, 리퀘스트가 오지 않아도 호출됨
	@Override
	public void init() throws ServletException {
		loadProperties("com/bit/twitter/properties/Command");
		
		super.init();
	}

	private void loadProperties(String path) {
		ResourceBundle rb = ResourceBundle.getBundle(path); //web.xml의 리소스 객체를 가져오기 위해
		Enumeration<String> actions = rb.getKeys();
		
		while (actions.hasMoreElements()) {
			String command = actions.nextElement(); // properties의 URI들
			String className = rb.getString(command);// command에 대한 값

			try { 
				Class commandClass = Class.forName(className);  // Class : 클래스의 정보를 가짐
				Object commandInstance = commandClass.newInstance();  //객체 인스턴스화
				
				commandMap.put(command, commandInstance);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Request URI에 따라서 알맞은 CommandAction 객체로 그 처리를 위임
		//톰캣이 만든 req와 resp
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Request URI에 따라서 알맞은 CommandAction 객체로 그 처리를 위임
		process(req, resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// doGet()과 doPost()에서 동일한 함수를...호출?
		// commandAction이 리턴하는 스트링: 뷰의 이름
		
		String view = null;
		ICommandAction action = null;
		
		try {
			String command = req.getRequestURI();  //will return /mvc/login.do
			if (command.indexOf(req.getContextPath()) == 0) {
				command = command.substring(req.getContextPath().length());  // /mvc 이후의 문자열 리턴
			}
			
			action = (ICommandAction) commandMap.get(command);
			
			if (action == null) {
				System.out.println("ACTION NOT FOUND");
				return;
			}
			System.out.println("action: " + action);
			view = action.processRequest(req, resp);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if (view == null) {
			return;
		}
		
		// action이 리턴한 view를 dispatcher가 찾아서 req와 resp를 보냄
		RequestDispatcher dispatcher = req.getRequestDispatcher(view); 
		/*
		 * RequestDispatcher: 
		 * Defines an object that receives requests from the client and sends them to any resource 
		 * (such as a servlet, HTML file, or JSP file) on the server. 
		 * The servlet container creates the RequestDispatcher object, which is used as a wrapper around a server resource 
		 * located at a particular path or given by a particular name.
		 */
		dispatcher.forward(req, resp);  // Forwards a request from a servlet to another resource (servlet, JSP file, or HTML file) on the server.
	}
}
