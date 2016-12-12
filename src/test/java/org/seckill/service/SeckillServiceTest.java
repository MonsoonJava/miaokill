package org.seckill.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.exception.RepeatSeckillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.pojo.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-config.xml",
    "classpath:spring/spring-config-service.xml"
})
public class SeckillServiceTest {
    
    private static Logger logger = LoggerFactory.getLogger(SeckillServiceTest.class);
    
    @Autowired
    private SeckillService seckillService;
    
    @Test
    public void testGetAllSeckill() {
        List<Seckill> allSeckill = seckillService.getAllSeckill();
        for (Seckill seckill : allSeckill) {
            logger.info("seckill={}", seckill);
        }
    }

    @Test
    public void testGetSeckillById() {
        Seckill seckill = seckillService.getSeckillById(1000);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillLogic() {
        try {
            Exposer exposer = seckillService.exportSeckillUrl(1000);
            logger.info("exposer={}", exposer);
            if(exposer.isExposed()){
                SeckillExcution executeSeckill = seckillService.ExecuteSeckill(1000, 18768147051L, exposer.getMd5());
                logger.info("executeSeckill={}", executeSeckill);
            }else{
                logger.warn("seckill is not on starting");
            }
        } catch (RepeatSeckillException e) {
            logger.error(e.getMessage());
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        } 
    }

}
