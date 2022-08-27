package com.oasystem.bean;

public class Result {

    private boolean success;//是否成功的标志位
    private String message;//消息
    private Object obj;//存放结果对象
    private String errorCode;//存放错误码

    public Result() {}

    public Result(boolean success, String message, Object obj, String errorCode) {
        this.success = success;
        this.message = message;
        this.obj = obj;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "Result{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", obj=" + obj +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
