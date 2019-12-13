package net.likeyun.common.exception;

import net.likeyun.common.response.ResultCode;

import java.text.MessageFormat;

/**
 * @Description: 自定义异常类型
 * @Author: lfy
 * @Date: 2019/11/28 14:18
 */
public class CustomException extends RuntimeException {

    //错误代码
    ResultCode resultCode;

    public CustomException(ResultCode resultCode){
        super(resultCode.message());
        this.resultCode = resultCode;
    }

    public CustomException(ResultCode resultCode, Object... args){
        super(resultCode.message());
        String message = MessageFormat.format(resultCode.message(), args);
        resultCode.setMessage(message);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode(){
        return resultCode;
    }

}
