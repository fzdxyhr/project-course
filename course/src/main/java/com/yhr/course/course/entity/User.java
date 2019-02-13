package com.yhr.course.course.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/6 0006.
 */

@Data
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("用户标识")
    private Integer id;

    @ApiModelProperty("用户名称")
    @Column(name = "user_name")
    private String userName;

    @ApiModelProperty("账号")
    @Column(name = "account")
    private String account;

    @ApiModelProperty("密码")
    @Column(name = "password")
    private String password;

    @ApiModelProperty("身份证")
    @Column(name = "id_card")
    private String idCard;

    @ApiModelProperty("性别(1:男,2:女)")
    @Column(name = "sex")
    private Integer sex;

    @ApiModelProperty("状态(0:失效,1:有效)")
    @Column(name = "status")
    private Integer status;

    @ApiModelProperty("电话")
    @Column(name = "telephone")
    private String telephone;

    @ApiModelProperty("班级标识")
    @Column(name = "class_id")
    private Integer classId;

    @ApiModelProperty("是否管理员(0:否,1:是)")
    @Column(name = "is_admin")
    private Integer isAdmin;

    @ApiModelProperty("角色(teacher:老师,student:学生)")
    @Column(name = "role")
    private String role;

    @ApiModelProperty("头像地址")
    @Column(name = "photo_path")
    private String photoPath;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
