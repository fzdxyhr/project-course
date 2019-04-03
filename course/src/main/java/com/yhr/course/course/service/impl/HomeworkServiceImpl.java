package com.yhr.course.course.service.impl;

import com.yhr.course.course.config.GaeaContext;
import com.yhr.course.course.contants.RoleEnum;
import com.yhr.course.course.dao.ClassesRepository;
import com.yhr.course.course.dao.HomeworkRepository;
import com.yhr.course.course.dao.HomeworkSubmitRepository;
import com.yhr.course.course.dao.UserRepository;
import com.yhr.course.course.entity.*;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.HomeworkService;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.service.UserService;
import com.yhr.course.course.utils.ExcelUtils;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.HomeworkUserExportVo;
import com.yhr.course.course.vo.HomeworkUserVo;
import com.yhr.course.course.vo.HomeworkVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Administrator on 2019-01-10.
 */

@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private HomeworkSubmitRepository homeworkSubmitRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PagerHelper<HomeworkVo> list(String key, Integer pageNo, Integer pageSize) throws Exception {
        PagerHelper<HomeworkVo> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_homework where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and homework_title like ?");
            params.add("%" + key + "%");
        }
        //判断是否为管理员,管理员不进行过滤
        User user = userService.get(GaeaContext.getAdminUserId());
        if (!RoleEnum.ADMIN.getValue().equals(user.getRole())) {
            sql.append(" and publish_teacher = ?");
            params.add(GaeaContext.getAdminUserId());
        }

        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<HomeworkVo> tags = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<HomeworkVo>(HomeworkVo.class));
        if (CollectionUtils.isNotEmpty(tags)) {
            Map<Integer, User> userMap = userService.getAllUserMap();
            for (HomeworkVo tag : tags) {
                tag.setPublishTeacherName(tag.getPublishTeacher() == null || userMap.get(tag.getPublishTeacher()) == null ? "" : userMap.get(tag.getPublishTeacher()).getUserName());
            }
        }
        result.setTotal(total);
        result.setItems(tags);
        return result;
    }

    @Override
    public PagerHelper<HomeworkVo> listFront(String key, Integer pageNo, Integer pageSize) throws Exception {
        PagerHelper<HomeworkVo> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_homework where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and homework_title like ?");
            params.add("%" + key + "%");
        }

        //根据学生查询对应的班级，并获取班级对应的授课老师，然后查询对应布置的作业
        User user = userService.get(GaeaContext.getUserId());
        //获取对应的班级信息
        Classes classes = classesRepository.getOne(user.getClassId());
        sql.append(" and publish_teacher = ?");
        params.add(classes.getTeacherId());

        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<HomeworkVo> homeworkVos = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<HomeworkVo>(HomeworkVo.class));
        if (CollectionUtils.isNotEmpty(homeworkVos)) {
            Map<Integer, User> userMap = userService.getAllUserMap();
            for (HomeworkVo homeworkVo : homeworkVos) {
                homeworkVo.setPublishTeacherName(homeworkVo.getPublishTeacher() == null || userMap.get(homeworkVo.getPublishTeacher()) == null ? "" : userMap.get(homeworkVo.getPublishTeacher()).getUserName());
                //获取该学生作业评分值
                HomeworkSubmit homeworkSubmit = homeworkSubmitRepository.findByHomeworkIdAndUserId(homeworkVo.getId(), GaeaContext.getUserId());
                if (homeworkSubmit != null) {
                    homeworkVo.setScore(homeworkSubmit.getScore());
                    homeworkVo.setSubmitHomeworkFilePath(homeworkSubmit.getHomeworkFilePath());
                }
            }
        }
        result.setTotal(total);
        result.setItems(homeworkVos);
        return result;
    }

    @Override
    public HomeworkVo create(HomeworkVo homeworkVo) {
        Homework homework = new Homework();
        BeanUtils.copyProperties(homeworkVo, homework);
        homework.setPublishTeacher(GaeaContext.getAdminUserId());
        homework.setCreateTime(new Date());
        homeworkRepository.save(homework);
        return homeworkVo;
    }

    @Override
    public Homework update(Integer id, Homework homework) throws Exception {
        Homework tempHomework = homeworkRepository.getOne(id);
        if (tempHomework == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        BeanUtils.copyProperties(homework, tempHomework);
        return homeworkRepository.save(tempHomework);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Homework tempTag = homeworkRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        homeworkRepository.delete(tempTag);
    }

    @Override
    public Homework get(Integer id) throws Exception {
        Homework tempTag = homeworkRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        return tempTag;
    }

    @Override
    public HomeworkSubmit submit(HomeworkSubmit homeworkSubmit) {
        HomeworkSubmit submit = homeworkSubmitRepository.findByHomeworkIdAndUserId(homeworkSubmit.getHomeworkId(), GaeaContext.getUserId());
        if (submit == null) {
            submit = new HomeworkSubmit();
            submit.setCreateTime(new Date());
        }
        submit.setHomeworkId(homeworkSubmit.getHomeworkId());
        submit.setHomeworkFilePath(homeworkSubmit.getHomeworkFilePath());
        submit.setUserId(GaeaContext.getUserId());
        return homeworkSubmitRepository.save(submit);
    }

    @Override
    public List<HomeworkUserVo> listUsers(Integer id) {
        List<HomeworkSubmit> homeworkSubmits = homeworkSubmitRepository.findByHomeworkId(id);
        if (CollectionUtils.isEmpty(homeworkSubmits)) {
            return Collections.EMPTY_LIST;
        }
        List<HomeworkUserVo> homeworkUserVoList = new ArrayList<>();
        Map<Integer, User> userMap = userService.getAllUserMap();
        for (HomeworkSubmit homeworkSubmit : homeworkSubmits) {
            HomeworkUserVo homeworkUserVo = new HomeworkUserVo();
            homeworkUserVo.setUserId(homeworkSubmit.getUserId());
            homeworkUserVo.setHomeworkId(homeworkSubmit.getHomeworkId());
            homeworkUserVo.setUserName(homeworkSubmit.getUserId() == null || userMap.get(homeworkSubmit.getUserId()) == null ? "" : userMap.get(homeworkSubmit.getUserId()).getUserName());
            homeworkUserVo.setSubmitTime(homeworkSubmit.getCreateTime());
            homeworkUserVo.setSubmitHomeworkFilePath(homeworkSubmit.getHomeworkFilePath());
            homeworkUserVo.setScore(homeworkSubmit.getScore());
            homeworkUserVoList.add(homeworkUserVo);
        }
        return homeworkUserVoList;
    }

    @Override
    public void score(Integer id, Integer userId, Integer score) {
        HomeworkSubmit homeworkSubmit = homeworkSubmitRepository.findByHomeworkIdAndUserId(id, userId);
        homeworkSubmit.setScore(score);
        homeworkSubmitRepository.save(homeworkSubmit);
    }

    @Override
    public void exportSubmitStudent(HttpServletResponse response, Integer id, Integer classId) {
        Homework homework = homeworkRepository.getOne(id);
        Classes classes = null;
        List<Integer> userIds = new ArrayList<>();
        List<HomeworkSubmit> homeworkSubmits = new ArrayList<>();
        if (classId != null) {
            classes = classesRepository.getOne(classId);
            List<User> users = userRepository.findByClassId(classId);
            if (CollectionUtils.isNotEmpty(users)) {
                for (User user : users) {
                    userIds.add(user.getId());
                }
            }
            homeworkSubmits = homeworkSubmitRepository.findByHomeworkIdAndUserIdIn(id, userIds);
        } else {
            homeworkSubmits = homeworkSubmitRepository.findByHomeworkId(id);
        }
        List<HomeworkUserExportVo> userExportVos = new ArrayList<>();
        Map<Integer, User> userMap = userService.getAllUserMap();
        for (HomeworkSubmit homeworkSubmit : homeworkSubmits) {
            HomeworkUserExportVo userExportVo = new HomeworkUserExportVo();
            userExportVo.setClassName(classes == null ? "" : classes.getClassName());
            userExportVo.setHomeworkName(homework.getHomeworkTitle());
            userExportVo.setScore(homeworkSubmit.getScore());
            userExportVo.setUserName(homeworkSubmit.getUserId() == null || userMap.get(homeworkSubmit.getUserId()) == null ? "" : userMap.get(homeworkSubmit.getUserId()).getUserName());
            userExportVo.setSubmitHomeworkFilePath(homeworkSubmit.getHomeworkFilePath());
            userExportVo.setSubmitTime(homeworkSubmit.getCreateTime());
            userExportVos.add(userExportVo);
        }
        ExcelUtils<HomeworkUserExportVo> exportExcelUtil = new ExcelUtils<>();
        Map<String, String> operationMap = new HashMap<String, String>();
        Map<String, Object> valueMap = new HashMap<String, Object>();
        String fileName = classes == null ? "所有提交作业学生" : classes.getClassName() + "提交作业学生";
        String[] headers = new String[]{"用户名称", "班级名称", "作业名称", "评分", "提交作业文件路径", "提交时间"};
        exportExcelUtil.exportExcel(fileName, headers, userExportVos, "yyyy-MM-dd HH:mm:ss", fileName, response, operationMap, valueMap);
    }
}
