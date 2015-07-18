package com.bit.twitter.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.twitter.dao.TwitDAO;
import com.bit.twitter.model.Member;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WriteAction implements ICommandAction {

	@Override
	public String processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");// 리퀘스트 파라미터에 한글이 섞여있을 수 있기때문에
		
		// POST만 처리하도록 필터링
		if (request.getMethod().toUpperCase().equals("POST")) {
			// 로그인 상태 체크
			Member member = (Member) request.getSession().getAttribute("memberInfo");
			if (member != null) {
				// DB에 INSERT
				String content = request.getParameter("content");
				if (content == null || content.trim().equals("")) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
				} else {
					boolean result = TwitDAO.insertTwit(member.getMemberSeq(), content) > 0;
					
					Gson gson = new GsonBuilder().create();
					
					response.setContentType("applicaiton/json");
					response.getWriter().append(gson.toJson(result));
				}
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
			}
		}
		return null;
	}

}
