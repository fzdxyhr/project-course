package com.yhr.course.course.service;

import com.yhr.course.course.vo.HomeworkStatisticVo;
import com.yhr.course.course.vo.SignStatisticVo;

import java.util.List;

/**
 * @author yhr
 * @version latest
 * @date 2019/3/22
 */
public interface StatisticsService {

    List<SignStatisticVo> getSignStatistic(Integer classId);

    List<HomeworkStatisticVo> getHomeworkStatistic(Integer classId);
}
