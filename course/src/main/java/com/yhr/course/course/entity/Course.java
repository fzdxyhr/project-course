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
@Table(name = "s_course")
public class Course {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("课程标识")
    private Integer id;

    @ApiModelProperty("课程名称")
    @Column(name = "course_name")
    private String courseName;

    @ApiModelProperty("课程描述")
    @Column(name = "course_desc")
    private String courseDesc;

    @ApiModelProperty("课程提示")
    @Column(name = "course_tip")
    private String courseTip;

    @ApiModelProperty("任课老师标识")
    @Column(name = "teacher_id")
    private Integer teacherId;

    @ApiModelProperty("归属标签标识")
    @Column(name = "tag_id")
    private String tagId;

    @ApiModelProperty("课程教室")
    @Column(name = "course_room")
    private String courseRoom;

    @ApiModelProperty("最大可选人数")
    @Column(name = "max_student")
    private Integer maxStudent;

    @ApiModelProperty("课程图片地址")
    @Column(name = "course_image_url")
    private String courseImageUrl;

    @ApiModelProperty("创建时间")
    @Column(name = "create_time")
    private Date createTime;

}
