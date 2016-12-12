package org.seckill.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.pojo.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit去加载spring的配置文件初始化spring
@ContextConfiguration("classpath:spring/spring-config.xml")
public class SuccessKilledDaoTest {
    
    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() {
        int insertNumber = successKilledDao.insertSuccessKilled(1001, 13362936139L);
        System.out.println("插入的数据条目数" + insertNumber);
    }

    @Test
    public void testQuerySuccessKilledById() {
        SuccessKilled successKill = successKilledDao.querySuccessKilledById(1001, 13362936139L);
        System.out.println(successKill);
    }

}
