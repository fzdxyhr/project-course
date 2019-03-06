package com.yhr.course.course.service.impl;

import com.yhr.course.course.dao.CourseChapterRepository;
import com.yhr.course.course.entity.CourseChapter;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.CourseChapterService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseChapterVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/3/3.
 */
@Service
public class CourseChapterServiceImpl implements CourseChapterService {
    @Autowired
    private CourseChapterRepository courseChapterRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public PagerHelper<CourseChapter> list(String key, Integer pageNo, Integer pageSize) {
        PagerHelper<CourseChapter> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_course_chapter where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and chapter_name like ?");
            params.add("%" + key + "%");
        }

        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<CourseChapter> courseChapters = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<CourseChapter>(CourseChapter.class));
        result.setTotal(total);
        result.setItems(courseChapters);
        return result;
    }

    @Override
    public CourseChapterVo create(CourseChapterVo courseChapterVo) {
        List<CourseChapter> courseChapters = new ArrayList<>();
        CourseChapter courseChapter = new CourseChapter();
        BeanUtils.copyProperties(courseChapterVo, courseChapter);
        courseChapter.setCreateTime(new Date());
        courseChapter = courseChapterRepository.save(courseChapter);
        //保存子章节信息
        if (CollectionUtils.isNotEmpty(courseChapterVo.getCourseChapterVos())) {
            for (CourseChapterVo chapterVo : courseChapterVo.getCourseChapterVos()) {
                CourseChapter chapter = new CourseChapter();
                BeanUtils.copyProperties(chapterVo, chapter);
                chapter.setChapterParentId(courseChapter.getId());
                chapter.setCourseId(courseChapterVo.getCourseId());
                courseChapters.add(chapter);
            }
        }
        courseChapterRepository.saveAll(courseChapters);
        return courseChapterVo;
    }

    @Override
    @Transactional
    public CourseChapterVo update(Integer id, CourseChapterVo courseChapterVo) throws Exception {
        List<CourseChapter> courseChapters = new ArrayList<>();
        CourseChapter courseChapter = courseChapterRepository.getOne(id);
        if (courseChapter == null) {
            throw new ServiceException("不存在【" + id + "】对应的章节");
        }
        BeanUtils.copyProperties(courseChapterVo, courseChapter);
        courseChapter.setId(id);
        courseChapterRepository.save(courseChapter);
        //保存子章节信息
        if (CollectionUtils.isNotEmpty(courseChapterVo.getCourseChapterVos())) {
            courseChapterRepository.deleteByChapterParentId(id);
            for (CourseChapterVo chapterVo : courseChapterVo.getCourseChapterVos()) {
                CourseChapter chapter = new CourseChapter();
                BeanUtils.copyProperties(chapterVo, chapter);
                chapter.setChapterParentId(courseChapter.getId());
                chapter.setCourseId(courseChapterVo.getCourseId());
                courseChapters.add(chapter);
            }
        }
        courseChapterRepository.saveAll(courseChapters);
        return courseChapterVo;
    }

    @Override
    @Transactional
    public void delete(Integer id) throws Exception {
        CourseChapter courseChapter = courseChapterRepository.getOne(id);
        if (courseChapter == null) {
            throw new ServiceException("不存在【" + id + "】对应的章节");
        }
        courseChapterRepository.delete(courseChapter);
        courseChapterRepository.deleteByChapterParentId(id);
    }

    @Override
    public CourseChapter get(Integer id) throws Exception {
        CourseChapter tempTag = courseChapterRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的章节");
        }
        return tempTag;
    }
}
