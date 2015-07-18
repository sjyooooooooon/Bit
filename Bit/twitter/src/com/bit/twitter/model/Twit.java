package com.bit.twitter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Twit implements Serializable {
	private BigDecimal twitSeq;
	private BigDecimal writerSeq;
	private String content;
	private Date regDtm;
	private Member member;
	public Twit() {}
	
	public Twit(BigDecimal twitSeq, BigDecimal writerSeq, String content,
			Date regDtm) {
		this.twitSeq = twitSeq;
		this.writerSeq = writerSeq;
		this.content = content;
		this.regDtm = regDtm;
	}
	
	public Twit(BigDecimal twitSeq, BigDecimal writerSeq, String content,
			Date regDtm, Member member) {
		this.twitSeq = twitSeq;
		this.writerSeq = writerSeq;
		this.content = content;
		this.regDtm = regDtm;
		this.member = member;
	}
	
	public BigDecimal getTwitSeq() {
		return twitSeq;
	}
	public void setTwitSeq(BigDecimal twitSeq) {
		this.twitSeq = twitSeq;
	}
	public BigDecimal getWriterSeq() {
		return writerSeq;
	}
	public void setWriterSeq(BigDecimal writerSeq) {
		this.writerSeq = writerSeq;
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
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
}
