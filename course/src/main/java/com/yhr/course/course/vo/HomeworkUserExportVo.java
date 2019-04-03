package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/3/17.
 */

@Data
public class HomeworkUserExportVo {

    @ApiModelProperty("用户名称")
    private String userName;

    @ApiModelProperty("班级名称")
    private String className;

    @ApiModelProperty("作业名称")
    private String homeworkName;

    @ApiModelProperty("评分")
    private Integer score;

    @ApiModelProperty("提交作业文件路径")
    private String submitHomeworkFilePath;

    @ApiModelProperty("提交时间")
    private Date submitTime;

}
