package com.yhr.course.course.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2019/3/17.
 */

@Data
public class HomeworkUserVo {

    private String userName;

    private Integer userId;

    private Integer homeworkId;

    private Date submitTime;

}
