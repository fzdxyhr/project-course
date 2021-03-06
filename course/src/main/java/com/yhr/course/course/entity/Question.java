package com.yhr.course.course.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/6 0006.
 */

@Data
@Entity
@Proxy(lazy = false)
@Table(name = "s_question")
public class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("问题标识")
    private Integer id;

    @ApiModelProperty("问题标题")
    @Column(name = "question_title")
    private String questionTitle;

    @ApiModelProperty("问题内容")
    @Column(name = "question_content")
    private String questionContent;

    @ApiModelProperty("课程标识")
    @Column(name = "course_id")
    private Integer courseId;

    @ApiModelProperty("用户标识")
    @Column(name = "user_id")
    private Integer userId;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
