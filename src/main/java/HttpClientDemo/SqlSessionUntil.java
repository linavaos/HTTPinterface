package HttpClientDemo;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class SqlSessionUntil {

	private static SqlSessionFactory sqlSessionFactory= null;


	private SqlSessionUntil(){

	}

	public  static SqlSessionFactory initsqlSessionFactory(String resource){

		//加载mybatis的配置文件（它也加载关联的映射文件）
		try{
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
		}catch (Exception e){
			e.printStackTrace();
		}

		return sqlSessionFactory;

	}


	public static SqlSession SqlSession(String resource){				
		return  initsqlSessionFactory(resource).openSession();
	}
}
