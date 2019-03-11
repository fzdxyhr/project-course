package com.yhr.course.course.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * @author yhr
 * @version latest
 * @date 2019/3/5
 */

@Data
public class CourseChapterVo {

    private Integer id;

    @ApiModelProperty("课程标识")
    private Integer courseId;

    @ApiModelProperty("章节名称")
    private String chapterName;

    @ApiModelProperty("章节描述")
    private String chapterDesc;

    @ApiModelProperty("章节父标识")
    private Integer chapterParentId;

    @ApiModelProperty("章节类型（1: 文档,2: PPT课件,3: 视频）")
    private Integer chapterType;

    @ApiModelProperty("章节对象路径")
    private String chapterFilePath;

    @ApiModelProperty("是否为最近学习章节")
    private boolean recentStudy;

    @ApiModelProperty("是否学习过章节")
    private boolean study;

    @ApiModelProperty("子章节对象信息")
    private List<CourseChapterVo> courseChapterVos;

}
