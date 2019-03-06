package com.yhr.course.course.service.impl;

import com.yhr.course.course.config.GaeaContext;
import com.yhr.course.course.contants.Contants;
import com.yhr.course.course.dao.CourseChapterRepository;
import com.yhr.course.course.dao.CourseRepository;
import com.yhr.course.course.dao.CourseStudentRepository;
import com.yhr.course.course.dao.TagRepository;
import com.yhr.course.course.entity.Course;
import com.yhr.course.course.entity.CourseChapter;
import com.yhr.course.course.entity.CourseStudent;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.CourseService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseChapterVo;
import com.yhr.course.course.vo.CourseVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019-01-09.
 */

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CourseStudentRepository courseStudentRepository;
    @Autowired
    private CourseChapterRepository courseChapterRepository;

    @Override
    public PagerHelper<CourseVo> list(String key, Integer pageNo, Integer pageSize) {
        PagerHelper<CourseVo> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_course where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and course_name like ?");
            params.add("%" + key + "%");
        }

        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<CourseVo> courseVos = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(CourseVo.class));
        if (CollectionUtils.isNotEmpty(courseVos)) {
            for (CourseVo courseVo : courseVos) {
                List<CourseChapter> courseChapters = courseChapterRepository.findByCourseId(courseVo.getId());
                courseVo.setCourseChapterVos(resolveChapter(courseChapters));
            }
        }
        result.setTotal(total);
        result.setItems(courseVos);
        return result;
    }

    @Override
    public Course create(Course course) {
        course.setCreateTime(new Date());
        return courseRepository.save(course);
    }

    @Override
    public Course update(Integer id, Course course) throws Exception {
        Course tempCourse = courseRepository.getOne(id);
        if (tempCourse == null) {
            throw new ServiceException("不存在【" + id + "】对应的课程");
        }
        tempCourse.setCourseName(course.getCourseName());
        tempCourse.setCourseDesc(course.getCourseDesc());
        tempCourse.setCourseRoom(course.getCourseRoom());
        tempCourse.setCourseImageUrl(course.getCourseImageUrl());
        tempCourse.setCourseTip(course.getCourseTip());
        tempCourse.setTeacherId(course.getTeacherId());
        tempCourse.setMaxStudent(course.getMaxStudent());
        tempCourse.setTagId(course.getTagId());
        return courseRepository.save(tempCourse);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Course course = courseRepository.getOne(id);
        if (course == null) {
            throw new ServiceException("不存在【" + id + "】对应的课程");
        }
        courseRepository.delete(course);
    }

    @Override
    public CourseVo get(Integer id) throws Exception {
        Course course = courseRepository.getOne(id);
        if (course == null) {
            throw new ServiceException("不存在【" + id + "】对应的课程");
        }
        CourseVo courseVo = new CourseVo();
        BeanUtils.copyProperties(course, courseVo);
        List<CourseChapter> courseChapters = courseChapterRepository.findByCourseId(courseVo.getId());
        courseVo.setCourseChapterVos(resolveChapter(courseChapters));
        return courseVo;
    }

    @Override
    public void study(Integer id) throws Exception {
        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourseId(id);
        courseStudent.setStudentId(GaeaContext.getUserId());
        courseStudent.setCreateTime(new Date());
        courseStudentRepository.save(courseStudent);
    }

    @Override
    public CourseStudent getStudy(Integer id) throws Exception {
        return courseStudentRepository.findByStudentIdAndCourseId(GaeaContext.getUserId(), id);
    }

    @Override
    public String upload(MultipartFile multipartFile) throws Exception {
        if (multipartFile == null) {
            return "";
        }
        String fileName = multipartFile.getOriginalFilename();
        String relativePath = "image";
        InputStream inputStream = multipartFile.getInputStream();
        File destDir = new File(Contants.UPLOAD_FILE_PATH + relativePath);
        if (!destDir.exists()) {
            FileUtils.forceMkdir(destDir);
        }
        File destFile = new File(Contants.UPLOAD_FILE_PATH + relativePath + File.separator + fileName);
        OutputStream outputStream = new FileOutputStream(destFile);
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        return fileName;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws Exception {
        if (multipartFile == null) {
            return "";
        }
        String fileName = new String(multipartFile.getOriginalFilename().getBytes("UTF-8"), Charset.forName("utf-8"));
        String relativePath = "file";
        InputStream inputStream = multipartFile.getInputStream();
        File destDir = new File(Contants.UPLOAD_FILE_PATH + relativePath);
        if (!destDir.exists()) {
            FileUtils.forceMkdir(destDir);
        }
        File destFile = new File(Contants.UPLOAD_FILE_PATH + relativePath + File.separator + fileName);
        OutputStream outputStream = new FileOutputStream(destFile);
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        return "http://localhost:8085/v1/files/download/" + fileName;
    }

    @Override
    public void downloadImage(String relativePath, HttpServletResponse response) throws Exception {
        String rootPath = Contants.UPLOAD_FILE_PATH;
        String path = rootPath + "image" + File.separator + relativePath;
        OutputStream outputStream = response.getOutputStream();
        InputStream iStream = new FileInputStream(new File(path));
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = iStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
    }

    @Override
    public void downloadFile(String fileName, HttpServletResponse response) throws Exception {
        String rootPath = Contants.UPLOAD_FILE_PATH;
        String path = rootPath + "file" + File.separator + fileName;
        OutputStream outputStream = response.getOutputStream();
        InputStream iStream = new FileInputStream(new File(path));
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = iStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
    }

    private List<CourseChapterVo> resolveChapter(List<CourseChapter> courseChapters) {
        if (CollectionUtils.isEmpty(courseChapters)) {
            return Collections.EMPTY_LIST;
        }
        List<CourseChapterVo> chapters = new ArrayList<>();
        for (CourseChapter courseChapter : courseChapters) {
            if (courseChapter.getChapterParentId() == null) {
                CourseChapterVo chapterVo = new CourseChapterVo();
                BeanUtils.copyProperties(courseChapter, chapterVo);
                chapters.add(chapterVo);
            }
        }
        for (CourseChapterVo chapter : chapters) {
            List<CourseChapterVo> courseChapterVos = new ArrayList<>();
            for (CourseChapter courseChapter : courseChapters) {
                if (courseChapter.getChapterParentId() == chapter.getId()) {
                    CourseChapterVo chapterVo = new CourseChapterVo();
                    BeanUtils.copyProperties(courseChapter, chapterVo);
                    courseChapterVos.add(chapterVo);
                }
            }
            chapter.setCourseChapterVos(courseChapterVos);
        }
        return chapters;
    }

    private String getFileName(MultipartFile multipartFile) {
        try {
            String suffix = multipartFile.getOriginalFilename().split("\\.")[1];
            byte[] uploadBytes = multipartFile.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            String hashString = new BigInteger(1, digest).toString(16);
            return hashString + "." + suffix;
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        return multipartFile.getOriginalFilename();
    }
}
