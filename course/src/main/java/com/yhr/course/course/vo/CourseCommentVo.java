package com.yhr.course.course.vo;

import com.yhr.course.course.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2019/3/1.
 */

@Data
public class CourseCommentVo {

    @ApiModelProperty("课程评论标识")
    private Integer id;

    @ApiModelProperty("课程标识")
    private Integer courseId;

    @ApiModelProperty("用户标识")
    private Integer userId;

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("评分(10分为最高分)")
    private Integer score;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("用户头像路径")
    private String photoPath;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
