package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.exception.RepeatSeckillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.pojo.Seckill;

public interface SeckillService {

    //获得秒杀的列表
    List<Seckill> getAllSeckill();
    
    //根据id查询单个秒杀活动
    Seckill getSeckillById(int seckillId);
    
    //暴露秒杀接口
    Exposer exportSeckillUrl(int seckillId);
    
    //执行秒杀;
    SeckillExcution ExecuteSeckill(int seckillId,long userPhone,String md5) throws RepeatSeckillException,SeckillException,SeckillCloseException;
    
    
}
