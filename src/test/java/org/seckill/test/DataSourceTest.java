package org.seckill.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class DataSourceTest {

    
    @Test
    public void startAnaliys() throws IOException{
        String mybatisConfigPath = "mybatis/mybatis-config-test.xml";
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigPath);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //int count = (Integer)sqlSession.selectOne("org.alien.mybatis.samples.mapper.AuthorMapper.getAllAuthorsCount");
        //System.out.println(count);
    }
    
    
    
}
