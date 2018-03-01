package HttpClientDemo;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CreateConnection {

	private final String resource = "conf.xml"; 
	private  Reader reader=null;
	private SqlSessionFactory sessionFactory = null;
	private SqlSession mysession = null;

	public  CreateConnection(){	   
		//加载mybatis的配置文件（它也加载关联的映射文件）
		try{
			reader = Resources.getResourceAsReader(resource);
			System.out.println(reader);
		}catch (Exception e){
			e.printStackTrace();
		}
		//或者加载资源文件
		//InputStream is = Test.class.getClassLoader().getResourceAsStream(resource);
		//构建sqlSession的工厂
		sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//创建能执行映射文件中sql的sqlSession
		mysession = sessionFactory.openSession();		

	}


	public SqlSession getSqlSession(){				
		return mysession;
	}

}
