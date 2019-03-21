package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/3/21.
 */

@Data
public class SignTimeVo {

    @ApiModelProperty("签到开始你时间")
    private Date signStartTime;

    @ApiModelProperty("签到结束你时间")
    private Date signEndTime;
}
