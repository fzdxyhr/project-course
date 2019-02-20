package com.yhr.course.course.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Administrator on 2019/2/17.
 */

@Data
public class UserVo {

    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("密码")
    private String password;
}
