package com.bit.twitter.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionContext {
	private static DataSource ds; // 커넥션 풀

	public static Connection getConnection() throws SQLException, NamingException {
		// 톰캣이 웹애플리케이션을 로딩할 때 META-INF/context.xml을 읽어서
		// Resource name="jdbc/ora"
		// type="javax.sql.DataSource" 형의 풀을 jdbc/ora라는 이름의 리소스로 등록 
		if (ds == null ) {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/ora");  //java:comp/env/   -> 이 경로에 등록된다. lookup으로 ds객체를 불러
			// JNDI(Java Naming and Directory Interface)는 디렉터리 서비스에서 제공하는 데이터 및 객체를 발견(discover)하고 참고(lookup)하기 위한 자바 API다.
		}
		return ds.getConnection();
	}
}
