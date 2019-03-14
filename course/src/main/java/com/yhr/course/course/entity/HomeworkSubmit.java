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
@Table(name = "s_homework_submit")
public class HomeworkSubmit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("作业提交标识")
    private Integer id;

    @ApiModelProperty("作业标识")
    @Column(name = "homework_id")
    private Integer homeworkId;

    @ApiModelProperty("学生标识")
    @Column(name = "user_id")
    private Integer userId;

    @ApiModelProperty("作业文件路径")
    @Column(name = "homework_file_path")
    private String homeworkFilePath;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
