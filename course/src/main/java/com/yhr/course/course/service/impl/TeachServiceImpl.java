package com.yhr.course.course.service.impl;

import com.yhr.course.course.dao.CourseChapterRepository;
import com.yhr.course.course.dao.CourseRepository;
import com.yhr.course.course.dao.TagRepository;
import com.yhr.course.course.dao.TeachRepository;
import com.yhr.course.course.entity.*;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.ClassesService;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.service.TeachService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.ChapterTreeVo;
import com.yhr.course.course.vo.TeachVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2019-01-10.
 */

@Service
public class TeachServiceImpl implements TeachService {

    public static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    public static String[] weekdays = new String[]{"一", "二", "三", "四", "五", "六", "日"};

    @Autowired
    private TeachRepository teachRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseChapterRepository courseChapterRepository;
    @Autowired
    private ClassesService classesService;

    @Override
    public PagerHelper<TeachVo> list(String key, Integer pageNo, Integer pageSize) {
        PagerHelper<TeachVo> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_teach where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and tag_name like ?");
            params.add("%" + key + "%");
        }

        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<TeachVo> teaches = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(TeachVo.class));
        if (CollectionUtils.isNotEmpty(teaches)) {
            Map<Integer, Classes> classesMap = classesService.getAllClassesMap();
            for (TeachVo teach : teaches) {
                teach.setTeachClassesName(teach.getTeachClasses() == null || classesMap.get(teach.getTeachClasses()) == null ? "" : classesMap.get(teach.getTeachClasses()).getClassName());
                teach.setTeachContentName(getContentName(teach.getTeachContent()));
                teach.setTeachTime(getDateContent(teach.getTeachStartTime(), teach.getTeachEndTime()));
            }
        }
        result.setTotal(total);
        result.setItems(teaches);
        return result;
    }

    @Override
    public TeachVo create(TeachVo teachVo) {
        Teach teach = new Teach();
        BeanUtils.copyProperties(teachVo, teach);
        teach.setCreateTime(new Date());
        teachRepository.save(teach);
        return teachVo;
    }

    @Override
    public TeachVo update(Integer id, TeachVo teachVo) throws Exception {
        Teach tempTeach = teachRepository.getOne(id);
        if (tempTeach == null) {
            throw new ServiceException("不存在【" + id + "】对应的教学");
        }
        BeanUtils.copyProperties(teachVo, tempTeach);
        teachRepository.save(tempTeach);
        return teachVo;
    }

    @Override
    public void delete(Integer id) throws Exception {
        Teach tempTeach = teachRepository.getOne(id);
        if (tempTeach == null) {
            throw new ServiceException("不存在【" + id + "】对应的教学");
        }
        teachRepository.delete(tempTeach);
    }

    @Override
    public Teach get(Integer id) throws Exception {
        Teach tempTeach = teachRepository.getOne(id);
        if (tempTeach == null) {
            throw new ServiceException("不存在【" + id + "】对应的教学");
        }
        return tempTeach;
    }

    @Override
    public List<ChapterTreeVo> chapterTree() throws Exception {
        //获取所有课程信息
        List<Course> courses = courseRepository.findAll();
        if (CollectionUtils.isEmpty(courses)) {
            return Collections.EMPTY_LIST;
        }
        List<ChapterTreeVo> chapterTreeVos = new ArrayList<>();
        for (Course course : courses) {
            ChapterTreeVo chapterTreeVo = new ChapterTreeVo();
            chapterTreeVo.setLabel(course.getCourseName());
            chapterTreeVo.setValue(course.getId());
            //查询章节信息
            List<CourseChapter> courseChapters = courseChapterRepository.findByCourseIdAndChapterParentIdIsNull(course.getId());
            if (CollectionUtils.isNotEmpty(courseChapters)) {
                List<ChapterTreeVo> treeVos = new ArrayList<>();
                for (CourseChapter courseChapter : courseChapters) {
                    ChapterTreeVo treeVo = new ChapterTreeVo();
                    treeVo.setLabel(courseChapter.getChapterName());
                    treeVo.setValue(courseChapter.getId());
                    treeVos.add(treeVo);
                }
                chapterTreeVo.setChildren(treeVos);
                getAllChapterTreeByCicle(treeVos);
            }
            chapterTreeVos.add(chapterTreeVo);
        }
        return chapterTreeVos;
    }

    private String getDateContent(Date startDate, Date endDate) {
        if (startDate == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(startDate);
        StringBuffer result = new StringBuffer();
        result.append("第" + calendar.get(Calendar.WEEK_OF_YEAR) + "周 ");
        result.append("周" + weekdays[calendar.get(Calendar.DAY_OF_WEEK) - 1] + "\n");
        result.append(format.format(startDate));
        result.append("-" + format.format(endDate));
        return result.toString();
    }

    private String getContentName(String contentIds) {
        if (StringUtils.isEmpty(contentIds)) {
            return "";
        }
        String[] ids = contentIds.split(",");
        if (ids.length == 0) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < ids.length; i++) {
            String id = ids[i];
            if (i == 0) {
                //查询课程信息
                Course course = courseRepository.getOne(Integer.parseInt(id));
                if (course != null) {
                    result.append(course.getCourseName());
                }
            }
            if (i > 0) {
                //查询章节信息
                CourseChapter chapter = courseChapterRepository.getOne(Integer.parseInt(id));
                if (chapter != null) {
                    result.append(result.length() > 0 ? ">" + chapter.getChapterName() : chapter.getChapterName());
                }
            }
        }
        return result.toString();
    }

    private void getAllChapterTreeByCicle(List<ChapterTreeVo> chapterTreeVos) {
        if (CollectionUtils.isEmpty(chapterTreeVos)) {
            return;
        }
        for (ChapterTreeVo chapterTreeVo : chapterTreeVos) {
            List<CourseChapter> chaptersc = courseChapterRepository.findByChapterParentId(chapterTreeVo.getValue());
            if (CollectionUtils.isNotEmpty(chaptersc)) {
                List<ChapterTreeVo> treeVos = new ArrayList<>();
                for (CourseChapter chapter : chaptersc) {
                    ChapterTreeVo treeVo = new ChapterTreeVo();
                    treeVo.setLabel(chapter.getChapterName());
                    treeVo.setValue(chapter.getId());
                    treeVos.add(treeVo);
                }
                chapterTreeVo.setChildren(treeVos);
                getAllChapterTreeByCicle(treeVos);
            }
        }
    }
}
