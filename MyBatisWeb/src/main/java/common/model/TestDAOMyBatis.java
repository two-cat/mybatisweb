package common.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class TestDAOMyBatis {

	// 어떤 매퍼를 사용할지 네임스페이스를 지정해야 함
	// TestMapper.xml에 설정된 namespace값과 일치해야 함
	private final String NS = "common.mapper.TestMapper";
	private SqlSession ses;

	// 세션팩토리를 얻는 메소드 구성
	private SqlSessionFactory getSessionFactory() {
		String resource = "common/config/mybatis-config.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(is);
		return fac;
	}// ----------------------------------
	
	/**c##scott계정의 테이블 수 가져오기*/
	public String getTotalCount() {
		ses = this.getSessionFactory().openSession();
		String count = ses.selectOne(NS+".getTotalCount");
		if(ses!=null) ses.close();		
		return count;		
	}//------------------------------------
	
	

}
