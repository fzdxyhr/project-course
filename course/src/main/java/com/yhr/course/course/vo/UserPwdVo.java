package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2019/3/31.
 */

@Data
public class UserPwdVo {

    @ApiModelProperty("旧的密码")
    private String oldPassword;

    @ApiModelProperty("新的密码")
    private String newPassword;
}
