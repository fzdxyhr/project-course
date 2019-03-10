package com.yhr.course.course.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2019/3/10.
 */

@Data
@Entity
@Proxy(lazy = false)
@Table(name = "s_homework")
public class Homework {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("作业标识")
    private Integer id;

    @ApiModelProperty("作业标题")
    @Column(name = "homework_title")
    private String homeworkTitle;

    @ApiModelProperty("发布老师")
    @Column(name = "publish_teacher")
    private Integer publishTeacher;

    @ApiModelProperty("作业描述")
    @Column(name = "homework_desc")
    private String homeworkDesc;

    @ApiModelProperty("作业文件路径")
    @Column(name = "homework_file_path")
    private String homeworkFilePath;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;
}
