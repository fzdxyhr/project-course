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
@Table(name = "s_classes_teacher")
public class ClassesTeacher {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("班级老师标识")
    private Integer id;

    @ApiModelProperty("老师标识")
    @Column(name = "teacher_id")
    private Integer teacherId;

    @ApiModelProperty("班级标识")
    @Column(name = "classes_id")
    private Integer classesId;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
