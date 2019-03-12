package com.yhr.course.course.service.impl;

import com.yhr.course.course.config.AliyunFileHandle;
import com.yhr.course.course.config.GaeaContext;
import com.yhr.course.course.dao.CourseChapterRepository;
import com.yhr.course.course.dao.CourseRepository;
import com.yhr.course.course.dao.CourseStudentRepository;
import com.yhr.course.course.dao.UserStudyProgressRepository;
import com.yhr.course.course.entity.Course;
import com.yhr.course.course.entity.CourseChapter;
import com.yhr.course.course.entity.CourseStudent;
import com.yhr.course.course.entity.UserStudyProgress;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.CourseService;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.CourseChapterVo;
import com.yhr.course.course.vo.CourseVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.*;

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
    @Autowired
    private AliyunFileHandle aliyunFileHandle;
    @Autowired
    private UserStudyProgressRepository userStudyProgressRepository;
    @Autowired
    private TagService tagService;

    @Override
    public PagerHelper<CourseVo> list(String key, Integer tagId, Integer pageNo, Integer pageSize) {
        PagerHelper<CourseVo> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_course where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and course_name like ?");
            params.add("%" + key + "%");
        }
        if (tagId != null) {
            sql.append(" and tag_id like ?");
            params.add("%" + tagId + "%");
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
                courseVo.setCourseChapterVos(resolveChapter(courseChapters, courseVo.getId(), false));
                courseVo.setTags(getTagNames(courseVo.getTagId()));
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
    public Course update(Integer id, CourseVo course) throws Exception {
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
        courseVo.setCourseChapterVos(resolveChapter(courseChapters, id, true));
        return courseVo;
    }

    @Override
    public void study(Integer id, Integer chapterId) throws Exception {
        UserStudyProgress userStudyProgress = userStudyProgressRepository.findByCourseIdAndUserIdAndChapterId(id, GaeaContext.getUserId(), chapterId);
        if (userStudyProgress == null) {
            userStudyProgress = new UserStudyProgress();
            userStudyProgress.setCourseId(id);
            userStudyProgress.setChapterId(chapterId);
            userStudyProgress.setProgress(0);
            userStudyProgress.setUserId(GaeaContext.getUserId());
            userStudyProgress.setCreateTime(new Date());
        } else {
            userStudyProgress.setCreateTime(new Date());
        }
        userStudyProgressRepository.save(userStudyProgress);
    }

    @Override
    public boolean getStudy(Integer id) throws Exception {
        List<UserStudyProgress> studyProgressList = userStudyProgressRepository.findByCourseIdAndUserIdOrderByCreateTimeDesc(id, GaeaContext.getUserId());
        if (CollectionUtils.isEmpty(studyProgressList)) {
            return false;
        }
        return true;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws Exception {
        if (multipartFile == null) {
            return "";
        }
        String fileName = new String(multipartFile.getOriginalFilename().getBytes("UTF-8"), Charset.forName("utf-8"));
//        String relativePath = "image";
//        InputStream inputStream = multipartFile.getInputStream();
//        File destDir = new File(Contants.UPLOAD_FILE_PATH + relativePath);
//        if (!destDir.exists()) {
//            FileUtils.forceMkdir(destDir);
//        }
//        File destFile = new File(Contants.UPLOAD_FILE_PATH + relativePath + File.separator + fileName);
//        OutputStream outputStream = new FileOutputStream(destFile);
//        int len = 0;
//        byte[] buffer = new byte[1024];
//        while ((len = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, len);
//        }
        aliyunFileHandle.uploadFile(fileName, multipartFile.getInputStream());
        return "http://filecourse.oss-cn-shanghai.aliyuncs.com/" + fileName;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws Exception {
        if (multipartFile == null) {
            return "";
        }
        String fileName = new String(multipartFile.getOriginalFilename().getBytes("UTF-8"), Charset.forName("utf-8"));
//        String relativePath = "file";
//        InputStream inputStream = multipartFile.getInputStream();
//        File destDir = new File(Contants.UPLOAD_FILE_PATH + relativePath);
//        if (!destDir.exists()) {
//            FileUtils.forceMkdir(destDir);
//        }
//        File destFile = new File(Contants.UPLOAD_FILE_PATH + relativePath + File.separator + fileName);
//        OutputStream outputStream = new FileOutputStream(destFile);
//        int len = 0;
//        byte[] buffer = new byte[1024];
//        while ((len = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, len);
//        }
        aliyunFileHandle.uploadFile(fileName, multipartFile.getInputStream());
        return "http://filecourse.oss-cn-shanghai.aliyuncs.com/" + fileName;
    }

    @Override
    public void downloadImage(String fileName, HttpServletResponse response) throws Exception {
//        String rootPath = Contants.UPLOAD_FILE_PATH;
//        String path = rootPath + "image" + File.separator + relativePath;
//        OutputStream outputStream = response.getOutputStream();
//        InputStream iStream = new FileInputStream(new File(path));
//        int len = 0;
//        byte[] buffer = new byte[1024];
//        while ((len = iStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, len);
//        }
        aliyunFileHandle.downloadFile(fileName, response.getOutputStream());
    }

    @Override
    public void downloadFile(String fileName, HttpServletResponse response) throws Exception {
//        String rootPath = Contants.UPLOAD_FILE_PATH;
//        String path = rootPath + "file" + File.separator + fileName;
////        response.setContentType("video/mp4");
//        OutputStream outputStream = response.getOutputStream();
//        InputStream iStream = new FileInputStream(new File(path));
//        int len = 0;
//        byte[] buffer = new byte[1024];
//        while ((len = iStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, len);
//        }
        aliyunFileHandle.downloadFile(fileName, response.getOutputStream());
    }

    private List<CourseChapterVo> resolveChapter(List<CourseChapter> courseChapters, Integer courseId, boolean isNeedStudy) {
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
        if (isNeedStudy) {//为true 说明需要设置章节是否为最近学习章节
            List<UserStudyProgress> progresses = userStudyProgressRepository.findByCourseIdAndUserIdOrderByCreateTimeDesc(courseId, GaeaContext.getUserId());
            //取得第一条数据即为最近学习的章节
            if (CollectionUtils.isEmpty(progresses)) {
                return chapters;
            }
            UserStudyProgress studyProgress = progresses.get(0);
            for (CourseChapterVo chapter : chapters) {
                for (CourseChapterVo childChapter : chapter.getCourseChapterVos()) {
                    if (studyProgress.getChapterId().equals(childChapter.getId())) {
                        childChapter.setRecentStudy(true);
                    }
                    UserStudyProgress userStudyProgress = userStudyProgressRepository.findByCourseIdAndUserIdAndChapterId(courseId, GaeaContext.getUserId(), childChapter.getId());
                    if (userStudyProgress != null) {
                        childChapter.setStudy(true);
                    }
                }
            }
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

    //获取课程对应的标签名称
    private List<String> getTagNames(String tagId) {
        if (StringUtils.isEmpty(tagId)) {
            return Collections.EMPTY_LIST;
        }
        List<String> tagNames = new ArrayList<>();
        Map<Integer, String> stringMap = tagService.getAllTagMap();
        String[] tagIds = tagId.split(",");
        for (String id : tagIds) {
            if (stringMap.get(Integer.parseInt(id)) == null || StringUtils.isNotEmpty(stringMap.get(Integer.parseInt(id)))) {
                tagNames.add(stringMap.get(Integer.parseInt(id)));
            }
        }
        return tagNames;
    }
}
