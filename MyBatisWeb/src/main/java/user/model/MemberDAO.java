package user.model;
import java.sql.*;
import java.util.*;
import jdbc.util.*;
public class MemberDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public MemberDAO() {
		System.out.println("MemberDAO()������...");
	}
	
	public boolean idCheck(String userid) throws SQLException{
		try {
			con = DBUtil.getCon();
			StringBuilder buf=new StringBuilder("select idx from jsp_member ")
					.append(" where userid =?");
			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			boolean b = rs.next();
			//b=>true�̸� �ش� ���̵�� �����Ѵٴ� �ǹ�
			//   false�� �ش� ���̵�� ����					
			return !b;
		} finally {
			close();
		}
	}//--------------------------------------
	
	
	public int insertMember(MemberVO user) throws SQLException{
		try {
			con = DBUtil.getCon();		
		//StringBuffer => StringBuilder 
		StringBuilder buf = new StringBuilder("insert into jsp_member ");
		buf.append(" values(jsp_member_seq.nextval,?,?,?,?,?,?,?,?,?,1000,sysdate)");
					
		String sql=buf.toString();
		ps=con.prepareStatement(sql);
		ps.setString(1, user.getName());
		ps.setString(2, user.getUserid());
		ps.setString(3, user.getPwd());
		ps.setString(4, user.getHp1());
		ps.setString(5, user.getHp2());
		ps.setString(6, user.getHp3());
		ps.setString(7, user.getZipcode());
		ps.setString(8, user.getAddr1());
		ps.setString(9, user.getAddr2());
		
		return ps.executeUpdate();
			
		} finally {
			close();
		}		
	}//--------------------------------------
	public List<MemberVO> listMember() throws SQLException{
		try {
			con = DBUtil.getCon();
			StringBuilder buf=new StringBuilder("select idx, name, userid, pwd, ")
					.append(" hp1, hp2, hp3, zipcode, addr1, addr2, mileage, indate from jsp_member ")
					.append(" order by idx desc");
			String sql=buf.toString();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			List<MemberVO> arr = makeList(rs);
			return arr;
		}finally {
			close();
		}
	}//--------------------------------------
	public List<MemberVO> findMember(String type, String keyword) throws SQLException{
		try {
			con = DBUtil.getCon();
			String colName="name";
			switch(type) {
			case "1": colName="name"; break;
			case "2": colName="userid"; break;
			case "3": colName="hp1||hp2||hp3";break;
			}
			String sql="select * from jsp_member where "+colName+" like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			rs = ps.executeQuery();			
			List<MemberVO> arr = makeList(rs);
			return arr;
		} finally {
			close();
		}
	}//--------------------------------------
	
	public List<MemberVO> makeList(ResultSet rs) throws SQLException{
		List<MemberVO> arr = new ArrayList<>();
		while(rs.next()) {
			int idx = rs.getInt("idx");
			String name = rs.getString("name");
			String userid = rs.getString("userid");
			String pwd = rs.getString("pwd");
			String hp1 = rs.getString("hp1");
			String hp2 = rs.getString("hp2");
			String hp3 = rs.getString("hp3");
			String zipcode = rs.getString("zipcode");
			String addr1 = rs.getString("addr1");
			String addr2 = rs.getString("addr2");
			int mileage = rs.getInt("mileage");
			java.sql.Date indate = rs.getDate("indate"); 
			MemberVO vo =new MemberVO(idx, name,userid,pwd,hp1,hp2,hp3,
					zipcode, addr1,addr2,indate,mileage );
			arr.add(vo);
		}
		return arr;
	}//-----------------------------------
	
	public int deleteMember(int idx) throws SQLException{
		try {
			con= DBUtil.getCon();
			String sql="delete from jsp_member where idx=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, idx);			
			return ps.executeUpdate();
		} finally {
			close();
		}
	}//--------------------------------------
	//ȸ����ȣ(idx-pk)�� ȸ������ �������� �޼ҵ�
	public MemberVO selectMemberByIdx(int idx) throws SQLException{
		try {
			con = DBUtil.getCon();
			String sql="select * from jsp_member where idx=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, idx);
			rs = ps.executeQuery();
			List<MemberVO> arr = makeList(rs);
			if(arr!=null && arr.size()==1) {
				MemberVO user = arr.get(0);
				return user;
			}
			return null;			
		} finally {
			close();
		}
	}//--------------------------------------
	public int updateMember(MemberVO user) throws SQLException{
		try {
			con = DBUtil.getCon();
			StringBuilder buf 
			= new StringBuilder("update jsp_member set name=?,userid=?, pwd= ?, ")
					.append(" hp1=?,hp2=?, hp3=?, zipcode=?, addr1=?, addr2=?")
					.append(", mileage=?")
					.append(" where idx = ?");
			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getUserid());
			ps.setString(3, user.getPwd());
			ps.setString(4, user.getHp1());
			ps.setString(5, user.getHp2());
			ps.setString(6, user.getHp3());
			ps.setString(7, user.getZipcode());
			ps.setString(8, user.getAddr1());
			ps.setString(9, user.getAddr2());
			ps.setInt(10, user.getMileage());
			ps.setInt(11, user.getIdx());
			return ps.executeUpdate();
		} finally {
			close();
		}
	}//---------------------------------------
	
	/**�α��� ȸ���� üũ�ϴ� �޼ҵ�*/
	public MemberVO loginCheck(String userid, String pwd) 
			throws SQLException, NotMemberException{		
		MemberVO user = this.selectMemberByUserid(userid);
		if(user == null) {
			//���̵� ���� ��� ==> ���� �߻�
			throw new NotMemberException("���̵� �������� �ʽ��ϴ�");
		}
		//���̵� �ִٸ� ==> ��й�ȣ üũ
		if(!pwd.equals(user.getPwd())) {
			throw new NotMemberException("��й�ȣ�� ��ġ���� �ʾƿ�");
		}
		//���̵�� ����� ��ġ�ϴٸ�
		return user;
	}//------------------------------------
	public MemberVO selectMemberByUserid(String userid)
			throws SQLException{
		try {
			con = DBUtil.getCon();
			String sql="select * from jsp_member where userid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			List<MemberVO> arr = makeList(rs);
			if(arr!=null && arr.size()==1) {
				MemberVO user = arr.get(0);
				return user;
			}
			return null;
		}finally {
			close();
		}		
	}//------------------------------------
	
	
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
