package com.yhr.course.course.controller;

import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.service.SignService;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.SignClassesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2019-01-10.
 */
@RestController
@RequestMapping("/v1")
public class SignController {

    @Autowired
    private SignService signService;

    @RequestMapping(value = "/signs/classes", method = RequestMethod.GET)
    public List<SignClassesVo> listSignClasses() {
        return signService.listSignClasses();
    }

    @RequestMapping(value = "/signs", method = RequestMethod.POST)
    public boolean listSignClasses(@RequestParam("teacher_id") Integer teacherId) {
        return signService.sign(teacherId);
    }

    @RequestMapping(value = "/signs/status", method = RequestMethod.GET)
    public Integer getSignStatus() {
        return signService.getSignStatus();
    }

}