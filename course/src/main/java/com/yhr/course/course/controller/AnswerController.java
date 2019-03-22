package com.yhr.course.course.controller;

import com.yhr.course.course.service.AnswerService;
import com.yhr.course.course.service.QuestionService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.AnswerVo;
import com.yhr.course.course.vo.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2019-01-10.
 */

@RestController
@RequestMapping("/v1/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public AnswerVo create(@RequestBody AnswerVo answerVo) throws Exception {
        return answerService.create(answerVo);
    }

}