package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2019/4/2.
 */

@Data
public class UserExportVo {

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("班级名称")
    private String className;

    @ApiModelProperty("身份证")
    private String idCard;

    @ApiModelProperty("性别(1:男,2:女)")
    private String sex;

    @ApiModelProperty("电话")
    private String telephone;

    @ApiModelProperty("状态(0:失效,1:有效)")
    private String status;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
