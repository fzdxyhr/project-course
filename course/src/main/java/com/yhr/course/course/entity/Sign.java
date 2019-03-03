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
@Table(name = "s_sign")
public class Sign {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("签到标识")
    private Integer id;

    @ApiModelProperty("学生标识")
    @Column(name = "student_id")
    private Integer studentId;

    @ApiModelProperty("老师标识")
    @Column(name = "teacher_id")
    private Integer teacherId;

    @ApiModelProperty("课程标识")
    @Column(name = "course_id")
    private Integer courseId;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
