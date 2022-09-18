package reply.model;

import java.sql.Date;

public class ReplyVO {
	private int re_idx;
	private String re_name;
	private String re_content;
	private int re_readnum;
	private java.sql.Date re_date;
	private int idx_fk;
	
	public ReplyVO() {
		
	}

	public ReplyVO(int re_idx, String re_name, String re_content, int re_readnum, Date re_date, int idx_fk) {
		super();
		this.re_idx = re_idx;
		this.re_name = re_name;
		this.re_content = re_content;
		this.re_readnum = re_readnum;
		this.re_date = re_date;
		this.idx_fk = idx_fk;
	}

	public int getRe_idx() {
		return re_idx;
	}

	public void setRe_idx(int re_idx) {
		this.re_idx = re_idx;
	}

	public String getRe_name() {
		return re_name;
	}

	public void setRe_name(String re_name) {
		this.re_name = re_name;
	}

	public String getRe_content() {
		return re_content;
	}

	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}

	public int getRe_readnum() {
		return re_readnum;
	}

	public void setRe_readnum(int re_readnum) {
		this.re_readnum = re_readnum;
	}

	public java.sql.Date getRe_date() {
		return re_date;
	}

	public void setRe_date(java.sql.Date re_date) {
		this.re_date = re_date;
	}

	public int getIdx_fk() {
		return idx_fk;
	}

	public void setIdx_fk(int idx_fk) {
		this.idx_fk = idx_fk;
	}
}///////////////////////////////
