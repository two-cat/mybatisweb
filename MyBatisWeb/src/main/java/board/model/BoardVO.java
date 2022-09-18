package board.model;

import java.sql.Date;

public class BoardVO {
	private int idx;
	private String name;
	private String subject;
	private String content;
	private int readnum;
	private String filename;//Ã·ºÎÆÄÀÏ¸í
	private long filesize;//Ã·ºÎÆÄÀÏÅ©±â
	private java.sql.Date wdate;
	
	//´ñ±Û¼ö
	private int re_cnt;
	
	public BoardVO() {
		
	}

	public BoardVO(int idx, String name, String subject, String content, 
			int readnum, String filename, long filesize,
			Date wdate) {
		super();
		this.idx = idx;
		this.name = name;
		this.subject = subject;
		this.content = content;
		this.readnum = readnum;
		this.filename = filename;
		this.filesize = filesize;
		this.wdate = wdate;
	}
	//setter, getter---------------

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadnum() {
		return readnum;
	}

	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public java.sql.Date getWdate() {
		return wdate;
	}

	public void setWdate(java.sql.Date wdate) {
		this.wdate = wdate;
	}

	public int getRe_cnt() {
		return re_cnt;
	}

	public void setRe_cnt(int re_cnt) {
		this.re_cnt = re_cnt;
	}

	
}///////////////////////////////////////
