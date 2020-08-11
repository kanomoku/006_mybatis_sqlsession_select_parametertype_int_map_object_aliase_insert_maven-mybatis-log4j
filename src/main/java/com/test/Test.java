package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.pojo.People;

public class Test {
	public static void main(String[] args) {
		try {
			InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
			SqlSession sqlSession = sqlSessionFactory.openSession();
			
			Logger logger = Logger.getLogger(Test.class);

/*			logger.info("--------------------------------无参数查询");
			List<People> list = sqlSession.selectList("a.b.c");
			for (People people : list) {
				System.out.println(people);
			}*/

//			2.2 在Mapper.xml 中可以通过#{}获取参数
//				2.2.1 parameterType 控制参数类型
//				2.2.2 #{}获取参数内容
//				2.2.2.1 使用索引,从0 开始#{0}表示第一个参数
//				2.2.2.2 也可以使用#{param1}第一个参数
//				2.2.2.3 如果只有一个参数(基本数据类型或String),mybatis对#{}里面内容没有要求只要写内容即可.
//				2.2.2.4 如果参数是对象#{属性名}
//				2.2.2.5 如果参数是map 写成#{key}
/*			logger.info("-------------------------------参数为int类型     #{0} #{param1}");
			People p = sqlSession.selectOne("a.b.selById", 2);
			System.out.println(p);*/
			
//			3. #{} 和${} 的区别
//				3.1 #{} 获取参数的内容支持索引获取,param1 获取指定位置参数,并且SQL 使用?占位符
//				3.2 ${} 字符串拼接不使用?,默认找${内容}内容的get/set 方法,如果写数字,就是一个数字
			
/*			logger.info("-------------------------------参数为int类型  ${2}是直接拼接字符串,   把2当参数值传进去");
			People p = sqlSession.selectOne("a.b.selById2", 2);
			System.out.println(p);*/

/*			logger.info("--------------------参数为对象类型    #{id}");
			People p5 = new People();
			p5.setId(1);
			p5.setName("张三");
			People p6 = sqlSession.selectOne("a.b.selById4", p5);
			System.out.println(p6);*/
			
/*			logger.info("--------------------参数为对象类型    ${id}");
			People p3 = new People();
			p3.setId(1);
			p3.setName("张三");
			People p4 = sqlSession.selectOne("a.b.selById3", p3);
			System.out.println(p4);*/

/*			logger.info("--------------------参数为map类型  需要用 #{}来取里面的值");
			Map<String, Object> map = new HashMap<>();
			map.put("id", 1);
			map.put("name", "张三");
			People p7 = sqlSession.selectOne("a.b.selById5", map);
			System.out.println(p7);*/

			// 分页查询
			logger.info("--------------------参数为map类型  需要用 #{}来取里面的值");
			int pageSize = 2;
			int pageNum = 2;
			Map<String, Object> mapPage = new HashMap<>();
			mapPage.put("pageStart", pageSize * (pageNum - 1));
			mapPage.put("pageSize", pageSize);
			List<People> peopleList = sqlSession.selectList("a.b.page", mapPage);
			for (People people : peopleList) {
				System.out.println(people);
			}
			
/*			logger.info("--------------------insert 参数对象类型  需要用 #{}来取里面的值");
			People p8 = new People();
			p8.setName("新增");
			p8.setAge(18);
			sqlSession.insert("a.b.insert", p8);*/
			
			logger.info("--------------------update");
			People p9 = new People();
			p9.setName("王五");
			p9.setAge(14);
			sqlSession.update("a.b.update", p9);

			sqlSession.commit();
			sqlSession.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
