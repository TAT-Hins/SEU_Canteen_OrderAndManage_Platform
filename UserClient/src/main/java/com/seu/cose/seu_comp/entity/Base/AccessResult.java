package com.seu.cose.seu_comp.entity.Base;

import com.seu.cose.seu_comp.entity.Base.User;

/*
 * 通用请求返回结果
 */
public class AccessResult<T extends Object> {

    private Boolean result;

    private String status;

    protected T responseBody;

    public void setResult(Boolean result){
        this.result = result;
    }

    public Boolean getResult(){
        return this.result;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }

    public T getResponseBody() {
        return this.responseBody;
    }
}
