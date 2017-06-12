package com.baizhi.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

import static org.apache.ibatis.io.Resources.getResourceAsStream;


public class MyBatisUtil {
	//重量级资源，且线程安全
	private static SqlSessionFactory sqlSessionFactory;
	//绑定资源ThreadLocal
	private static final ThreadLocal<SqlSession> TOL = new ThreadLocal<SqlSession>();
	private static InputStream ris;
	static{
		try {
			ris = getResourceAsStream("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(ris);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				ris.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 获取sqlSession的方法
	 */
	public static SqlSession getSqlSession(){
		SqlSession sqlSession = TOL.get();
		if(sqlSession==null){
			sqlSession = sqlSessionFactory.openSession();
			//放入线程中
			TOL.set(sqlSession);
		}
		return sqlSession;
	}
	
	
	/**
	 * 释放资源的方法
	 */
	public static void setFree(){
		SqlSession sqlSession = getSqlSession();
		if (sqlSession!=null) {
			sqlSession.close();
			TOL.remove();
		}
	}
	
	/**
	 * 获取mapper
	 */
	public static Object getMapper(Class clazz){
		return getSqlSession().getMapper(clazz);
	}
	
	/**
	 * 提交事务的方法
	 */
	public static void commit(){
		getSqlSession().commit();
		setFree();
	}
	
	/**
	 * 回滚事务的方法
	 */
	public static void rollback(){
		getSqlSession().rollback();
		setFree();
	}
}

