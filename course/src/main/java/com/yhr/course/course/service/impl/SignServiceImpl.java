package com.yhr.course.course.service.impl;

import com.yhr.course.course.config.GaeaContext;
import com.yhr.course.course.dao.ClassesRepository;
import com.yhr.course.course.dao.SignRepository;
import com.yhr.course.course.dao.UserRepository;
import com.yhr.course.course.entity.Classes;
import com.yhr.course.course.entity.Sign;
import com.yhr.course.course.entity.User;
import com.yhr.course.course.service.SignService;
import com.yhr.course.course.vo.SignClassesVo;
import com.yhr.course.course.vo.SignTimeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/3/10.
 */
@Service
public class SignServiceImpl implements SignService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private SignRepository signRepository;

    @Override
    public List<SignClassesVo> listSignClasses() {
        List<SignClassesVo> signClassesVos = new ArrayList<>();
        User user = userRepository.getOne(GaeaContext.getUserId());
        Classes classes = classesRepository.getOne(user.getClassId());
        Sign sign = signRepository.findByStudentIdAndTeacherIdAndCreateTime(user.getId(), classes.getTeacherId(), simpleDateFormat.format(new Date()));
        User teacher = userRepository.getOne(classes.getTeacherId());
        SignClassesVo signClassesVo = SignClassesVo.from(classes, teacher.getUserName(), sign == null ? 0 : 1);
        signClassesVo.setCanSign(0);
        if (classes != null && classes.getSignStartTime() != null && classes.getSignEndTime() != null) {
            Date currentTime = new Date();
            if (currentTime.before(classes.getSignEndTime()) && currentTime.after(classes.getSignStartTime())) {
                signClassesVo.setCanSign(1);
            }
        }
        signClassesVos.add(signClassesVo);
        return signClassesVos;
    }

    @Override
    public boolean sign(Integer teacherId) {
        try {
            Sign sign = new Sign();
            sign.setTeacherId(teacherId);
            sign.setStudentId(GaeaContext.getUserId());
            sign.setCreateTime(new Date());
            signRepository.save(sign);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Integer getSignStatus() {
        User user = userRepository.getOne(GaeaContext.getUserId());
        Classes classes = classesRepository.getOne(user.getClassId());
        Sign sign = signRepository.findByStudentIdAndTeacherIdAndCreateTime(user.getId(), classes.getTeacherId(), simpleDateFormat.format(new Date()));
        return sign == null ? 0 : 1;
    }

    @Override
    public SignTimeVo getSignTime() {
        User user = userRepository.getOne(GaeaContext.getUserId());
        Classes classes = classesRepository.getOne(user.getClassId());
        if (classes != null) {
            SignTimeVo signTimeVo = new SignTimeVo();
            signTimeVo.setSignStartTime(classes.getSignStartTime());
            signTimeVo.setSignEndTime(classes.getSignEndTime());
            return signTimeVo;
        }
        return null;
    }
}
