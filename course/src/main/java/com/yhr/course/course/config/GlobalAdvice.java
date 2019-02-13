package com.yhr.course.course.config;

import com.yhr.course.course.exception.ServiceException;
import com.yhr.course.course.utils.ResponseData;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019-01-10.
 */

@ControllerAdvice
public class GlobalAdvice implements ResponseBodyAdvice<Object> {

    @ExceptionHandler(value = ServiceException.class)    //异常处理器，处理ServiceException异常
    @ResponseStatus(HttpStatus.OK)
    public ResponseData handleException(HttpServletRequest request, Exception e) {
        ResponseData responseData = new ResponseData<>();
        responseData.setSuccess(false);
        responseData.setMessage(e.getMessage());
        responseData.setUrl(request.getRequestURI().toString());
        responseData.setData(null);
        return responseData;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ResponseData result = new ResponseData();
        HttpServletResponse httpServletResponse = (HttpServletResponse) serverHttpResponse;
        HttpServletRequest servletRequest = (HttpServletRequest) serverHttpRequest;
        if (HttpStatus.OK.value() == httpServletResponse.getStatus()) {
            result.setSuccess(true);
            result.setMessage("请求成功");
        } else {
            result.setSuccess(false);
            result.setMessage("请求失败");
        }
        result.setUrl(servletRequest.getRequestURI());
        result.setData(body);
        return result;
    }
}
