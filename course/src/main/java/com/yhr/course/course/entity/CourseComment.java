package com.yhr.course.course.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2019/3/1.
 */

@Data
@Entity
@Table(name = "s_course_comment")
public class CourseComment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("课程评论标识")
    private Integer id;

    @ApiModelProperty("课程标识")
    @Column(name = "course_id")
    private Integer courseId;

    @ApiModelProperty("用户标识")
    @Column(name = "user_id")
    private Integer userId;

    @ApiModelProperty("评分(10分为最高分)")
    @Column(name = "score")
    private Integer score;

    @ApiModelProperty("评论内容")
    @Column(name = "comment_content")
    private String commentContent;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
