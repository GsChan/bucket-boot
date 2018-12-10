package com.boot.bucket.common.dto;

import java.io.Serializable;

/**
 * <p>Project: bucket-boot </p>
 *
 * <p>Function: 返回结果dto对象 </p>
 *
 * <p>Description: 返回结果dto对象 </p>
 *
 * @author liwq
 *
 */
public class ResultDto<T> implements Serializable {

    private static final long serialVersionUID = 8749042758887271273L;

    /**
     * 返回值对象
     */
    private T value;

    /**
     * 返回结果成功，失败
     */
    private boolean success;

    /**
     * 错误代码
     */
    private int errorCode;

    /**
     * 错误信息
     */
    private String message;

    public static <T> ResultDto<T> valueOfSuccess() {
        return ResultDto.valueOfSuccess(null);
    }

    public static <T> ResultDto<T> valueOfSuccess(T value) {
        ResultDto resultDto = new ResultDto();
        resultDto.setSuccess(true);
        resultDto.setValue(value);
        return resultDto;
    }
    public static <T> ResultDto<T> valueOfSuccess(T value,String message) {
        ResultDto resultDto = new ResultDto();
        resultDto.setSuccess(true);
        resultDto.setValue(value);
        resultDto.setMessage(message);
        return resultDto;
    }

    public static <T> ResultDto<T> valueOfError(String message) {
        return ResultDto.valueOfError(message, -1);
    }

    public static <T> ResultDto<T> valueOfError(String message, int errorCode) {
        return ResultDto.valueOfError(message, errorCode, null);
    }

    public static <T> ResultDto<T> valueOfError(String message, int errorCode, T value) {
        ResultDto resultDto = new ResultDto();
        resultDto.setSuccess(false);
        resultDto.setErrorCode(errorCode);
        resultDto.setMessage(message);
        resultDto.setValue(value);
        return resultDto;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
