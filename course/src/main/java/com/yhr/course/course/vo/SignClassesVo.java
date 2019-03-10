package com.yhr.course.course.vo;

import com.yhr.course.course.entity.Classes;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/12/6 0006.
 */

@Data
public class SignClassesVo {

    @ApiModelProperty("班级标识")
    private Integer id;

    @ApiModelProperty("班级名称")
    private String className;

    @ApiModelProperty("班级描述")
    private String classDesc;

    @ApiModelProperty("授课老师标识")
    private Integer teacherId;

    @ApiModelProperty("授课老师名称")
    private String teacherName;

    @ApiModelProperty("是否签到（0:未签到,1:已签到）")
    private Integer sign;

    @ApiModelProperty("创建时间")
    private Date createTime;

    public static SignClassesVo from(Classes classes, String teacherName, Integer sign) {
        SignClassesVo classesVo = new SignClassesVo();
        classesVo.setClassName(classes.getClassName());
        classesVo.setClassDesc(classes.getClassDesc());
        classesVo.setSign(sign);
        classesVo.setTeacherName(teacherName);
        classesVo.setTeacherId(classes.getTeacherId());
        return classesVo;
    }

}
