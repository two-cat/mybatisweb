package common.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class TestDAOMyBatis {

	// � ���۸� ������� ���ӽ����̽��� �����ؾ� ��
	// TestMapper.xml�� ������ namespace���� ��ġ�ؾ� ��
	private final String NS = "common.mapper.TestMapper";
	private SqlSession ses;

	// �������丮�� ��� �޼ҵ� ����
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
	
	/**c##scott������ ���̺� �� ��������*/
	public String getTotalCount() {
		ses = this.getSessionFactory().openSession();
		String count = ses.selectOne(NS+".getTotalCount");
		if(ses!=null) ses.close();		
		return count;		
	}//------------------------------------
	
	

}
