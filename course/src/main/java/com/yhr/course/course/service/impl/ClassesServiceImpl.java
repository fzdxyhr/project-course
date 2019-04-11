package com.yhr.course.course.service.impl;

import com.yhr.course.course.config.GaeaContext;
import com.yhr.course.course.contants.RoleEnum;
import com.yhr.course.course.dao.ClassesRepository;
import com.yhr.course.course.dao.SignRepository;
import com.yhr.course.course.dao.TagRepository;
import com.yhr.course.course.dao.UserRepository;
import com.yhr.course.course.entity.Classes;
import com.yhr.course.course.entity.Sign;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.ClassesService;
import com.yhr.course.course.service.UserService;
import com.yhr.course.course.utils.ExcelUtils;
import com.yhr.course.course.utils.PagerHelper;
import com.yhr.course.course.vo.StudentVo;
import com.yhr.course.course.vo.UserExportVo;
import com.yhr.course.course.vo.UserSignExportVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author yhr
 * @version latest
 * @date 2019/2/27
 */

@Service
public class ClassesServiceImpl implements ClassesService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SignRepository signRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public PagerHelper<Classes> list(String key, Integer pageNo, Integer pageSize) throws Exception {
        PagerHelper<Classes> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_classes where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and class_name like ?");
            params.add("%" + key + "%");
        }
        //判断是否为管理员,管理员不进行过滤
        User user = userService.get(GaeaContext.getAdminUserId());
        if (!RoleEnum.ADMIN.getValue().equals(user.getRole())) {
            sql.append(" and teacher_id = ?");
            params.add(GaeaContext.getAdminUserId());
        }
        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);
        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<Classes> classesList = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<Classes>(Classes.class));
        if (CollectionUtils.isNotEmpty(classesList)) {
            Map<Integer, User> userMap = userService.getAllUserMap();
            for (Classes classes : classesList) {
                classes.setProgress(classes.getProgress() == null ? 0 : classes.getProgress());
                //根据教师标识查询对应的教师名称
                classes.setTeacherName(classes.getTeacherId() == null || userMap.get(classes.getTeacherId()) == null ? "" : userMap.get(classes.getTeacherId()).getUserName());
                //根据学生标识查询对应的学生姓名
                classes.setMonitorName(classes.getMonitor() == null || userMap.get(classes.getMonitor()) == null ? "" : userMap.get(classes.getMonitor()).getUserName());
            }
        }
        result.setTotal(total);
        result.setItems(classesList);
        return result;
    }

    @Override
    public Classes create(Classes classes) {
        classes.setCreateTime(new Date());
        return classesRepository.save(classes);
    }

    @Override
    public Classes update(Integer id, Classes classes) throws Exception {
        Classes tempTag = classesRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的班级");
        }
        BeanUtils.copyProperties(classes, tempTag);
        return classesRepository.save(tempTag);
    }

    @Override
    public void delete(Integer id) throws Exception {
        Classes tempClasses = classesRepository.getOne(id);
        if (tempClasses == null) {
            throw new ServiceException("不存在【" + id + "】对应的班级");
        }
        classesRepository.delete(tempClasses);
    }

    @Override
    public Classes get(Integer id) throws Exception {
        Classes tempClasses = classesRepository.getOne(id);
        if (tempClasses == null) {
            throw new ServiceException("不存在【" + id + "】对应的班级");
        }
        return tempClasses;
    }

    /**
     * 根据班级查询对应学生及签到情况
     *
     * @param classId
     * @return
     */
    @Override
    public List<StudentVo> findClassesStudent(Integer classId) {
        List<User> users = userRepository.findByClassId(classId);
        if (CollectionUtils.isEmpty(users)) {
            return Collections.emptyList();
        }
        List<StudentVo> studentVos = new ArrayList<>();
        Classes classes = classesRepository.getOne(classId);
        for (User user : users) {
            Sign sign = signRepository.findByStudentIdAndTeacherIdAndCreateTime(user.getId(), classes.getTeacherId(), simpleDateFormat.format(new Date()));
            StudentVo studentVo = new StudentVo();
            studentVo.setUserName(user.getUserName());
            studentVo.setAccount(user.getAccount());
            studentVo.setSign(sign == null ? 0 : 1);
            studentVos.add(studentVo);
        }
        return studentVos;
    }

    /**
     * 导出班级学生
     *
     * @param response
     * @param classId
     */
    @Override
    public void exportStudent(HttpServletResponse response, Integer classId) {
        List<UserExportVo> exportVos = new ArrayList<>();
        Classes classes = classesRepository.getOne(classId);
        List<User> users = userRepository.findByClassId(classId);
        ExcelUtils<UserExportVo> exportExcelUtil = new ExcelUtils<>();
        Map<String, String> operationMap = new HashMap<>();
        Map<String, Object> valueMap = new HashMap<>();
        String fileName = classes == null ? "学生" : classes.getClassName() + "学生";
        String[] headers = new String[]{"学生名称", "学生账号", "班级名称", "身份证", "性别", "手机号码", "状态", "创建时间"};
        for (User user : users) {
            UserExportVo exportVo = new UserExportVo();
            BeanUtils.copyProperties(user, exportVo);
            exportVo.setClassName(classes.getClassName());
            exportVo.setSex(user.getSex() == 1 ? "男" : "女");
            exportVo.setStatus(user.getStatus() == 1 ? "有效" : "失效");
            exportVos.add(exportVo);
        }
        exportExcelUtil.exportExcel(fileName, headers, exportVos, "yyyy-MM-dd HH:mm:ss", fileName, response, operationMap, valueMap);
    }

    /**
     * 导出班级签到学生信息
     *
     * @param response
     * @param classId
     */
    @Override
    public void exportSignStudent(HttpServletResponse response, Integer classId) {
        List<User> users = userRepository.findByClassId(classId);
        Classes classes = classesRepository.getOne(classId);
        ExcelUtils<UserSignExportVo> exportExcelUtil = new ExcelUtils<>();
        Map<String, String> operationMap = new HashMap<>();
        Map<String, Object> valueMap = new HashMap<>();
        String fileName = classes == null ? "学生" + simpleDateFormat.format(new Date()) + "签到数据" : classes.getClassName() + "学生" + simpleDateFormat.format(new Date()) + "签到数据";
        String[] headers = new String[]{"学生名称", "学生账号", "班级名称", "是否签到"};
        List<UserSignExportVo> studentVos = new ArrayList<>();
        for (User user : users) {
            Sign sign = signRepository.findByStudentIdAndTeacherIdAndCreateTime(user.getId(), classes.getTeacherId(), simpleDateFormat.format(new Date()));
            UserSignExportVo studentVo = new UserSignExportVo();
            studentVo.setUserName(user.getUserName());
            studentVo.setAccount(user.getAccount());
            studentVo.setSign(sign == null ? "未签到" : "已签到");
            studentVo.setClassName(classes == null ? "" : classes.getClassName());
            studentVos.add(studentVo);
        }
        exportExcelUtil.exportExcel(fileName, headers, studentVos, "yyyy-MM-dd HH:mm:ss", fileName, response, operationMap, valueMap);
    }

    public Map<Integer, Classes> getAllClassesMap() {
        List<Classes> classesList = classesRepository.findAll();
        if (CollectionUtils.isEmpty(classesList)) {
            return new HashMap<>();
        }
        Map<Integer, Classes> classesMap = new HashMap<>();
        for (Classes classes : classesList) {
            classesMap.put(classes.getId(), classes);
        }
        return classesMap;
    }
}
