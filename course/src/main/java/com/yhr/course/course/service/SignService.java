package com.yhr.course.course.service;

import com.yhr.course.course.vo.SignClassesVo;

import java.util.List;

/**
 * Created by Administrator on 2019/3/10.
 */
public interface SignService {

    List<SignClassesVo> listSignClasses();

    boolean sign(Integer teacherId);

    Integer getSignStatus();
}
