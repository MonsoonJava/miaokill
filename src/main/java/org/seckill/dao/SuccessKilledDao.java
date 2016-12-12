package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.pojo.SuccessKilled;

public interface SuccessKilledDao {
    /**
     * 插入购买明细，可以重复过滤
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId")int seckillId,@Param("userPhone")long userPhone);
    
    /**
     * 根据秒杀活动ID，获取成功的秒杀信息，
     * @param string
     * @return
     */
    SuccessKilled querySuccessKilledById(@Param("seckillId")int seckillId,@Param("userPhone")long userPhone);
}
