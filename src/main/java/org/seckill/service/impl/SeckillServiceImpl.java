package org.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.enums.SeckillStatusEnum;
import org.seckill.exception.RepeatSeckillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.pojo.Seckill;
import org.seckill.pojo.SuccessKilled;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;


@Service
public class SeckillServiceImpl implements SeckillService{

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    
    private static String salt = "afljasf0asdf09345df;.Dqe;";
    private static Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);
    
    public List<Seckill> getAllSeckill() {
        return seckillDao.queryAll(0, 100);
    }

    public Seckill getSeckillById(int seckillId) {
        return seckillDao.querySeckillbyID(seckillId);
    }

    /**
     * 秒杀的业务逻辑,如果时间在秒杀的时间范围内，将接口暴露出去，否则显示秒杀还没开始
     */
    @Transactional
    public Exposer exportSeckillUrl(int seckillId) {
        long now = System.currentTimeMillis();
        Seckill seckill = seckillDao.querySeckillbyID(seckillId);
        if( null == seckill ){
            return new Exposer(false,seckillId);
        }
        long start = Date.parse(seckill.getStartTime().toString());
        long end = Date.parse(seckill.getEndTime().toString());
        if(now >= start && now <= end){
            Exposer exposer = new Exposer(true,getMd5(String.valueOf(seckillId)),seckillId);
            return exposer;
        }else{
            return new Exposer(false,seckillId,now,start,end);
        }
    }
    
   
    private static String getMd5(String seckillId){
        String md5Source = String.valueOf(seckillId) + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(md5Source.getBytes());
        return md5;
    }
    @Transactional
    public SeckillExcution ExecuteSeckill(int seckillId, long userPhone,String md5) throws RepeatSeckillException,
            SeckillException, SeckillCloseException {
        
        try {
            if(md5 == null || !md5.equals(getMd5(seckillId + ""))){
                throw new SeckillException("md5 data rewrite");
            }
            //減庫存
            Date killtime = new Date();
            int updateNumber = seckillDao.reduceNumber(seckillId, killtime);
            if(updateNumber <=0 ){
                throw new SeckillCloseException("sekill close exception");
            }else{
                //增加成功秒殺信息
                int insertNumber = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if(insertNumber > 0 ){
                    SuccessKilled successKilled = successKilledDao.querySuccessKilledById(seckillId, userPhone);
                    return new SeckillExcution(seckillId, successKilled, SeckillStatusEnum.SUCCESS);
                }else{
                    throw new RepeatSeckillException("repeat seckill exception");
                }
            }
        }catch (SeckillCloseException e1) {
            logger.error(e1.toString());
           throw e1;
        }catch (RepeatSeckillException e2) {
            logger.error(e2.toString());
            throw e2;
        }catch (Exception e) {
            logger.error(e.toString());
            throw new SeckillException("seckill inner error");
        }
    }

}
