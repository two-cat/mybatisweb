package reply.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.model.DAOMyBatisBase;

public class ReplyDAOMyBatis extends DAOMyBatisBase{
	
	private final String NS="common.mapper.BoardMapper";
	
	public int insertReply(ReplyVO rvo) {
		try(SqlSession ses=this.getSessionFactory().openSession(true)){
			int n = ses.insert(NS+".insertReply", rvo);
			//증가된 시퀀스값 알아내기
			System.out.println("rvo.re_idx: "+rvo.getRe_idx());
			return n;
		}
	}//------------------------------------
	public List<ReplyVO> listReply(int idx_fk) {
		try(SqlSession ses=this.getSessionFactory().openSession()){
			List<ReplyVO> arr=ses.selectList(NS+".listReply", idx_fk );
			return arr;
		}
	}//------------------------------------
	
	public int deleteReply(int re_idx) {
		try(SqlSession ses = this.getSessionFactory().openSession(true)){
			int n = ses.delete(NS+".deleteReply", re_idx);
			return n;
		}
	}//------------------------------------

}/////////////////////////////////


