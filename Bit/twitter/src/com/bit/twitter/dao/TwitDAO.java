package com.bit.twitter.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bit.twitter.model.Timeline;

public class TwitDAO {
	public static int insertTwit(BigDecimal memberSeq, String content) throws Exception {
		String sql = "INSERT INTO t_twit (twit_seq, writer_seq, content) "
				+ "VALUES(seq_twit.NEXTVAL, ?, ?)";
		Connection conn = ConnectionContext.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setBigDecimal(1, memberSeq);
		pstmt.setString(2, content);
		
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	/**
	 * 전체 타임라인 조회
	 * @throws Exception 
	 */
	public static List<Timeline> selectTwit() throws  Exception {
		System.out.println("selectTwit()");
		List<Timeline> list = new ArrayList<>();
		//String sql = "SELECT * FROM t_twit WHERE rownum < 11 ORDER BY twit_seq DESC";
		String sql = "SELECT "
				+ "	A.member_seq"
				+ "	, B.writer_seq"
				+ " , A.email"
				+ "	, A.name"
				+ "	, A.nickname"
				+ "	, B.rnum"
				+ "	, B.twit_seq"
				+ "	, B.content"
				+ "	, B.reg_dtm"
				+ " FROM t_member A"
				+ "	, ("
				+ "    SELECT /*+INDEX_DESC(t_twit PK_TWIT)*/"
				+ "		rownum AS RNUM"
				+ "		, twit_seq"
				+ "		, writer_seq"
				+ "		, content"
				+ "		, reg_dtm"
				+ "      FROM t_twit ) B"
				+ " WHERE "
				+ "	A.member_seq = B.writer_seq";
		
		System.out.println(sql);
		Connection conn = ConnectionContext.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Timeline twit = new Timeline(
					rs.getBigDecimal("member_seq")
					, rs.getBigDecimal("writer_seq")
					, rs.getString("email")
					, rs.getString("name")
					, rs.getString("nickname")
					, rs.getBigDecimal("rnum")
					, rs.getBigDecimal("twit_seq")
					, rs.getString("content")
					, rs.getDate("reg_dtm"));
			list.add(twit);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}
	
	/**
	 * 특정 타임라인 조회
	 * @throws Exception 
	 */
	public static List<Timeline> selectTwit(String memberSeq) throws  Exception {
		System.out.println("selectTwit(String memberSeq), "+ memberSeq);
		List<Timeline> list = new ArrayList<>();
		String sql = "SELECT "
				+ "	A.member_seq"
				+ "	, B.writer_seq"
				+ " , A.email"
				+ "	, A.name"
				+ "	, A.nickname"
				+ "	, B.rnum"
				+ "	, B.twit_seq"
				+ "	, B.content"
				+ "	, B.reg_dtm"
				+ "	, CASE  WHEN (SELECT X.followee_seq from t_follow X WHERE follower_seq = ? AND X.followee_seq = B.writer_seq)IS NULL THEN 'N' "
				+ " ELSE 'Y' END   AS followStatus"
				+ "  FROM t_member A"
				+ "      , ("
				+ "    SELECT /*+INDEX_DESC(t_twit PK_TWIT)*/"
				+ "		 rownum AS RNUM"
				+ "		 , twit_seq"
				+ "      , writer_seq "
				+ "      , content "
				+ "		 , reg_dtm"
				+ "      FROM t_twit ) B"
				+ " WHERE "
				+ "    A.member_seq = B.writer_seq "
				+ "  AND B.writer_seq = ? "
				+ "  AND B.rnum >= 1"
				+ "  AND B.rnum < 10";
		System.out.println(sql);
		Connection conn = ConnectionContext.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberSeq);
		pstmt.setString(2, memberSeq);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Timeline twit = new Timeline(
					rs.getBigDecimal("member_seq")
					, rs.getBigDecimal("writer_seq")
					, rs.getString("email")
					, rs.getString("name")
					, rs.getString("nickname")
					, rs.getBigDecimal("rnum")
					, rs.getBigDecimal("twit_seq")
					, rs.getString("content")
					, rs.getDate("reg_dtm")
					, rs.getString("followStatus"));
			list.add(twit);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}
}
