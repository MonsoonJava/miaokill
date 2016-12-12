package org.seckill.dto;

public class SeckillResult<T> {

    private boolean state ;
    
    private String resultMsg;
    
    private T data;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public SeckillResult(boolean state, T data) {
        this.state = state;
        this.data = data;
    }

    public SeckillResult(boolean state, String resultMsg) {
        this.state = state;
        this.resultMsg = resultMsg;
    }
    
    
    
    
}
