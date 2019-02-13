package com.yhr.course.course.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2019-01-10.
 */

@Data
public class ResponseData<T> {

    @ApiModelProperty("是否成功,false:失败,true:成功")
    private boolean success;
    @ApiModelProperty("返回信息")
    private String message;
    @ApiModelProperty("返回数据")
    private T data;
    @ApiModelProperty("请求地址")
    private String url;
}
