package net.likeyun.common.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @Description: 统一响应结果集
 * @Author: lfy
 * @Date: 2019/11/28 14:17
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Result<T> {
    //操作代码
    int code;

    //提示信息
    String message;

    boolean success = true;

    //结果数据
    T data;

    public Result(ResultCode resultCode,boolean success){
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.success = success;
    }

    public Result(ResultCode resultCode, T data,boolean success){
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.success = success;
        this.data = data;
    }

    public Result(String message){
        this.message = message;
    }

    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS,true);
    }

    public static <T> Result SUCCESS(T data){
        return new Result(ResultCode.SUCCESS, data,true);
    }

    public static Result FAIL(){
        return new Result(ResultCode.FAIL,false);
    }

    public static Result FAIL(String message){
        return new Result(message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}