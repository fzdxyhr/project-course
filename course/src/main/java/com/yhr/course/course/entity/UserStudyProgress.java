package com.yhr.course.course.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yhr
 * @version latest
 * @date 2019/3/11
 */
@Data
@Entity
@Proxy(lazy = false)
@Table(name = "s_user_study_progress")
public class UserStudyProgress {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("用户学习标识")
    private Integer id;

    @ApiModelProperty("课程标识")
    @Column(name = "course_id")
    private Integer courseId;

    @ApiModelProperty("用户标识")
    @Column(name = "user_id")
    private Integer userId;

    @ApiModelProperty("章节标识")
    @Column(name = "chapter_id")
    private Integer chapterId;

    @ApiModelProperty("学习进度")
    @Column(name = "progress")
    private Integer progress;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
