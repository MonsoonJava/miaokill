package org.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.seckill.pojo.Seckill;

public interface SeckillDao {

    /**
     * 减少库存
     * @param seckillId
     * @param createTime
     * @return
     */
    public int reduceNumber(@Param("seckillId")int seckillId,@Param("killtime")Date killtime);
    
    /**
     * 根据秒杀的ID去查询特定秒杀信息的 
     * @param seckillId
     * @return
     */
    Seckill querySeckillbyID(int seckillId);
    
    
    
    
    /**
     * 根据偏移量获取秒杀列表
     * @param offset
     * @param limit
     * @return
     */
   List<Seckill> queryAll(@Param("offset")int offset,@Param("limit")int limit);
}
