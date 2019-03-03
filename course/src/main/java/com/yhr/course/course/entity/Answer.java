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
@Table(name = "s_answer")
public class Answer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("回复标识")
    private Integer id;

    @ApiModelProperty("回复内容")
    @Column(name = "answer_content")
    private String answer_content;

    @ApiModelProperty("回复问题标识")
    @Column(name = "question_id")
    private Integer question_id;

    @ApiModelProperty("用户标识")
    @Column(name = "user_id")
    private Integer userId;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
