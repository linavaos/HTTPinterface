package Demo;

import org.apache.ibatis.session.SqlSession;

import HttpClientDemo.SqlSessionUntil;

public class Demo1 {

	public static void main(String[] args) {

		User a = new User();
		a.setAge(20);
		//123444
		a.setName("zhangsan");
		SqlSession sqlSession = SqlSessionUntil.SqlSession("testconf.xml");

		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try{
			int i = userMapper.addUser(a);
			sqlSession.commit();
		}catch(Exception e){
			System.out.println(e);
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}


	}

}
