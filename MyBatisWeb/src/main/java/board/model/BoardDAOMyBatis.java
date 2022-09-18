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
		//디폴트가 수동 커밋. ...openSession() => transaction처리를 수동으로 해주어야 함
		//자동커밋을 하고 싶다면 ...openSession(true) :true값을 넣어주면 자동 커밋이 됨
		try(SqlSession ses=this.getSessionFactory().openSession(true)){
			int n=ses.insert(NS+".insertBoard", vo);
			/*
			 * if(n>0) { ses.commit(); }else { ses.rollback(); }
			 */
			return n;
		}
		/* try(){}을 이용하면 알아서 try()안에 있는 리소스들의 자원반납을 자동으로 해줌 */		
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
	
	//R-Read: select문=> idx로 가져오기 => selectOne()메소드 사용 
	public BoardVO selectBoardByIdx(int idx) {
		try(SqlSession ses = this.getSessionFactory().openSession(true)){
			BoardVO vo = ses.selectOne(NS+".selectBoardByIdx", idx);
			return vo;
		}
		
	}//--------------------------

	//U-Upadte: 조회수 증가
	public int updateReadnum(int idx) {
		try(SqlSession ses = this.getSessionFactory().openSession(true)){
			
			int n = ses.update(NS+".updateReadnum", idx);
			return n;
		}		
	}//--------------------------

	//트랜잭션 처리를 수동으로 하자. ==>openSession(false)로 주어야 수동 처리됨
	public int deleteBoard(int idx) {
		try(SqlSession ses = this.getSessionFactory().openSession(false)){
			//댓글이 있는 먼저 확인하자
			int cnt = ses.selectOne(NS+".getReplyCountByIdx", idx);
			if(cnt>0) {			
			//댓글 먼저 삭제 처리
			int n1 = ses.delete(NS+".deleteReplyAllByIdx", idx);
			if(n1>0) {
				//댓글 삭제를 성공했다면 부모글 삭제 처리
				int n = ses.delete(NS+".deleteBoard", idx);
				if(n>0) {
					ses.commit();//모두 삭제되면 commit()
				}else {
					ses.rollback();//부모글 삭제를 실패하면 모두 취소 rollback()
				}				
			}else {
				//댓글 삭제를 실패했다면 rollback();
				ses.rollback();
			}
			return n1;
			}else {
				//댓글이 없다면=> 부모글 삭제 처리
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





