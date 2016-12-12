package org.seckill.dto;

import org.seckill.enums.SeckillStatusEnum;
import org.seckill.pojo.SuccessKilled;

//执行秒杀后的数据传输存储对象
public class SeckillExcution {

    private int seckillId;
    
    private SuccessKilled successKilled;
    //秒杀执行后的状态
    private SeckillStatusEnum seckillStatusEnum;


    
    
    public SeckillExcution(int seckillId, SuccessKilled successKilled, SeckillStatusEnum seckillStatusEnum) {
        this.seckillId = seckillId;
        this.successKilled = successKilled;
        this.seckillStatusEnum = seckillStatusEnum;
    }

    public SeckillExcution(int seckillId, SeckillStatusEnum seckillStatusEnum) {
        this.seckillId = seckillId;
        this.seckillStatusEnum = seckillStatusEnum;
    }

    public int getSeckillId() {
        return seckillId;
    }


    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

   

    public SeckillStatusEnum getSeckillStatusEnum() {
        return seckillStatusEnum;
    }


    public void setSeckillStatusEnum(SeckillStatusEnum seckillStatusEnum) {
        this.seckillStatusEnum = seckillStatusEnum;
    }


    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }


    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExcution [seckillId=" + seckillId + ", successKilled=" + successKilled
                + ", seckillStatusEnum=" + seckillStatusEnum + "]";
    }
    
    
}
