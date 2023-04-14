package com.example.response;

import com.example.utils.CaptchaUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    public int ResponseCode;
    public T data;
    public String message;
    public static <T>BaseResponse<T> success(T data){
        return new BaseResponse(200,data,"ok");
    }
    public static <T>BaseResponse<T> Error(){
        return new BaseResponse(500,null,"Error");
    }
}
