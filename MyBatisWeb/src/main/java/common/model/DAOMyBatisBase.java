package common.model;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.*;
import java.io.*;

public class DAOMyBatisBase {
	
	private String resource="common/config/mybatis-config.xml";
	protected SqlSession ses;
	
	public SqlSessionFactory getSessionFactory() {
		
		try {
			InputStream is =Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder
			=new SqlSessionFactoryBuilder();
			
			SqlSessionFactory fac=builder.build(is);
			return fac;
		} catch (IOException e) {			
			e.printStackTrace();
			return null;
		}
	}

}
