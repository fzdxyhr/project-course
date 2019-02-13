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
@Table(name = "s_course_student")
public class CourseStudent {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("课程学生标识")
    private Integer id;

    @ApiModelProperty("学生标识")
    @Column(name = "student_id")
    private Integer studentId;

    @ApiModelProperty("课程标识")
    @Column(name = "course_id")
    private Integer courseId;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
