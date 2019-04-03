package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2018/12/6 0006.
 */

@Data
public class TeachExportVo {

    @ApiModelProperty("教学班级名称")
    private String teachClassesName;

    @ApiModelProperty("教学内容名称")
    private String teachContentName;

    @ApiModelProperty("教学评价")
    private String teachEvaluation;

    @ApiModelProperty("教学说明")
    private String teachRemark;

    @ApiModelProperty("教学时间")
    private String teachTime;

}
