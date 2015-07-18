package com.bit.twitter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Timeline implements Serializable {
	private BigDecimal memberSeq;
	private BigDecimal writerSeq;
	private String email;
	private String name;
	private String nickname;
	private BigDecimal rnum;
	private BigDecimal twitSeq;
	private String content;
	private Date regDtm;
	private String followStatus;
	
	public Timeline() {}
	
	public Timeline(BigDecimal memberSeq, BigDecimal writerSeq, String email,
			String name, String nickname, BigDecimal rnum, BigDecimal twitSeq,
			String content, Date regDtm) {
		this.memberSeq = memberSeq;
		this.writerSeq = writerSeq;
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.rnum = rnum;
		this.twitSeq = twitSeq;
		this.content = content;
		this.regDtm = regDtm;
	}
	
	public Timeline(BigDecimal memberSeq, BigDecimal writerSeq, String email,
			String name, String nickname, BigDecimal rnum, BigDecimal twitSeq,
			String content, Date regDtm, String followStatus) {
		this.memberSeq = memberSeq;
		this.writerSeq = writerSeq;
		this.email = email;
		this.name = name;
		this.nickname = nickname;
		this.rnum = rnum;
		this.twitSeq = twitSeq;
		this.content = content;
		this.regDtm = regDtm;
		this.followStatus = followStatus;
	}
	
	public BigDecimal getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(BigDecimal memberSeq) {
		this.memberSeq = memberSeq;
	}
	public BigDecimal getWriterSeq() {
		return writerSeq;
	}
	public void setWriterSeq(BigDecimal writerSeq) {
		this.writerSeq = writerSeq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public BigDecimal getRnum() {
		return rnum;
	}
	public void setRnum(BigDecimal rnum) {
		this.rnum = rnum;
	}
	public BigDecimal getTwitSeq() {
		return twitSeq;
	}
	public void setTwitSeq(BigDecimal twitSeq) {
		this.twitSeq = twitSeq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}

	public String getFollowStatus() {
		return followStatus;
	}

	public void setFollowStatus(String followStatus) {
		this.followStatus = followStatus;
	}
	
	
}
