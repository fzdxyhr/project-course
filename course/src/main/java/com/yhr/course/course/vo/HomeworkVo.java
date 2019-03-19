package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2019/3/10.
 */

@Data
public class HomeworkVo {

    @ApiModelProperty("作业标识")
    private Integer id;

    @ApiModelProperty("作业标题")
    private String homeworkTitle;

    @ApiModelProperty("发布老师")
    private Integer publishTeacher;

    @ApiModelProperty("发布老师名称")
    private String publishTeacherName;

    @ApiModelProperty("作业描述")
    private String homeworkDesc;

    @ApiModelProperty("作业文件路径")
    private String homeworkFilePath;

    @ApiModelProperty("提交作业文件路径")
    private String submitHomeworkFilePath;

    @ApiModelProperty("作业评分")
    private Integer score;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
