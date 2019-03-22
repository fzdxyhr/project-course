package com.yhr.course.course.service.impl;

import com.yhr.course.course.config.GaeaContext;
import com.yhr.course.course.dao.AnswerRepository;
import com.yhr.course.course.entity.Answer;
import com.yhr.course.course.service.AnswerService;
import com.yhr.course.course.vo.AnswerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2019-01-09.
 */

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public AnswerVo create(AnswerVo answerVo) throws Exception {
        Answer answer = new Answer();
        answer.setAnswerContent(answerVo.getAnswerContent());
        answer.setQuestionId(answerVo.getQuestionId());
        answer.setUserId(GaeaContext.getUserId());
        answer.setCreateTime(new Date());
        answer = answerRepository.save(answer);
        answerVo.setId(answer.getId());
        return answerVo;
    }
}
