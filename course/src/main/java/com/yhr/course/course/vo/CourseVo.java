package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/12/6 0006.
 */

@Data
public class CourseVo {

    @ApiModelProperty("课程标识")
    private Integer id;

    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("课程描述")
    private String courseDesc;

    @ApiModelProperty("课程提示")
    private String courseTip;

    @ApiModelProperty("任课老师标识")
    private Integer teacherId;

    @ApiModelProperty("归属标签标识")
    private String tagId;

    @ApiModelProperty("课程教室")
    private String courseRoom;

    @ApiModelProperty("最大可选人数")
    private Integer maxStudent;

    @ApiModelProperty("课程图片地址")
    private String courseImageUrl;

    @ApiModelProperty("课程章节信息")
    private List<CourseChapterVo> courseChapterVos;

    @ApiModelProperty("创建时间")
    private Date createTime;

}
