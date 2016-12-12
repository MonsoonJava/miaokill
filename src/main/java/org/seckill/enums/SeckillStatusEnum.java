package org.seckill.enums;

//使用枚舉來表述常量數據
public enum SeckillStatusEnum {
    SUCCESS(1,"秒殺成功"),
    END(0,"秒殺結束"),
    REPEAT_SECKILL(-1,"重複秒殺"),
    INNER_ERROR(-2,"服務器錯誤"),
    DATA_REWRITE(-3,"數據篡改");
    
    
    
    private int status;
    
    private String statusInfo;

    private SeckillStatusEnum(int status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public static SeckillStatusEnum statusOf(int index){
        for( SeckillStatusEnum state :SeckillStatusEnum.values()){
            if(state.getStatus() == index) {
                return state;
            }
        }
        return null;
    }
    
}
