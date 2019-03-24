package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/6 0006.
 */

@Data
public class TeachVo {

    @ApiModelProperty("教学标识")
    private Integer id;

    @ApiModelProperty("教学班级")
    private Integer teachClasses;

    @ApiModelProperty("教学班级名称")
    private String teachClassesName;

    @ApiModelProperty("教学内容")
    private String teachContent;

    @ApiModelProperty("教学内容名称")
    private String teachContentName;

    @ApiModelProperty("教学评价")
    private Integer teachEvaluation;

    @ApiModelProperty("教学说明")
    private String teachRemark;

    @ApiModelProperty("教学开始时间")
    private Date teachStartTime;

    @ApiModelProperty("教学结束时间")
    private Date teachEndTime;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
