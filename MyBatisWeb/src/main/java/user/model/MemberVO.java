package user.model;

import java.sql.Date;

public class MemberVO {
	//멤버변수: property
	private int idx;
	private String name;
	private String userid;
	private String pwd;
	private String hp1;
	private String hp2;
	private String hp3;
	private String zipcode;
	private String addr1;
	private String addr2;
	private java.sql.Date indate;
	private int mileage;
	
	public MemberVO() {
		this("","","","","","","","","");
		System.out.println("MemberVO빈 생성자...");
	}//기본생성자-----
	
	public MemberVO(String name, String userid, String pwd, String hp1, 
	String hp2, String hp3, String zipcode, String addr1, String addr2) {
		super(); 
		this.name = name;
		this.userid = userid;
		this.pwd = pwd;
		this.hp1 = hp1;
		this.hp2 = hp2;
		this.hp3 = hp3;
		this.zipcode = zipcode;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}

	public MemberVO(int idx, String name, String userid, String pwd, String hp1, String hp2, String hp3, String zipcode,
			String addr1, String addr2, Date indate, int mileage) {
		super();
		this.idx = idx;
		this.name = name;
		this.userid = userid;
		this.pwd = pwd;
		this.hp1 = hp1;
		this.hp2 = hp2;
		this.hp3 = hp3;
		this.zipcode = zipcode;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.indate = indate;
		this.mileage = mileage;
	}

	//setter, getter-------
	public void setName(String name) {
		this.name = name;
		System.out.println("setName(): "+name);
	}
	
	public String getName() {
		return name;
	}
	public void setUserid(String userid) {
		this.userid = userid;
		System.out.println("setUserid(): "+userid);
	}
	
	public String getUserid() {
		return userid;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}
	
	public String getHp1() {
		return hp1;
	}
	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}
	
	public String getHp2() {
		return hp2;
	}
	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}
	
	public String getHp3() {
		return hp3;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	
	public String getAddr1() {
		return addr1;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	
	public String getAddr2() {
		return addr2;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public java.sql.Date getIndate() {
		return indate;
	}

	public void setIndate(java.sql.Date indate) {
		this.indate = indate;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	
	public String getAllHp() {
		return hp1+"-"+hp2+"-"+hp3;
	}
	
	public String getAllAddr() {
		return "["+zipcode+"] "+addr1+" "+addr2;
	}
}
