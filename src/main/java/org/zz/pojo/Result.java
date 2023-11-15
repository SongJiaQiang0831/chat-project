package org.zz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author songjiaqiang  2023/11/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    int code;
    String msg;
    T data;

    private static final int SUCCESS_CODE=200;
    private static final String SUCCESS_STR="success";
    private static final int FAIL_CODE=500;
    private static final String FAIL_STR="fail";



    public Result(T data) {
        this.code=SUCCESS_CODE;
        this.msg=SUCCESS_STR;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(SUCCESS_CODE,SUCCESS_STR,data);
    }
    public static <T> Result<T> fail(String msg,T data) {
        return new Result<T>(FAIL_CODE,msg,data);
    }
}
