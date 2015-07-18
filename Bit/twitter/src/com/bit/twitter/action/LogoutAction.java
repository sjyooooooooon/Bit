package com.bit.twitter.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements ICommandAction {

	@Override
	public String processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		//request.getSession().removeAttribute("memberInfo");  // "memberInfo" 어트리뷰트를 기준으로 로그인 여부를 판단했기 때문에 가능
		request.getSession().invalidate();
		return null;
	}

}
