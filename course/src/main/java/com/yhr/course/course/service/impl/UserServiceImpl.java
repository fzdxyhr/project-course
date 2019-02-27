package com.yhr.course.course.service.impl;

import com.yhr.course.course.contants.RoleEnum;
import com.yhr.course.course.dao.TagRepository;
import com.yhr.course.course.dao.UserRepository;
import com.yhr.course.course.entity.Tag;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.service.TagService;
import com.yhr.course.course.service.UserService;
import com.yhr.course.course.utils.ExcelUtils;
import com.yhr.course.course.utils.MD5Utils;
import com.yhr.course.course.utils.PagerHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Administrator on 2019-01-10.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public PagerHelper<User> list(String key, String role, Integer pageNo, Integer pageSize) {
        PagerHelper<User> result = new PagerHelper<>();
        StringBuffer sql = new StringBuffer("select * from s_user where 1=1");
        List<Object> params = new ArrayList<>();
        if (StringUtils.isNotEmpty(key)) {
            sql.append(" and user_name like ?");
            params.add("%" + key + "%");
        }
        if (StringUtils.isNotEmpty(role)) {
            sql.append(" and role = ?");
            params.add(role);
        }
        StringBuffer totalSql = new StringBuffer("select count(1) from (" + sql.toString() + ") a");
        Integer total = jdbcTemplate.queryForObject(totalSql.toString(), params.toArray(), Integer.class);

        int startIndex = (pageNo - 1) * pageSize;
        sql.append(" limit ?,?");
        params.add(startIndex);
        params.add(pageSize);
        List<User> users = jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<User>(User.class));
        result.setTotal(total);
        result.setItems(users);
        return result;
    }

    @Override
    public User create(User user) {
        user.setCreateTime(new Date());
        return userRepository.save(user);
    }

    @Override
    public User update(Integer id, User user) throws Exception {
        User tempUser = userRepository.getOne(id);
        if (tempUser == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        BeanUtils.copyProperties(user, tempUser);
        return userRepository.save(tempUser);
    }

    @Override
    public void delete(Integer id) throws Exception {
        User tempTag = userRepository.getOne(id);
        if (tempTag == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        userRepository.delete(tempTag);
    }

    @Override
    public User get(Integer id) throws Exception {
        User tempUser = userRepository.getOne(id);
        if (tempUser == null) {
            throw new ServiceException("不存在【" + id + "】对应的标签");
        }
        return tempUser;
    }

    @Override
    public void downloadTemplate(HttpServletResponse response) throws Exception {
        ExcelUtils<User> exportExcelUtil = new ExcelUtils<>();
        Map<String, String> operationMap = new HashMap<String, String>();
        Map<String, Object> valueMap = new HashMap<String, Object>();
        String fileName = "学生模板";
        String[] headers = new String[]{"用户名称", "账号", "性别", "身份证", "手机号码"};
        exportExcelUtil.exportExcel(fileName, headers, new ArrayList<>(), "yyyy-MM-dd HH:mm:ss", fileName, response, operationMap, valueMap);
    }

    @Override
    public void parseExcel(MultipartFile multipartFile) throws Exception {
        //检查文件格式
        checkFile(multipartFile);
        ExcelUtils<User> excelUtils = new ExcelUtils<>();
        List<String[]> datas = excelUtils.getExcelData(multipartFile);
        for (int i = 0; i < datas.size(); i++) {
            User user = new User();
            Map<String, Integer> sexMap = getSexMap();
            user.setUserName(datas.get(i).length > 0 ? datas.get(i)[0].trim() : null);
            user.setAccount(datas.get(i).length > 1 ? datas.get(i)[1].trim() : null);
            user.setPassword(MD5Utils.MD5Encode("123456", "utf8"));
            user.setSex(datas.get(i).length > 2 ? Integer.parseInt(sexMap.get(datas.get(i)[2].trim()).toString()) : null);
            user.setIdCard(datas.get(i).length > 3 ? datas.get(i)[3].trim() : null);
            user.setTelephone(datas.get(i).length > 4 ? datas.get(i)[4].trim() : null);
            user.setRole(RoleEnum.STUDENT.getValue());
            user.setIsAdmin(0);
            user.setCreateTime(new Date());
            userRepository.save(user);
        }
    }

    private Map<String, Integer> getSexMap() {
        Map<String, Integer> result = new HashMap<>();
        result.put("男", 1);
        result.put("女", 2);
        return result;
    }

    /**
     * 检查文件格式
     *
     * @param file
     * @throws Exception
     */
    private static void checkFile(MultipartFile file) throws Exception {
        //判断文件是否存在
        if (null == file) {
            throw new ServiceException("文件不存在");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            throw new ServiceException("上传文件必须以xls或者xlsx格式");
        }
    }
}
