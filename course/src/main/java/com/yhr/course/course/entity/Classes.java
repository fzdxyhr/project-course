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
@Table(name = "s_classes")
public class Classes {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("班级标识")
    private Integer id;

    @ApiModelProperty("班级名称")
    @Column(name = "class_name")
    private String className;

    @ApiModelProperty("班级描述")
    @Column(name = "class_desc")
    private String classDesc;

    @ApiModelProperty("授课老师标识")
    @Column(name = "teacher_id")
    private Integer teacherId;

    @ApiModelProperty("班长")
    @Column(name = "monitor")
    private Integer monitor;

    @ApiModelProperty("班级人数")
    @Column(name = "max_student")
    private Integer maxStudent;

    @ApiModelProperty("班级教学进度")
    @Column(name = "progress")
    private Integer progress;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

    //不做系列化的字段
    @Transient
    private String teacherName;
    @Transient
    private String monitorName;

}
