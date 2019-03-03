package com.yhr.course.course.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yhr
 * @version latest
 * @date 2018/9/11
 */

public class GaeaContext {

    @ApiModelProperty("用户标识")
    private static Integer userId;

    @ApiModelProperty("后台用户标识")
    private static Integer adminUserId;

    @ApiModelProperty("项目标识")
    private static Integer projectId;

    public static Integer getAdminUserId() {
        return adminUserId;
    }

    public static void setAdminUserId(Integer adminUserId) {
        GaeaContext.adminUserId = adminUserId;
    }

    public static Integer getUserId() {
        return GaeaContext.userId;
    }

    public static void setUserId(Integer userId) {
        GaeaContext.userId = userId;
    }

    public static Integer getProjectId() {
        return GaeaContext.projectId;
    }

    public static void setProjectId(Integer projectId) {
        GaeaContext.projectId = projectId;
    }
}
