package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2019/3/8.
 */

@Data
public class QuestionVo {

    @ApiModelProperty("问题标识")
    private Integer id;

    @ApiModelProperty("问题标题")
    private String questionTitle;

    @ApiModelProperty("问题内容")
    private String questionContent;

    @ApiModelProperty("课程标识")
    private Integer courseId;

    @ApiModelProperty("课程图片地址")
    private String coursePath;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("用户标识")
    private Integer userId;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("用户头像路径")
    private String photoPath;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
