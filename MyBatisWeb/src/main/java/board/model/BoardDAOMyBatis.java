package board.model;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import common.model.DAOMyBatisBase;

public class BoardDAOMyBatis extends DAOMyBatisBase{
	
	private final String NS="common.mapper.BoardMapper";
	
	public int getTotalCount(String findType, String findKeyword) {
		try {
			Map<String, String> map=new HashMap<>();
			map.put("findType", findType);
			map.put("findKeyword", findKeyword);
			
			ses=this.getSessionFactory().openSession();
			int count=ses.selectOne(NS+".getTotalCount", map);			
			return count;
		}finally {
			if(ses!=null) ses.close();
		}
	}

	//C-Create : insert
	public int insertBoard(BoardVO vo) {		
		//����Ʈ�� ���� Ŀ��. ...openSession() => transactionó���� �������� ���־�� ��
		//�ڵ�Ŀ���� �ϰ� �ʹٸ� ...openSession(true) :true���� �־��ָ� �ڵ� Ŀ���� ��
		try(SqlSession ses=this.getSessionFactory().openSession(true)){
			int n=ses.insert(NS+".insertBoard", vo);
			/*
			 * if(n>0) { ses.commit(); }else { ses.rollback(); }
			 */
			return n;
		}
		/* try(){}�� �̿��ϸ� �˾Ƽ� try()�ȿ� �ִ� ���ҽ����� �ڿ��ݳ��� �ڵ����� ���� */		
	}//--------------------------
	
	//R-Read : select
	public List<BoardVO> listBoard(int start, int end, String type, String keyword){
				
		try(SqlSession ses = this.getSessionFactory().openSession(true)){
			
			Map<String, String> map=new HashMap<>();
			map.put("start", start+"");
			map.put("end", String.valueOf(end));
			map.put("findType", type);
			map.put("findKeyword", keyword);
			
			List<BoardVO> arr = ses.selectList(NS+".listBoard", map );
			return arr;
		}
	}//--------------------------
	
	//R-Read: select��=> idx�� �������� => selectOne()�޼ҵ� ��� 
	public BoardVO selectBoardByIdx(int idx) {
		try(SqlSession ses = this.getSessionFactory().openSession(true)){
			BoardVO vo = ses.selectOne(NS+".selectBoardByIdx", idx);
			return vo;
		}
		
	}//--------------------------

	//U-Upadte: ��ȸ�� ����
	public int updateReadnum(int idx) {
		try(SqlSession ses = this.getSessionFactory().openSession(true)){
			
			int n = ses.update(NS+".updateReadnum", idx);
			return n;
		}		
	}//--------------------------

	//Ʈ����� ó���� �������� ����. ==>openSession(false)�� �־�� ���� ó����
	public int deleteBoard(int idx) {
		try(SqlSession ses = this.getSessionFactory().openSession(false)){
			//����� �ִ� ���� Ȯ������
			int cnt = ses.selectOne(NS+".getReplyCountByIdx", idx);
			if(cnt>0) {			
			//��� ���� ���� ó��
			int n1 = ses.delete(NS+".deleteReplyAllByIdx", idx);
			if(n1>0) {
				//��� ������ �����ߴٸ� �θ�� ���� ó��
				int n = ses.delete(NS+".deleteBoard", idx);
				if(n>0) {
					ses.commit();//��� �����Ǹ� commit()
				}else {
					ses.rollback();//�θ�� ������ �����ϸ� ��� ��� rollback()
				}				
			}else {
				//��� ������ �����ߴٸ� rollback();
				ses.rollback();
			}
			return n1;
			}else {
				//����� ���ٸ�=> �θ�� ���� ó��
				int n = ses.delete(NS+".deleteBoard", idx);
				if(n>0) ses.commit();
				else ses.rollback();
				return n;
			}
			
		}
	}//--------------------------

	public int updateBoard(BoardVO board) {
		try(SqlSession ses = this.getSessionFactory().openSession(true)){
			int n = ses.update(NS+".updateBoard", board);
			return n;
		}
	}//--------------------------

}





