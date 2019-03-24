package com.yhr.course.course.controller;

import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.service.StatisticsService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.HomeworkStatisticVo;
import com.yhr.course.course.vo.SignStatisticVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yhr
 * @version latest
 * @date 2019/3/22
 */

@RestController
@RequestMapping("/v1/statistic")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public List<SignStatisticVo> getSignStatistic(@RequestParam(value = "class_id", required = false) Integer classId) {
        return statisticsService.getSignStatistic(classId);
    }

    @RequestMapping(value = "/homework", method = RequestMethod.GET)
    public List<HomeworkStatisticVo> getHomeworkStatistic(@RequestParam(value = "teacher_id", required = false) Integer teacherId) {
        return statisticsService.getHomeworkStatistic(teacherId);
    }
}
