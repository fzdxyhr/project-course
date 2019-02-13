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
@Table(name = "s_question")
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("问题标识")
    private Integer id;

    @ApiModelProperty("问题内容")
    @Column(name = "question_content")
    private String questionContent;

    @ApiModelProperty("课程标识")
    @Column(name = "course_id")
    private Integer courseId;

    @ApiModelProperty("章节标识")
    @Column(name = "chapter_id")
    private Integer chapter_id;

    @ApiModelProperty("用户标识")
    @Column(name = "user_id")
    private Integer userId;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
