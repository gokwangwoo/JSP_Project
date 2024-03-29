package com.newlecture.web.entity;

import java.sql.Date;

public class Notice {
	
	private int id;
	private String title;
	private Date regdate;
	private String writerId;
	private String hit;
	private String files;
	private String content;
	
	//기본 생성자 추가
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	//각 요소들(id, title ...)에 대한 생성자를 생성
	public Notice(int id, String title, Date regdate, String writerId, String hit, String files, String content) {
		this.id = id;
		this.title = title;
		this.regdate = regdate;
		this.writerId = writerId;
		this.hit = hit;
		this.files = files;
		this.content = content;
	}
	//모든 요소들에 대해 Setter, getter를 생성
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	//이제 SQL에서 받아온 값을 String으로 바꿔주는 함수가 필요하다
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", regdate=" + regdate + ", writerId=" + writerId + ", hit="
				+ hit + ", files=" + files + ", content=" + content + "]";
	}
	
	
}
