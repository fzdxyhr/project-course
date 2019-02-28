package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yhr
 * @version latest
 * @date 2019/2/28
 */

@Data
public class StudentVo {

    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("是否签到（0:未签到,1:已签到）")
    private Integer sign;

}
