package com.yhr.course.course.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2019/3/1.
 */

@Data
@Entity
@Table(name = "s_course_chapter")
public class CourseChapter {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("课程章节标识")
    private Integer id;

    @ApiModelProperty("章节名称")
    @Column(name = "chapter_name")
    private String chapterName;

    @ApiModelProperty("章节描述")
    @Column(name = "chapter_desc")
    private String chapterDesc;

    @ApiModelProperty("章节父标识")
    @Column(name = "chapter_parent_id")
    private Integer chapterParentId;

    @ApiModelProperty("章节类型（1: 文档,2: PPT课件,3: 视频）")
    @Column(name = "chapter_type")
    private Integer chapterType;

    @ApiModelProperty("章节对象路径")
    @Column(name = "chapter_file_path")
    private String chapterFilePath;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
