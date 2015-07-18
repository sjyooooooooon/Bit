package com.bit.twitter.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

public class FollowDAO {
	boolean insertFollow(BigDecimal followerSeq, BigDecimal followeeSeq) throws Exception {
		System.out.println("insertFollow");
		String sql = "INSERT INTO t_follow VALUES(21, 1, SYSDATE)";
		Connection conn = ConnectionContext.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setBigDecimal(1, followerSeq);
		pstmt.setBigDecimal(2, followeeSeq);
		
		int result = pstmt.executeUpdate();
		if (result > 0) 
		
		pstmt.close();
		conn.close();
		return false;
	}
}
