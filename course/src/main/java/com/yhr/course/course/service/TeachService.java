package com.yhr.course.course.service;

import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.entity.Teach;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.ChapterTreeVo;
import com.yhr.course.course.vo.TeachVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019-01-10.
 */
public interface TeachService {

    PagerHelper<TeachVo> list(Integer classId, Integer pageNo, Integer pageSize);

    TeachVo create(TeachVo teach);

    TeachVo update(Integer id, TeachVo teach) throws Exception;

    void delete(Integer id) throws Exception;

    Teach get(Integer id) throws Exception;

    List<ChapterTreeVo> chapterTree() throws Exception;

    void exportTeach(HttpServletResponse response, Integer classId) throws Exception;

}
