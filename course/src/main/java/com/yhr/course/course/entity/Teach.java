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
@Table(name = "s_teach")
public class Teach {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("教学标识")
    private Integer id;

    @ApiModelProperty("教学班级")
    @Column(name = "teach_classes")
    private Integer teachClasses;

    @ApiModelProperty("教学内容")
    @Column(name = "teach_content")
    private String teachContent;

    @ApiModelProperty("教学评价")
    @Column(name = "teach_evaluation")
    private Integer teachEvaluation;

    @ApiModelProperty("教学说明")
    @Column(name = "teach_remark")
    private String teachRemark;

    @ApiModelProperty("教学开始时间")
    @Column(name = "teach_start_time")
    private Date teachStartTime;

    @ApiModelProperty("教学结束时间")
    @Column(name = "teach_end_time")
    private Date teachEndTime;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
