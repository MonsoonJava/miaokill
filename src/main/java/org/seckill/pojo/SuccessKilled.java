package org.seckill.pojo;

import java.util.Date;

public class SuccessKilled {

    private int seckillId;
    
    private long userPhone;
    
    private short status;
    
    private Date createTime;

    //多对一 :多个成功秒杀对应一个秒杀项
    private Seckill seckill;
    
    
    
    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public int getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(int seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled [seckillId=" + seckillId + ", userPhone=" + userPhone + ", status=" + status
                + ", createTime=" + createTime + ", seckill=" + seckill.toString() + "]";
    }
    
    
    
    
        
}
