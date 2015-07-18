package com.bit.twitter.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.twitter.dao.MemberDAO;
import com.bit.twitter.model.Member;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author bit-user
 * "/login.do" 요청 처리용 Action
 */
public class LoginAction implements ICommandAction {

	@Override
	public String processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		System.out.println("LoginAction.processRequest()");
		if (request.getMethod().toUpperCase().equals("POST")) {
			// request parameters
			String loginId = request.getParameter("login_id");
			String loginPw = request.getParameter("login_pw");
			
			// DB조회
			Member member = MemberDAO.selectMemberInfo(loginId, loginPw);
			
			
			if (member != null) { // 로그인 성공
				response.setContentType("application/json");

				request.getSession().setAttribute("memberInfo", member);
				
				PrintWriter writer = response.getWriter(); // 응답 메시지 바디로의 출력 스트림
				/*writer.append("{");
				writer.append("\"memberSeq\":" + member.getMemberSeq());
				writer.append(", \"nickname\":\"" + member.getNickname() + "\"");
				writer.append(", \"name\":\"" + member.getName() + "\"");
				writer.append(", \"email\":\"" + member.getEmail() + "\"");
				writer.append("}");*/
				Gson gson = new GsonBuilder().create();
				writer.append(gson.toJson(member));
				
				
			} else {              // 로그인 실패
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 응답코드: 401
			}
		}
		// 응답을 jsp에서 받으니 json 으로 보내면 javascript로 처리하기 편함
		return null;
	}

}
