package org.seckill.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.pojo.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit去加载spring的配置文件初始化spring
@ContextConfiguration("classpath:spring/spring-config.xml")
public class SeckillDaoTest {
    
    //注入DAO接口
    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testReduceNumber() {
        Date killTime;
        try {
            killTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-12-27 00:00:01");
            int updateNumber = seckillDao.reduceNumber(1000,killTime);
            System.out.println("更新的数据:  " + updateNumber);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuerySeckillbyID() {
        Seckill seckill = seckillDao.querySeckillbyID(1000);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }
    

    @Test
    public void testQueryAll() {
        List<Seckill> seckillList = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckillList) {
           System.out.println(seckill); 
        }
    }

}
